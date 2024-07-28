package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Repositories.MySQLRepositories.JPA;

import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Models.MeasurementsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMySQLMeasurementsRepository extends JpaRepository<MeasurementsModel, Long> {
    @Query(value = "SELECT * FROM measurements WHERE project_id = :projectId ;", nativeQuery = true)
    List<MeasurementsModel> findAllByProjectId(Long projectId);

    @Query(value = "SELECT * FROM measurements WHERE uuid = :uuid ;", nativeQuery = true)
    MeasurementsModel findByUuid(String uuid);
}
