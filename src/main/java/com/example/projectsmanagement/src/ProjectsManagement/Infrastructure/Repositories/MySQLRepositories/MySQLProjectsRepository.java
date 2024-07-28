package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Repositories.MySQLRepositories;

import com.example.projectsmanagement.src.ProjectsManagement.Domain.Entity.Projects;
import com.example.projectsmanagement.src.ProjectsManagement.Domain.Ports.ProjectsPort;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Models.ProjectsModel;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Repositories.MySQLRepositories.JPA.IMySQLProjectsRepository;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MySQLProjectsRepository implements ProjectsPort {
    @Autowired
    IMySQLProjectsRepository repository;

    @Override
    public BaseResponse getProjectsById(Long id) {
        ProjectsModel projectsModel = repository.getProjectsById(id);
        if (projectsModel == null) {
            throw new NotFoundException("Project");
        }
        return from(projectsModel, "Project found successfully", true, HttpStatus.FOUND);
    }

    @Override
    public BaseResponse listProjects() {
        List<ProjectsModel> projects = repository.findAll();
        if (projects.isEmpty()) {
            throw new NotFoundException("Project");
        }
        return from(projects, "Projects found successfully", true, HttpStatus.FOUND);
    }

    @Override
    public BaseResponse getProjectsByIdList(List<Long> id){
        try {
            List<ProjectsModel> projects = repository.findAllById(id);
            if (projects.isEmpty()) {
                throw new NotFoundException("Project");
            }
            return from(projects, "Projects found successfully", true, HttpStatus.FOUND);
        } catch (Exception e){
            throw new NotFoundException("Project");
        }
    }

    @Override
    public BaseResponse getProjectsByUuid(String uuid) {
        ProjectsModel projectsModel = repository.getProjectsByUuid(uuid);
        if (projectsModel == null) {
            throw new NotFoundException("Project");
        }
        return from(projectsModel, "Project found successfully", true, HttpStatus.FOUND);
    }

    @Override
    public BaseResponse addProject(Projects project) {
        try {
            ProjectsModel createdModel = repository.save(from(project));
            return from(createdModel, "Project added successfully", true, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            throw new NotCreateException("Project");
        }
    }

    @Override
    public BaseResponse updateProject(String projectUuid, String name, String description) {
        try {
            ProjectsModel model = repository.getProjectsByUuid(projectUuid);
            if (model == null) {
                throw new NotFoundException("Project");
            }
            model.setName(name);
            model.setDescription(description);
            return from(repository.save(model), "Project updated successfully", true, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            throw new NotUpdateException("Project");
        }
    }

    @Override
    public BaseResponse deleteProject(String uuid) {
        try {
            ProjectsModel model = repository.getProjectsByUuid(uuid);
            if (model == null) {
                throw new NotFoundException("Project");
            }
            repository.delete(model);
            return BaseResponse.builder()
                    .message("Project deleted successfully")
                    .success(true)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }catch (Exception e) {
            throw new NotDeleteExeption("Project");
        }
    }

    ProjectsModel from(Projects project) {
        ProjectsModel model = new ProjectsModel();
        model.setUuid(project.getUuid());
        model.setName(project.getName());
        model.setCompleted(project.isCompleted());
        model.setDescription(project.getDescription());
        return model;
    }

    BaseResponse from(ProjectsModel model, String message, boolean success, HttpStatus status) {
        return BaseResponse.builder()
                .data(model)
                .message(message)
                .success(success)
                .httpStatus(status)
                .build();
    }

    BaseResponse from(List<ProjectsModel> models, String message, boolean success, HttpStatus status) {
        return BaseResponse.builder()
                .data(models)
                .message(message)
                .success(success)
                .httpStatus(status)
                .build();
    }
}
