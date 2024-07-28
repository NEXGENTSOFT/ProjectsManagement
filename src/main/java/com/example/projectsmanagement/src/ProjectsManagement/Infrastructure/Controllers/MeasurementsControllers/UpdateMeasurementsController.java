package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.MeasurementsControllers;

import com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.MeasurementsUseCase.UpdateMeasurementsUseCase;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.UpdateMeasurementRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateMeasurementsController {
    @Autowired
    UpdateMeasurementsUseCase useCase;

    public BaseResponse run(UpdateMeasurementRequest request){ return useCase.run(request); }
}