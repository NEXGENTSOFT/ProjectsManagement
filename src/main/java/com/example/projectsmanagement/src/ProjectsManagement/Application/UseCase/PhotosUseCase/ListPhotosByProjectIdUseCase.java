package com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.PhotosUseCase;

import com.example.projectsmanagement.src.ProjectsManagement.Domain.Ports.PhotosPort;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListPhotosByProjectIdUseCase {
    @Autowired
    PhotosPort port;

    public BaseResponse run(Long projectId){
        return port.getPhotosByProjecId(projectId);
    }
}
