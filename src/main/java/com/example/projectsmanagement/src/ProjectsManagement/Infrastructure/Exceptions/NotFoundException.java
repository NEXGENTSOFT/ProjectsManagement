package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String object) {
        super("Error: "+ object + " not found");
    }
}
