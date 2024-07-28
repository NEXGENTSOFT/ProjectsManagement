package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Repositories.MySQLRepositories.JPA;

import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Models.DrawsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMySQLDrawsRepository extends JpaRepository<DrawsModel, Long> {
    @Query(value = "SELECT * FROM draws WHERE project_id = :projectId ;", nativeQuery = true)
    List<DrawsModel> findAllByProjectId(Long projectId);

    @Query(value = "SELECT * FROM draws WHERE uuid = :uuid ;", nativeQuery = true)
    DrawsModel findByUuid(String uuid);
}
