package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Repositories.MySQLRepositories;

import com.example.projectsmanagement.src.ProjectsManagement.Domain.Entity.Photos;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Models.PhotosModel;
import com.example.projectsmanagement.src.ProjectsManagement.Domain.Ports.PhotosPort;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Repositories.MySQLRepositories.JPA.IMySQLPhotosRepository;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MySQLPhotosRepository implements PhotosPort {

    @Autowired
    IMySQLPhotosRepository repository;

    @Override
    public BaseResponse createPhoto(Photos photo) {
        try {
            return from(repository.save(from(photo)), "Photo created successfully", true, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            throw new NotCreateException("Photo");
        }
    }

    @Override
    public BaseResponse getPhotosByProjecId(Long projectId) {
        List<PhotosModel> photos = repository.findAllByProjectId(projectId);
        if (photos.isEmpty()) {
            throw new NotFoundException("Photo");
        }
        return from(photos, "Photos found successfully", true, HttpStatus.FOUND);
    }

    @Override
    public BaseResponse getPhotoByPhotoUuid(String photoUuid) {
        PhotosModel photo = repository.findByUuid(photoUuid);
        if (photo == null) {
            throw new NotFoundException("Photo");
        }
        return from(photo, "Photo found successfully", true, HttpStatus.OK);
    }

    @Override
    public BaseResponse deletePhoto(String photoUuid) {
        try {
            PhotosModel photo = repository.findByUuid(photoUuid);
            if (photo == null) {
                throw new NotFoundException("Photo");
            }
            repository.delete(photo);
            return BaseResponse.builder()
                    .message("Photo deleted successfully")
                    .success(true)
                    .httpStatus(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            throw new NotDeleteExeption("Photo");
        }
    }

    private PhotosModel from(Photos photo) {
        PhotosModel model = new PhotosModel();
        model.setUuid(photo.getUuid());
        model.setName(photo.getName());
        model.setProjectId(photo.getProjectId());
        model.setUrl(photo.getUrl());
        return model;
    }

    private BaseResponse from(PhotosModel model, String message, boolean success, HttpStatus status) {
        return BaseResponse.builder()
                .data(model)
                .message(message)
                .success(success)
                .httpStatus(status)
                .build();
    }

    private BaseResponse from(List<PhotosModel> models, String message, boolean success, HttpStatus status) {
        return BaseResponse.builder()
                .data(models)
                .message(message)
                .success(success)
                .httpStatus(status)
                .build();
    }
}
