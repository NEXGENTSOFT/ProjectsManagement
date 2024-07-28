package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Exceptions;

public class NotCreateException extends RuntimeException {
    public NotCreateException(String object) {
        super("Error: " + object + " not created");
    }
}
