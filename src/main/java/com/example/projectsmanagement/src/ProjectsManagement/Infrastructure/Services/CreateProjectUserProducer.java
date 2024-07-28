package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProjectUserProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendCreateProjectUser(String message) {
        rabbitTemplate.convertAndSend("create_project_user.queue", message);
    }
}
