package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Exceptions;

public class NotDeleteExeption extends RuntimeException {
    public NotDeleteExeption(String object) {
        super("Error: " + object + " was not deleted");
    }
}
