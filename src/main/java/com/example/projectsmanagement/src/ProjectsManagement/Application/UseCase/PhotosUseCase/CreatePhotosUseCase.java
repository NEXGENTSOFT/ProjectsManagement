package com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.PhotosUseCase;

import com.example.projectsmanagement.src.ProjectsManagement.Domain.Entity.Photos;
import com.example.projectsmanagement.src.ProjectsManagement.Domain.Ports.PhotosPort;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.CreatePhotosRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Services.AmazonS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CreatePhotosUseCase {
    @Autowired
    PhotosPort port;

    @Autowired
    AmazonS3Service s3Service;

    public BaseResponse run(CreatePhotosRequest request, MultipartFile file){
        String name = request.getName();
        Long projectId = request.getProjectId();
        String url = s3Service.uploadFile(file, name, "Photos");
        Photos photo = new Photos(name, url, projectId);
        return port.createPhoto(photo);
    }
}