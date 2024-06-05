package com.projetoextesao.volunteerWork.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OngDto {
    @Getter
    @NotNull
    @Size(min = 5, max = 200)
    private String ongName;
    @Getter
    @NotNull
    @Size(min = 5, max = 100)
    private String ongMail;
    @Getter
    @NotNull
    @Size(min = 5, max = 100)
    private String ongPwd;
    @NotNull
    private boolean isActive;
    @Getter
    @NotNull
    private String createdBy;

    public void setOngName(String ongName) {
        this.ongName = ongName;
    }

    public void setOngMail(String ongMail) {
        this.ongMail = ongMail;
    }

    public void setOngPwd(String ongPwd) {
        this.ongPwd = ongPwd;
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