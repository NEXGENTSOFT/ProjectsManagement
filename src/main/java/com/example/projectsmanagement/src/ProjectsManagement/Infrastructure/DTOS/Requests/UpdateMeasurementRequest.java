package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateMeasurementRequest {
    private String measurementUuid;
    private float minus;
    private float fixedLevel;
    private float plus;
    private float height;
    private String notes;
}
