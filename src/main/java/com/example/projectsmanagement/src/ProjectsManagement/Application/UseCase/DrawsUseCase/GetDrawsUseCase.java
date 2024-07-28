package com.example.projectsmanagement.src.ProjectsManagement.Application.UseCase.DrawsUseCase;

import com.example.projectsmanagement.src.ProjectsManagement.Domain.Ports.DrawsPort;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetDrawsUseCase {

    @Autowired
    DrawsPort port;

    public BaseResponse run(String uuid){
        return port.getDrawByUuid(uuid);
    }
}
