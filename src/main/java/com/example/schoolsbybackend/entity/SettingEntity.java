package com.example.schoolsbybackend.entity;

import com.example.schoolsbybackend.model.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class SettingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String lang;
    private Boolean darkTheme;
    private Long fontSize;
    private Boolean notificationsOn;
    private Boolean pushNotificationsOn;

    public SettingEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Boolean getDarkTheme() {
        return darkTheme;
    }

    public void setDarkTheme(Boolean darkTheme) {
        this.darkTheme = darkTheme;
    }

    public Long getFontSize() {
        return fontSize;
    }

    public void setFontSize(Long fontSize) {
        this.fontSize = fontSize;
    }

    public Boolean getNotificationsOn() {
        return notificationsOn;
    }

    public void setNotificationsOn(Boolean notificationsOn) {
        this.notificationsOn = notificationsOn;
    }

    @Nullable
    public Boolean getPushNotificationsOn() {
        return pushNotificationsOn;
    }

    public void setPushNotificationsOn(@Nullable Boolean pushNotificationsOn) {
        this.pushNotificationsOn = pushNotificationsOn;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
