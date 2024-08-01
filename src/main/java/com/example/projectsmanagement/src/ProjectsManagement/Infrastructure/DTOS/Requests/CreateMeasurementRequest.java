package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateMeasurementRequest {
    private String station;
    private float minus;
    private float fixedLevel;
    private float plus;
    private String notes;
    private Long projectId;
}
