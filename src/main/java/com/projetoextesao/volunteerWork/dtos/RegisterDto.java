package com.projetoextesao.volunteerWork.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;


public class RegisterDto {
    @Getter
    @NotNull(message = "Name Required")
    private String registerName;
    @Getter
    @NotNull(message = "E-mail Required")
    @Email(message = "Invalid E-mail")
    private String registerMail;
    @Getter
    @NotNull(message = "E-mail confirm required")
    @Email(message = "Invalid Confirm E-mail")
    private String registerMailConfirm;
    @Getter
    @NotNull(message = "Password required")
    private String registerPwd;
    @Getter
    @NotNull(message = "Password Confirm Required")
    private String registerPwdConfirm;
    @NotNull
    private boolean isVolunteer;
    @Getter
    private List<String> registerOng;
    @NotNull(message = "Terms and Conditions")
    private boolean isTerm;

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
