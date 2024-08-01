package com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.MeasurementsUseCase;

import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.UpdateMeasurementRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Repositories.MySQLRepositories.MySQLMeasurementsRepository;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Services.CreateDescriptionSagaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateMeasurementsUseCase {
    @Autowired
    MySQLMeasurementsRepository repository;

    @Autowired
    CreateDescriptionSagaProducer sagaProducer;

    public BaseResponse run(UpdateMeasurementRequest request) throws JsonProcessingException, InterruptedException {

        String measurementUuid = request.getMeasurementUuid();
        float minus = request.getMinus();
        float fixedLevel = request.getFixedLevel();
        float plus = request.getPlus();
        float height = fixedLevel - minus + plus;
        String notes = sagaProducer.run(request.getNotes());

        return repository.updateMeasurement(measurementUuid, minus, fixedLevel, plus, height, notes);
    }
}