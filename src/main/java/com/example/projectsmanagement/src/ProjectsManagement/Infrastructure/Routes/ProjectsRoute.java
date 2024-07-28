package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Routes;

import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.ProjectsControllers.*;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.CreateProjectsRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.UpdateProjectRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectsRoute {
    @Autowired
    CreateProjectsController createController;

    @Autowired
    ListProjectsControllers listController;

    @Autowired
    GetProjectsByUuidController getByUuidController;

    @Autowired
    UpdateProjectsController updateController;

    @Autowired
    DeleteProjectsController deleteController;

    @PostMapping
    public BaseResponse createProject(@RequestBody CreateProjectsRequest request ){ return createController.run(request); }

    @GetMapping
    public BaseResponse getAllProjects() { return listController.run(); }

    @GetMapping("/{uuid}")
    public BaseResponse getProjectByUuid(@PathVariable String uuid){ return  getByUuidController.run(uuid); }

    @PutMapping
    public BaseResponse updateProject(@RequestBody UpdateProjectRequest request){ return updateController.run(request); }

    @DeleteMapping("/{uuid}")
    public BaseResponse deleteProject(@PathVariable String uuid){ return deleteController.run(uuid); }
}