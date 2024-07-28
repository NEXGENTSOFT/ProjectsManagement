package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.DrawsControllers;

import com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.DrawsUseCase.GetDrawsUseCase;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetDrawsController {
    @Autowired
    GetDrawsUseCase useCase;

    public BaseResponse run(String uuid){
        return useCase.run(uuid);
    }
}
