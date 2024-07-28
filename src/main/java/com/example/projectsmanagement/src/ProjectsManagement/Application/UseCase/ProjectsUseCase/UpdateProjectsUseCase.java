package com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.ProjectsUseCase;

import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.UpdateProjectRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Repositories.MySQLRepositories.MySQLProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateProjectsUseCase {
    @Autowired
    MySQLProjectsRepository repository;

    public BaseResponse run(UpdateProjectRequest request){
        return repository.updateProject(request.getUuid(), request.getName(), request.getDescription());
    }
}