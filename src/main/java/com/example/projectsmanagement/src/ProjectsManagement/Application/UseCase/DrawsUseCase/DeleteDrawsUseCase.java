package com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.DrawsUseCase;

import com.example.projectsmanagement.src.ProjectsManagement.Domain.Ports.DrawsPort;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.DeleteFileRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Services.AmazonS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteDrawsUseCase {
    @Autowired
    DrawsPort port;

    @Autowired
    AmazonS3Service s3Service;

    public BaseResponse run(DeleteFileRequest request){
        BaseResponse response = port.deleteDraw(request.getFileUuid());
        s3Service.deleteFileByUrl(request.getFileUrl());
        return response;
    }
}
