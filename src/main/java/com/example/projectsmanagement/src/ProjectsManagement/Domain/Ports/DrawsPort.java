package com.example.projectsmanagement.src.ProjectsManagement.Domain.Ports;


import com.example.projectsmanagement.src.ProjectsManagement.Domain.Entity.Draws;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;

public interface DrawsPort {
    BaseResponse createDraw(Draws draw);
    BaseResponse listDrawsByProjectId(Long projectId);
    BaseResponse getDrawByUuid(String drawUuid);
    BaseResponse updateDraw(String drawUuid, String title, String newUrl);
    BaseResponse deleteDraw(String drawUuid);
}