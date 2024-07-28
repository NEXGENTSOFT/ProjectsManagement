package com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.ProjectsUseCase;

import com.example.projectsmanagement.src.ProjectsManagement.Domain.Entity.Projects;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.CreateProjectsRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Models.ProjectsModel;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Repositories.MySQLRepositories.MySQLProjectsRepository;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Services.CreateProjectUserProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateProjectsUseCase {
    @Autowired
    MySQLProjectsRepository repository;

    @Autowired
    CreateProjectUserProducer producer;

    public BaseResponse run(CreateProjectsRequest request){
        Projects project = new Projects(request.getName(), request.getDescription());
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