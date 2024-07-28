package com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.MeasurementsUseCase;

import com.example.projectsmanagement.src.ProjectsManagement.Domain.Entity.Measurements;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.CreateMeasurementRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Repositories.MySQLRepositories.MySQLMeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateMeasurementsUseCase {
    @Autowired
    MySQLMeasurementsRepository repository;

    public BaseResponse run(CreateMeasurementRequest request){
        Measurements measurement = new Measurements(
                request.getStation(),
                request.getMinus(),
                request.getFixedLevel(),
                request.getPlus(),
                request.getHeight(),
                request.getNotes(),
                request.getProjectId()
        );
        return repository.createMeasurement(measurement);
    }
}