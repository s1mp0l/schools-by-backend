package com.example.schoolsbybackend.model;

import com.example.schoolsbybackend.entity.StudentEntity;
import com.example.schoolsbybackend.entity.TeacherEntity;

import java.util.stream.Collectors;

public class Student {
    private Long id;
    private User user;
    private NClass nclass;

    public static Student toModel(StudentEntity entity) {
        Student model = new Student();
        model.setId(entity.getId());
        model.setUser(User.toModel(entity.getUser()));
        model.setNclass(NClass.toModel(entity.getNclass()));
        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public NClass getNclass() {
        return nclass;
    }

    public void setNclass(NClass nclass) {
        this.nclass = nclass;
    }
}
