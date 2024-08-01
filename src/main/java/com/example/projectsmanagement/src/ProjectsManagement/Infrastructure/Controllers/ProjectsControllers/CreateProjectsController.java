package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.ProjectsControllers;

import com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.ProjectsUseCase.CreateProjectsUseCase;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.CreateProjectsRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProjectsController {
    @Autowired
    CreateProjectsUseCase useCase;
    
    public BaseResponse run(CreateProjectsRequest request) throws JsonProcessingException, InterruptedException { return useCase.run(request); }
}