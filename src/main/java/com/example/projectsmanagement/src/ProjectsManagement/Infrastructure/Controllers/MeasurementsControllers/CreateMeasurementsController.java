package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.MeasurementsControllers;

import com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.MeasurementsUseCase.CreateMeasurementsUseCase;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.CreateMeasurementRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateMeasurementsController {
    @Autowired
    CreateMeasurementsUseCase useCase;

    public BaseResponse run(CreateMeasurementRequest request) throws JsonProcessingException, InterruptedException {return useCase.run(request);}
}