package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.DrawsControllers;

import com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.DrawsUseCase.CreateDrawsUseCase;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.CreateDrawRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Utilities.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CreateDrawsController {
    @Autowired
    CreateDrawsUseCase useCase;


    JsonUtil jsonUtil = new JsonUtil();

    public BaseResponse run(String requestString, MultipartFile file) throws JsonProcessingException {
        CreateDrawRequest request = jsonUtil.deserialize(requestString, CreateDrawRequest.class);
        return useCase.run(request, file);
    }
}
