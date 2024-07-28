package com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.DrawsUseCase;

import com.example.projectsmanagement.src.ProjectsManagement.Domain.Ports.DrawsPort;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.UpdateDrawsRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Services.AmazonS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UpdateDrawsUseCase {
    @Autowired
    DrawsPort port;

    @Autowired
    AmazonS3Service s3Service;

    public BaseResponse run(UpdateDrawsRequest request, MultipartFile file){
        String uuid = request.getDrawUuid();
        String title = request.getTitle();
        String url = request.getUrl();
        String newUrl = s3Service.updateFileByUrl(file, url);
        return port.updateDraw(uuid, title, newUrl);
    }
}