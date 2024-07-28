package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Repositories.MySQLRepositories.JPA;

import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Models.PhotosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMySQLPhotosRepository extends JpaRepository<PhotosModel, Long> {
    @Query(value = "SELECT * FROM photos WHERE project_id = :projectId ;", nativeQuery = true)
    List<PhotosModel> findAllByProjectId(Long projectId);

    @Query(value = "SELECT * FROM photos WHERE uuid = :uuid ;", nativeQuery = true)
    PhotosModel findByUuid(String uuid);
}
