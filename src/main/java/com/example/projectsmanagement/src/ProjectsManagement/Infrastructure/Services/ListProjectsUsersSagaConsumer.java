package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Services;

import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.ProjectsControllers.GetProjectsByIdListController;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.GetProjectsByIdRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Utilities.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import java.util.List;

@Component
public class ListProjectsUsersSagaConsumer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    JsonUtil jsonUtil = new JsonUtil();

    @Autowired
    private GetProjectsByIdListController controller;

    public void sendListProjectUser(BaseResponse response, String correlationId) throws JsonProcessingException {
        String payload = jsonUtil.serialize(response);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setCorrelationId(correlationId);
        Message message = new Message(payload.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("list_project_user_responser.queue", message);
    }

    @RabbitListener(queues = "list_project_user_requester.queue")
    public void listen(Message message) throws JsonProcessingException {
        String body = new String(message.getBody());
        GetProjectsByIdRequest request = jsonUtil.deserialize(body, GetProjectsByIdRequest.class);
        String correlationId = request.getSession_uuid();
        List<Long> listId = request.getData();
        BaseResponse response = controller.run(listId);
        sendListProjectUser(response, correlationId);
    }
}
