package com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.ProjectsUseCase;

import com.example.projectsmanagement.src.ProjectsManagement.Domain.Entity.Projects;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.CreateProjectsRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Models.ProjectsModel;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Repositories.MySQLRepositories.MySQLProjectsRepository;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Services.CreateDescriptionSagaProducer;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Services.CreateProjectUserProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateProjectsUseCase {
    @Autowired
    MySQLProjectsRepository repository;

    @Autowired
    CreateProjectUserProducer producer;

    @Autowired
    CreateDescriptionSagaProducer sagaProducer;

    public BaseResponse run(CreateProjectsRequest request) throws JsonProcessingException, InterruptedException {
        String desciption = sagaProducer.run(request.getDescription());
        Projects project = new Projects(request.getName(), desciption);
        BaseResponse response = repository.addProject(project);
        if (response.getSuccess()){
            long id = 0;
            Object data = response.getData();
            if (data instanceof ProjectsModel) {
                ProjectsModel model = (ProjectsModel) data;
                id = model.getId();
            }
            String payload = "{\"user_id\": " + request.getUserId() + ", \"project_id\":" + id + "}";
            producer.sendCreateProjectUser(payload);
        }
        return response;
    }
}