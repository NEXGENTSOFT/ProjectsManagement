package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Routes;

import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.PhotosControllers.CreatePhotosController;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.PhotosControllers.DeletePhotosController;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.PhotosControllers.GetPhotosController;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.PhotosControllers.ListPhotosByProjectController;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.DeleteFileRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photos")
public class PhotosRoute {
    @Autowired
    GetPhotosController getController;

    @Autowired
    ListPhotosByProjectController listController;

    @Autowired
    CreatePhotosController createController;

    @Autowired
    DeletePhotosController deleteController;

    @GetMapping("/{uuid}")
    public BaseResponse getPhoto(@PathVariable String uuid){return getController.run(uuid);}

    @GetMapping("/project/{id}")
    public BaseResponse getPhotosByProject(@PathVariable Long id){return listController.run(id);}

    @PostMapping
    public BaseResponse createPhoto(@RequestParam MultipartFile file, @RequestParam String requestString) throws JsonProcessingException {return createController.run(requestString,file);}

    @DeleteMapping
    public BaseResponse deletePhoto(@RequestBody DeleteFileRequest request){return deleteController.run(request);}
}