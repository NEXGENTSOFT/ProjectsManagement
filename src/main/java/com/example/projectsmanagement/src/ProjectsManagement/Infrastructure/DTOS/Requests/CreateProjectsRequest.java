package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateProjectsRequest {
    private String name;
    private String description;
    private Long userId;
}
