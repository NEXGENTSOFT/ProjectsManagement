package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "measurements")
@Getter @Setter
public class MeasurementsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String uuid;

    @Column(nullable = false)
    private Long projectId;

    @Column(nullable = false)
    private String station;

    @Column(nullable = false)
    private float minus;

    @Column(nullable = false)
    private float fixedLevel;

    @Column(nullable = false)
    private float plus;

    @Column(nullable = true)
    private float height;

    @Column(nullable = true)
    private String note;
}
