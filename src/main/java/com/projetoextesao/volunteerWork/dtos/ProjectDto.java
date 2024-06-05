package com.projetoextesao.volunteerWork.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProjectDto {

    @Getter
    @NotNull
    private String projectName;
    @Getter
    @NotNull
    private String projectLocation;
    @Getter
    @NotNull
    private String projectDescription;
    @Getter
    @NotNull
    private String ongId;
    private boolean isActive;
    @Getter
    @NotNull
    private String createdBy;

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

}
