package com.example.projectsmanagement.src.ProjectsManagement.Domain.Entity;

import lombok.Getter;
import java.util.UUID;

@Getter
public class Measurements {
    private String uuid;
    private String station;
    private float minus;
    private float fixedLevel;
    private float plus;
    private float height;
    private String notes;
    private Long projectId;

    public Measurements(String station, float minus, float fixedLevel, float plus, String notes, Long projectId) {
        this.uuid = UUID.randomUUID().toString();
        this.station = station;
        this.minus = minus;
        this.fixedLevel = fixedLevel;
        this.plus = plus;
        this.height = fixedLevel - minus + plus;
        this.notes = notes;
        this.projectId = projectId;
    }
}