package com.projetoextesao.volunteerWork.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class LoginDto {
    @NotNull(message = "Login E-mail Required")
    @Email
    private String loginMail;
    @NotNull(message = "Login Password Required")
    private String loginPwd;

    public String getLoginMail() {
        return loginMail;
    }

    public void setLoginMail(String loginMail) {
        this.loginMail = loginMail;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }
}
