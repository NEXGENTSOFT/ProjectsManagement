package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.PhotosControllers;

import com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.PhotosUseCase.ListPhotosByProjectIdUseCase;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListPhotosByProjectController {
    @Autowired
    ListPhotosByProjectIdUseCase useCase;

    public BaseResponse run(Long projectId){
        return useCase.run(projectId);
    }
}
