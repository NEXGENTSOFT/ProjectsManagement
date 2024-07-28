package com.example.projectsmanagement.src.ProjectsManagement.Domain.Ports;


import com.example.projectsmanagement.src.ProjectsManagement.Domain.Entity.Photos;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;

public interface PhotosPort {
    BaseResponse createPhoto(Photos photo);
    BaseResponse getPhotosByProjecId(Long projecId);
    BaseResponse getPhotoByPhotoUuid(String photoUuid);
    BaseResponse deletePhoto(String photoUuid);
}