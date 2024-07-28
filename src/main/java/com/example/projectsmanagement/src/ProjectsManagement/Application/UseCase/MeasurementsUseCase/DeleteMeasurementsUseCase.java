package com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.MeasurementsUseCase;

import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Repositories.MySQLRepositories.MySQLMeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteMeasurementsUseCase {
    @Autowired
    MySQLMeasurementsRepository repository;

    public BaseResponse run(String uuid) {
        return repository.deleteMeasurement(uuid);
    }
}