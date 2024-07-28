package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateDrawsRequest {
    private String drawUuid;
    private String title;
    private String url;
}
