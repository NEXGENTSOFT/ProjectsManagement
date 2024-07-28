package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.ProjectsControllers;

import com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.ProjectsUseCase.GetProjectsUseCase;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetProjectsController {
    @Autowired
    GetProjectsUseCase useCase;
    
    public BaseResponse getProjects(Long id){ return useCase.run(id); }
}