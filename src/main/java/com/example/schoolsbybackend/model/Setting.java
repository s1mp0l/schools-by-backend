package com.example.schoolsbybackend.model;

import com.example.schoolsbybackend.entity.SettingEntity;

public class Setting {

    private Long id;
    private String lang;
    private Boolean darkTheme;
    private Long fontSize;
    private Boolean notificationsOn;
    private Long user;

    public static Setting toModel(SettingEntity entity){
        Setting model = new Setting();
        model.setId(entity.getId());
        model.setLang(entity.getLang());
        model.setDarkTheme(entity.getDarkTheme());
        model.setFontSize(entity.getFontSize());
        model.setNotificationsOn(entity.getNotificationsOn());
        model.setUser(entity.getUser().getId());
        return model;
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

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
}
