package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.PhotosControllers;

import com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.PhotosUseCase.CreatePhotosUseCase;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.CreatePhotosRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Utilities.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CreatePhotosController {
    @Autowired
    CreatePhotosUseCase useCase;

    JsonUtil jsonUtil = new JsonUtil();

    public BaseResponse run(String requestString, MultipartFile file) throws JsonProcessingException {
        CreatePhotosRequest request = jsonUtil.deserialize(requestString, CreatePhotosRequest.class);
        return useCase.run(request, file);
    }
}