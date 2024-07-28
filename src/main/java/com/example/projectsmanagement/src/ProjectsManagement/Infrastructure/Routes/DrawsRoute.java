package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Routes;

import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.DrawsControllers.*;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.DeleteFileRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/draws")
public class DrawsRoute {
    @Autowired
    GetDrawsController getController;

    @Autowired
    ListDrawsByProjectController listController;

    @Autowired
    CreateDrawsController createController;

    @Autowired
    UpdateDrawsController updateController;

    @Autowired
    DeleteDrawsController deleteController;

    @GetMapping("/{uuid}")
    public BaseResponse getDraws(@PathVariable String uuid){return getController.run(uuid);}

    @GetMapping("/projects/{id}")
    public BaseResponse getDrawsByProject(@PathVariable Long id){return listController.run(id);}

    @PostMapping
    public BaseResponse createDraw(@RequestParam MultipartFile file, @RequestParam String requestString) throws JsonProcessingException {
        return createController.run(requestString, file);
    }

    @PutMapping
    public BaseResponse updateDraws(@RequestParam MultipartFile file, @RequestParam String requestString) throws JsonProcessingException {
        return updateController.run(requestString, file);
    }

    @DeleteMapping
    public BaseResponse deleteDraws(@RequestBody DeleteFileRequest request) {return deleteController.run(request);}
}
