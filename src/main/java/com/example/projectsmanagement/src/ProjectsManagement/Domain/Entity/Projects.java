package com.example.projectsmanagement.src.ProjectsManagement.Domain.Entity;

import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Exceptions.TypeException;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Projects {
    private String uuid;
    private String name;
    private String description;
    private boolean completed;

    public Projects(String name, String description) {
        validateName(name);
        validateDescription(description);
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.completed = false;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new TypeException("Name");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new TypeException("Description");
        }
    }
}
