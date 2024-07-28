package com.example.projectsmanagement.src.ProjectsManagement.Domain.Ports;


import com.example.projectsmanagement.src.ProjectsManagement.Domain.Entity.Measurements;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;

public interface MeasurementsPort {
    BaseResponse createMeasurement(Measurements measurement);
    BaseResponse listMeasurementsByProjectId(Long projectId);
    BaseResponse getMeasurementByUuid(String measurementUuid);
    BaseResponse updateMeasurement(
            String measurementUuid,
            float minus,
            float fixedLevel,
            float plus,
            float height,
            String notes
    );
    BaseResponse deleteMeasurement(String measurementUuid);

}