package com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.MeasurementsUseCase;

import com.example.projectsmanagement.src.ProjectsManagement.Domain.Entity.Measurements;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.CreateMeasurementRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Repositories.MySQLRepositories.MySQLMeasurementsRepository;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Services.CreateDescriptionSagaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateMeasurementsUseCase {
    @Autowired
    MySQLMeasurementsRepository repository;

    @Autowired
    CreateDescriptionSagaProducer sagaProducer;

    public BaseResponse run(CreateMeasurementRequest request) throws JsonProcessingException, InterruptedException {
        String description = sagaProducer.run(request.getNotes());
        Measurements measurement = new Measurements(
                request.getStation(),
                request.getMinus(),
                request.getFixedLevel(),
                request.getPlus(),
                description,
                request.getProjectId()
        );
        return repository.createMeasurement(measurement);
    }
}