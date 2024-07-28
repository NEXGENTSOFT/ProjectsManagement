package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Repositories.MySQLRepositories;

import com.example.projectsmanagement.src.ProjectsManagement.Domain.Entity.Draws;
import com.example.projectsmanagement.src.ProjectsManagement.Domain.Ports.DrawsPort;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Repositories.MySQLRepositories.JPA.IMySQLDrawsRepository;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Models.DrawsModel;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MySQLDrawsRepository implements DrawsPort {

    @Autowired
    IMySQLDrawsRepository repository;

    @Override
    public BaseResponse createDraw(Draws draw) {
        try {
            DrawsModel createdModel = repository.save(from(draw));
            return from(createdModel, "Draw created successfully", true, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            throw new NotCreateException("Draw");
        }
    }

    @Override
    public BaseResponse listDrawsByProjectId(Long projectId) {
        List<DrawsModel> draws = repository.findAllByProjectId(projectId);
        if (draws.isEmpty()) {
            throw new NotFoundException("Draw");
        }
        return from(draws, "Draw list successfully", true, HttpStatus.OK);
    }

    @Override
    public BaseResponse getDrawByUuid(String drawUuid) {
        DrawsModel draw = repository.findByUuid(drawUuid);
        if (draw == null) {
            throw new NotFoundException("Draw");
        }
        return from(draw, "Draw found successfully", true, HttpStatus.OK);
    }

    @Override
    public BaseResponse updateDraw(String drawUuid, String title, String newUrl) {
        try {
            DrawsModel model = repository.findByUuid(drawUuid);
            if (model == null) {
                throw new NotFoundException("Draw");
            }
            model.setTitle(title);
            model.setUrl(newUrl);
            repository.save(model);
            return from(model, "Draw updated successfully", true, HttpStatus.OK);
        } catch (Exception e){
            throw new NotUpdateException("Draw");
        }
    }

    @Override
    public BaseResponse deleteDraw(String drawUuid) {
        try {
            DrawsModel draw = repository.findByUuid(drawUuid);
            if (draw == null) {
                throw new NotFoundException("Draw");
            }
            repository.delete(draw);
            return BaseResponse.builder()
                    .message("Draw deleted successfully")
                    .success(true)
                    .httpStatus(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            throw new NotDeleteExeption("Draw");
        }
    }

    private DrawsModel from(Draws draw) {
        DrawsModel model = new DrawsModel();
        model.setUuid(draw.getUuid());
        model.setTitle(draw.getTitle());
        model.setUrl(draw.getUrl());
        model.setProjectId(draw.getProjectId());
        return model;
    }

    private BaseResponse from(DrawsModel model, String message, boolean success, HttpStatus status) {
        return BaseResponse.builder()
                .data(model)
                .message(message)
                .success(success)
                .httpStatus(status)
                .build();
    }

    private BaseResponse from(List<DrawsModel> models, String message, boolean success, HttpStatus status) {
        return BaseResponse.builder()
                .data(models)
                .message(message)
                .success(success)
                .httpStatus(status)
                .build();
    }
}
