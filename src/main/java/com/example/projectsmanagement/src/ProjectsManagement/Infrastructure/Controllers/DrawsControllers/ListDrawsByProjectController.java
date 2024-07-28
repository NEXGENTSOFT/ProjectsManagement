package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.DrawsControllers;

import com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.DrawsUseCase.ListDrawByProjectIdUseCase;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListDrawsByProjectController {
    @Autowired
    ListDrawByProjectIdUseCase useCase;

    public BaseResponse run(Long projectId){
        return useCase.run(projectId);
    }
}
