package com.example.projectsmanagement.src.ProjectsManagement.Domain.Ports;


import com.example.projectsmanagement.src.ProjectsManagement.Domain.Entity.Projects;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;

import java.util.List;

public interface ProjectsPort {
    BaseResponse getProjectsById(Long id);
    BaseResponse listProjects();
    BaseResponse getProjectsByIdList(List<Long> id);
    BaseResponse getProjectsByUuid(String uuid);
    BaseResponse addProject(Projects project);
    BaseResponse updateProject(String projectUuid, String name, String description);
    BaseResponse deleteProject(String uuid);
}