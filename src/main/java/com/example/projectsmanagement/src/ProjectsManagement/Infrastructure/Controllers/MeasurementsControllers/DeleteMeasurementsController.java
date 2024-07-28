package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.MeasurementsControllers;


import com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.MeasurementsUseCase.DeleteMeasurementsUseCase;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteMeasurementsController {
    @Autowired
    DeleteMeasurementsUseCase useCase;

    public BaseResponse run(String uuid){return useCase.run(uuid);}
}