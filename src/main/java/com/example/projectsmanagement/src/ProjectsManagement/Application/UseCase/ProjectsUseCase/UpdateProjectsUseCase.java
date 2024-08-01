package com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.ProjectsUseCase;

import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.UpdateProjectRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Repositories.MySQLRepositories.MySQLProjectsRepository;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Services.CreateDescriptionSagaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateProjectsUseCase {
    @Autowired
    MySQLProjectsRepository repository;

    @Autowired
    CreateDescriptionSagaProducer sagaProducer;

    public BaseResponse run(UpdateProjectRequest request) throws JsonProcessingException, InterruptedException {
        String desciption = sagaProducer.run(request.getDescription());
        return repository.updateProject(request.getUuid(), request.getName(), desciption);
    }
}