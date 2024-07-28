package com.example.projectsmanagement.src.ProjectsManagement.Domain.Entity;

import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Exceptions.TypeException;
import lombok.Getter;
import java.util.UUID;

@Getter
public class Photos {
    private String uuid;
    private String name;
    private String url;
    private Long projectId;

    public Photos(String name, String url, Long projectId) {
        validateName(name);
        validateUrl(url);
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.url = url;
        this.projectId = projectId;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new TypeException("Title");
        }
    }

    private void validateUrl(String url) {
        if (url == null || url.isEmpty()) {
            throw new TypeException("URL");
        }
    }
}