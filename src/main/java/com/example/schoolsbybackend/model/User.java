package com.example.schoolsbybackend.model;


import com.example.schoolsbybackend.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

/** Асбтрактная модель пользователя, которая не содержит пароля. */
public class User {
    private Long id;
    private String userType;
    private String login;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String patronymic;
    private List<Note> notes;

    public static User toModel(UserEntity entity) {
        User model = new User(entity.getId());
        model.setLogin(entity.getLogin());
        model.setUserType(entity.getUserType());
        model.setPhoneNumber(entity.getPhoneNumber());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setPatronymic(entity.getPatronymic());
        model.setNotes(entity.getNotifications().stream().map(Note::toModel).collect(Collectors.toList()));
        return model;
    }

    public User(Long id) {
        this.id = id;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
}
