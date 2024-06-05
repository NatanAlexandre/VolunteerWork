package com.projetoextesao.volunteerWork.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = OngModel.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OngModel {
    public static final String TABLE_NAME = "TB_ONGS";
    public static final long serialVersionUID = 1L;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OngId", unique = true)
    private UUID ongId;
    @Getter
    @Column(name = "OngName", unique = true , nullable = false, length = 200)
    private String ongName;
    @Getter
    @Column(name = "OngMail", nullable = false, unique = true, length = 100)
    private String ongMail;
    @Getter
    @Column(name = "OngPwd", nullable = false, length = 100)
    private String ongPwd;
    @Column(name = "isActive", nullable = false)
    private boolean isActive;
    @Getter
    @Column(name = "CreatedBy", nullable = false)
    private String createdBy;
    @Getter
    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt;

    public void setOngId(UUID ongId) {
        this.ongId = ongId;
    }

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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
