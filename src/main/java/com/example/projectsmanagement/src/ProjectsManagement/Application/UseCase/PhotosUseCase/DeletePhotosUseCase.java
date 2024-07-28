package com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.PhotosUseCase;

import com.amazonaws.services.s3.AmazonS3Client;
import com.example.projectsmanagement.src.ProjectsManagement.Domain.Ports.PhotosPort;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.DeleteFileRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Services.AmazonS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeletePhotosUseCase {
    @Autowired
    PhotosPort port;

    @Autowired
    AmazonS3Service s3Service;

    public BaseResponse run(DeleteFileRequest request){
        s3Service.deleteFileByUrl(request.getFileUrl());
        return port.deletePhoto(request.getFileUuid());
    }
}