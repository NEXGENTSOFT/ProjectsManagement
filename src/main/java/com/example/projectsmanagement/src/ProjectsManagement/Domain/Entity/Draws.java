package com.example.projectsmanagement.src.ProjectsManagement.Domain.Entity;

import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Exceptions.TypeException;
import lombok.Getter;
import java.util.UUID;

@Getter
public class Draws {
    private String uuid;
    private String title;
    private String url;
    private Long projectId;

    public Draws(String title, String url, Long projectId) {
        validateTitle(title);
        validateUrl(url);
        this.uuid = UUID.randomUUID().toString();
        this.title = title;
        this.url = url;
        this.projectId = projectId;
    }

    private void validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new TypeException("Title");
        }
    }

    private void validateUrl(String url) {
        if (url == null || url.isEmpty()) {
            throw new TypeException("URL");
        }
    }
}