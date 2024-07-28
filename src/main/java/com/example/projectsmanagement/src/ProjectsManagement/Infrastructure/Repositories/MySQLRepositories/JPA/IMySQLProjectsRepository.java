package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Repositories.MySQLRepositories.JPA;

import com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Models.ProjectsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IMySQLProjectsRepository extends JpaRepository<ProjectsModel, Long> {
    @Query(value = "SELECT * FROM projects WHERE id = :id ;", nativeQuery = true)
    ProjectsModel getProjectsById(Long id);

    @Query(value = "SELECT * FROM projects WHERE id = :uuid ;", nativeQuery = true)
    ProjectsModel getProjectsByUuid(String uuid);
}
