package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DeleteFileRequest {
    private String fileUuid;
    private String fileUrl;
}
