package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Exceptions;

public class NotUpdateException extends RuntimeException {
    public NotUpdateException(String object) {
        super("Error while updating object " + object);
    }
}
