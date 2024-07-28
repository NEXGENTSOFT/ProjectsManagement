package com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.ProjectsUseCase;

import com.example.projectsmanagement.src.ProjectsManagement.Domain.Ports.ProjectsPort;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetProjectsByIdListUseCase {
    @Autowired
    private ProjectsPort projectsPort;

    public BaseResponse run(List<Long> idList){
        return projectsPort.getProjectsByIdList(idList);
    }
}
