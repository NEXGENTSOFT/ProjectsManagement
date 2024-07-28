package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class GetProjectsByIdRequest {
    private List<Long> data;
    private String session_uuid;
}
