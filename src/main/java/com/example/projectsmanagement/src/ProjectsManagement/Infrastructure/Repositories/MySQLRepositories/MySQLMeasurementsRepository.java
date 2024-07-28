package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Repositories.MySQLRepositories;

import com.example.projectsmanagement.src.ProjectsManagement.Domain.Entity.Measurements;
import com.example.projectsmanagement.src.ProjectsManagement.Domain.Ports.MeasurementsPort;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.DTOS.Responses.BaseResponse;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Models.MeasurementsModel;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Repositories.MySQLRepositories.JPA.IMySQLMeasurementsRepository;
import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MySQLMeasurementsRepository implements MeasurementsPort {
    @Autowired
    IMySQLMeasurementsRepository repository;

    @Override
    public BaseResponse createMeasurement(Measurements measurement) {
        try {
            MeasurementsModel createdModel = repository.save(from(measurement));
            return from(createdModel, "Measurement Created Successfully", true, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            throw new NotCreateException("Measurement");
        }
    }

    @Override
    public BaseResponse listMeasurementsByProjectId(Long projectId) {
        List<MeasurementsModel> measurements = repository.findAllByProjectId(projectId);
        if (measurements.isEmpty()) {
            throw new NotFoundException("Measurement");
        }
        return from(measurements, "Measurements found successfully", true, HttpStatus.FOUND);
    }

    @Override
    public BaseResponse getMeasurementByUuid(String measurementUuid) {
        MeasurementsModel model = repository.findByUuid(measurementUuid);
        if (model == null) {
            throw new NotFoundException("Measurement");
        }
        return from(model, "Measurement found successfully", true, HttpStatus.OK);
    }

    @Override
    public BaseResponse updateMeasurement(String measurementUuid, float minus, float fixedLevel, float plus, float height, String notes) {
        try {
            MeasurementsModel model = repository.findByUuid(measurementUuid);
            if (model == null) {
                throw new NotFoundException("Measurement");
            }
            model.setMinus(minus);
            model.setFixedLevel(fixedLevel);
            model.setPlus(plus);
            model.setHeight(height);
            model.setNote(notes);
            repository.save(model);
            return from(model, "Measurement Updated Successfully", true, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            throw new NotUpdateException("Measurement");
        }
    }

    @Override
    public BaseResponse deleteMeasurement(String measurementUuid) {
        try {
            MeasurementsModel model = repository.findByUuid(measurementUuid);
            if (model == null) {
                throw new NotFoundException("Measurement");
            }
            repository.delete(model);
            return BaseResponse.builder()
                    .success(true)
                    .httpStatus(HttpStatus.ACCEPTED)
                    .message("Model deleted successfully").build();
        } catch (Exception e) {
            throw new NotDeleteExeption("Measurement");
        }
    }

    private MeasurementsModel from(Measurements measurement) {
        MeasurementsModel model = new MeasurementsModel();
        model.setPlus(measurement.getPlus());
        model.setNote(measurement.getNotes());
        model.setMinus(measurement.getMinus());
        model.setHeight(measurement.getHeight());
        model.setStation(measurement.getStation());
        model.setUuid(measurement.getUuid());
        model.setFixedLevel(measurement.getFixedLevel());
        model.setProjectId(measurement.getProjectId());
        return model;
    }

    private BaseResponse from(MeasurementsModel model, String message, boolean success, HttpStatus httpStatus){
        return BaseResponse.builder()
                .data(model)
                .message(message)
                .success(success)
                .httpStatus(httpStatus).build();
    }

    private BaseResponse from(List<MeasurementsModel> models, String message, boolean success, HttpStatus httpStatus){
        return BaseResponse.builder()
                .data(models)
                .message(message)
                .success(success)
                .httpStatus(httpStatus).build();
    }
}
