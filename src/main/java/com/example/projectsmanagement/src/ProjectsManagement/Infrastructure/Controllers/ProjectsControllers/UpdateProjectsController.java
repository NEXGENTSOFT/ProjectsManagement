package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.ProjectsControllers;

import com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.ProjectsUseCase.UpdateProjectsUseCase;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.UpdateProjectRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProjectsController {
    @Autowired
    UpdateProjectsUseCase useCase;
    
    public BaseResponse run(UpdateProjectRequest request) throws JsonProcessingException, InterruptedException { return useCase.run(request); }
}