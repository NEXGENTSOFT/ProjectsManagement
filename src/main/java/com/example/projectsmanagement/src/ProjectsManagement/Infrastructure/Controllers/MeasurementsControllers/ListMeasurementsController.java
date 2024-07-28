package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.MeasurementsControllers;

import com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.MeasurementsUseCase.ListMeasurementsUseCase;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListMeasurementsController {
    @Autowired
    ListMeasurementsUseCase useCase;

    public BaseResponse run(Long id){return useCase.run(id);}
}
