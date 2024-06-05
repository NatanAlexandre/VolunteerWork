package com.projetoextesao.volunteerWork.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = RegisterModel.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RegisterModel {
    public static final String TABLE_NAME = "TB_REGISTER";
    public static final long serialVersionUID = 1L;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RegisterId", unique = true, nullable = false)
    private UUID registerId;
    @Getter
    @Column(name = "RegisterName", nullable = false, length = 200)
    private String registerName;
    @Getter
    @Column(name = "RegisterMail", unique = true, nullable = false, length = 100)
    private String registerMail;
    @Getter
    @Column(name = "RegisterMailConfirm", unique = true, nullable = false, length = 100)
    private String registerMailConfirm;
    @Getter
    @Column(name = "RegisterPwd", nullable = false, length = 100)
    private String registerPwd;
    @Getter
    @Column(name = "RegisterPwdConfirm", nullable = false, length = 100)
    private String registerPwdConfirm;
    @Column(name = "IsVolunteer", nullable = false)
    private boolean isVolunteer;
    @Getter
    @Column(name = "RegisterOng")
    private List<String> registerOng;
    @Column(name = "RegisterTerms", nullable = false)
    private boolean isTerm;

    public void setRegisterId(UUID registerId) {
        this.registerId = registerId;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public void setRegisterMail(String registerMail) {
        this.registerMail = registerMail;
    }

    public void setRegisterMailConfirm(String registerMailConfirm) {
        this.registerMailConfirm = registerMailConfirm;
    }

    public void setRegisterPwd(String registerPwd) {
        this.registerPwd = registerPwd;
    }

    public void setRegisterPwdConfirm(String registerPwdConfirm) {
        this.registerPwdConfirm = registerPwdConfirm;
    }

    public boolean isVolunteer() {
        return isVolunteer;
    }

    public void setVolunteer(boolean volunteer) {
        isVolunteer = volunteer;
    }

    public void setRegisterOng(List<String> registerOng) {
        this.registerOng = registerOng;
    }

    public boolean isTerm() {
        return isTerm;
    }

    public void setTerm(boolean term) {
        isTerm = term;
    }
}
