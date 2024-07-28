package com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.DrawsUseCase;

import com.example.projectsmanagement.src.ProjectsManagement.Domain.Entity.Draws;
import com.example.projectsmanagement.src.ProjectsManagement.Domain.Ports.DrawsPort;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.CreateDrawRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Services.AmazonS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CreateDrawsUseCase {
    @Autowired
    DrawsPort drawsPort;
    
    @Autowired
    AmazonS3Service amazonS3Service;

    public BaseResponse run(CreateDrawRequest request, MultipartFile file){
        String name = request.getTitle();
        Long projectId = request.getProjectId();

        String url = amazonS3Service.uploadFile(file, name, "Draws");

        Draws draw = new Draws(name, url, projectId);

        return drawsPort.createDraw(draw);
    }
}
