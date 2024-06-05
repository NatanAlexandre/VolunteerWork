package com.projetoextesao.volunteerWork.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = UserModel.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserModel {
    public static final String TABLE_NAME = "TB_USERS";
    private static final long serialVersionUID = 1L;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserID", unique = true)
    private UUID userId;
    @Getter
    @Column(name = "UserName", nullable = false, length = 200)
    private String userName;
    @Getter
    @Column(name = "UserMail", unique = true, nullable = false, length = 100)
    private String userMail;
    @Getter
    @Column(name = "UserPwd", nullable = false, length = 100)
    private String userPwd;
    @Column(name = "IsVolunteer", nullable = false)
    private boolean isVolunteer;
    @Column(name = "IsActive", nullable = false)
    private boolean isActive;
    @Getter
    @Column(name = "Ongs")
    private List<String> ongs;

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

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
