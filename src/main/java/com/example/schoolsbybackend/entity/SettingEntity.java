package com.example.schoolsbybackend.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class SettingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lang;
    private Boolean darkTheme;
    private Long fontSize;
    private Boolean notificationsOn;
    @Nullable
    private Boolean pushNotificationsOn;
}
