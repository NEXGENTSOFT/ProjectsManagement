package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.ProjectsControllers;

import com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.ProjectsUseCase.GetProjectsByIdListUseCase;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GetProjectsByIdListController {
    @Autowired
    private GetProjectsByIdListUseCase useCase;

    public BaseResponse run(List<Long> idList){
        return useCase.run(idList);
    }
}
