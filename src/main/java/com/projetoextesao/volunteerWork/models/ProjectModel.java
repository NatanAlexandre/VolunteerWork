package com.projetoextesao.volunteerWork.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = ProjectModel.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProjectModel {

    public static final String TABLE_NAME = "TB_PROJECTS";
    public static final long serialVersionUID = 1L;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ProjectId", unique = true, nullable = false)
    private UUID projectId;

    @Getter
    @Column(name = "ProjectName", unique = true, nullable = false, length = 200)
    private String projectName;

    @Getter
    @Column(name = "ProjectLocation", nullable = false, length = 200)
    private String projectLocation;

    @Getter
    @Column(name = "ProjectDescription", nullable = false, length = 600)
    private String projectDescription;

    @Getter
    @Column(name = "OngId", nullable = false, unique = true)
    private String ongId;

    @Column(name = "IsActive")
    private boolean isActive;

    @Getter
    @Column(name = "CreatedBy", nullable = false, length = 200)
    private String createdBy;

    @Getter
    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt;

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectLocation(String projectLocation) {
        this.projectLocation = projectLocation;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public void setOngId(String ongId) {
        this.ongId = ongId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
