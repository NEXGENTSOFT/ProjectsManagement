package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Routes;

import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Controllers.MeasurementsControllers.*;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.CreateMeasurementRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Requests.UpdateMeasurementRequest;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Services.AmazonS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/measurements")
public class MeasurementsRoute {
    @Autowired
    GetMeasurementsController getController;

    @Autowired
    ListMeasurementsController listController;

    @Autowired
    CreateMeasurementsController createController;

    @Autowired
    UpdateMeasurementsController updateController;

    @Autowired
    DeleteMeasurementsController deleteController;

    @Autowired
    AmazonS3Service s3Service;

    @PostMapping
    public BaseResponse create(@RequestBody CreateMeasurementRequest request){ return createController.run(request); }

    @GetMapping("/find/{uuid}")
    public BaseResponse get(@PathVariable String uuid){ return getController.run(uuid); }

    @GetMapping("/list/{id}")
    public BaseResponse list(@PathVariable Long id){ return listController.run(id); }

    @PutMapping
    public BaseResponse update(@RequestBody UpdateMeasurementRequest request){ return updateController.run(request); }

    @DeleteMapping("/{uuid}")
    public BaseResponse delete(@PathVariable String uuid){ return deleteController.run(uuid); }

}