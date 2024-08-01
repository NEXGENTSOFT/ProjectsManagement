package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

@Component
public class CreateDescriptionSagaProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final Map<String, CountDownLatch> pendingResponses = new ConcurrentHashMap<>();
    private final Map<String, String> responsePayloads = new ConcurrentHashMap<>();

    public void sendListProjectUser(String description, String correlationId) throws JsonProcessingException {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setCorrelationId(correlationId);
        Message message = new Message(description.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("create_description_request.queue", message);

        pendingResponses.put(correlationId, new CountDownLatch(1));
    }

    @RabbitListener(queues = "create_description_response.queue")
    public void listen(Message message) {
        String correlationId = message.getMessageProperties().getCorrelationId();
        String responsePayload = new String(message.getBody());

        if (pendingResponses.containsKey(correlationId)) {
            responsePayloads.put(correlationId, responsePayload);
            pendingResponses.get(correlationId).countDown();
        }
    }

    public String run(String description) throws JsonProcessingException, InterruptedException {
        String correlationId = UUID.randomUUID().toString();
        sendListProjectUser(description, correlationId);

        CountDownLatch latch = pendingResponses.get(correlationId);
        if (latch != null) {
            latch.await();
            return responsePayloads.get(correlationId);
        } else {
            return description;
        }
    }

}
