package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateProjectRequest {
    private String uuid;
    private String name;
    private String description;
}
