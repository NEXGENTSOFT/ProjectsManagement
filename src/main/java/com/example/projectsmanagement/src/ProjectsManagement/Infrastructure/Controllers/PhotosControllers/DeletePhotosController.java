package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.PhotosControllers;

import com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.PhotosUseCase.DeletePhotosUseCase;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.DeleteFileRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeletePhotosController {
    @Autowired
    DeletePhotosUseCase useCase;

    public BaseResponse run(DeleteFileRequest request){
        return useCase.run(request);
    }
}