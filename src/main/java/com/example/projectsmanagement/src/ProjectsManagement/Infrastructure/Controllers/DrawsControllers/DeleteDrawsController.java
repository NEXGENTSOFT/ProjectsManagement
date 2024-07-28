package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.DrawsControllers;

import com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.DrawsUseCase.DeleteDrawsUseCase;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.DeleteFileRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteDrawsController {
    @Autowired
    DeleteDrawsUseCase useCase;

    public BaseResponse run(DeleteFileRequest request){
        return useCase.run(request);
    }
}