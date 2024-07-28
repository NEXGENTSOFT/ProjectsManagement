package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.PhotosControllers;

import com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.PhotosUseCase.GetPhotosUseCase;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPhotosController {
    @Autowired
    GetPhotosUseCase useCase;

    public BaseResponse run(String uuid){
        return useCase.run(uuid);
    }
}