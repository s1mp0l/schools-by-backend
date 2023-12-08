package com.example.schoolsbybackend.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userType;
    private String login;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String patronymic;

    @OneToOne(mappedBy = "user")
    private StudentEntity student;

    @OneToOne(mappedBy = "user")
    private TeacherEntity teacher;

    @OneToOne(mappedBy = "user")
    private ParentEntity parent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<NoteEntity> notifications;

    public UserEntity() {
    }

    public List<NoteEntity> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NoteEntity> notifications) {
        this.notifications = notifications;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
