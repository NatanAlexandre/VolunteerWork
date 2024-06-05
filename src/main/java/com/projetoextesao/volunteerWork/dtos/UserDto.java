package com.projetoextesao.volunteerWork.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserDto {

    @Getter
    @NotNull
    private String userName;
    @Getter
    @NotNull
    private String userMail;
    @Getter
    @NotNull
    private String userPwd;
    @NotNull
    private boolean isVolunteer;
    @NotNull
    private boolean isActive;
    @Getter
    private List<String> ongs;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public boolean isVolunteer() {
        return isVolunteer;
    }

    public void setVolunteer(boolean volunteer) {
        isVolunteer = volunteer;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setOngs(List<String> ongs) {
        this.ongs = ongs;
    }
}
