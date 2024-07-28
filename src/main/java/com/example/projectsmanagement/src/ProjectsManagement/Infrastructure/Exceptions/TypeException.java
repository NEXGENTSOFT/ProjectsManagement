package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Exceptions;

public class TypeException extends RuntimeException {
    public TypeException(String object) {
        super("Type Error: " + object + " is incorrect data type");
    }
}
