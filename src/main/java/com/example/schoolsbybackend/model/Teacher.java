package com.example.schoolsbybackend.model;

import com.example.schoolsbybackend.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class Teacher {
    private Long id;
    private UserEntity user;
    private List<Subject> subjects;
    private List<NClass> classes;

    public static Teacher toModel(TeacherEntity entity) {
        Teacher model = new Teacher();
        model.setId(entity.getId());
        model.setUser(entity.getUser());
        model.setClasses(entity.getClasses().stream().map(NClass::toModel).collect(Collectors.toList()));
        model.setSubjects(entity.getSubjects().stream().map(Subject::toModel).collect(Collectors.toList()));
        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<NClass> getClasses() {
        return classes;
    }

    public void setClasses(List<NClass> classes) {
        this.classes = classes;
    }
}
