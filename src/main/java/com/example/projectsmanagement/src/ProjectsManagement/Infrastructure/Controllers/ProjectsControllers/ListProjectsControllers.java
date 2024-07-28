package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.ProjectsControllers;

import com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.ProjectsUseCase.ListProjectsUseCase;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListProjectsControllers {
    @Autowired
    ListProjectsUseCase useCase;

    public BaseResponse run(){
        return useCase.run();
    }
}
