package com.example.schoolsbybackend.model;

import com.example.schoolsbybackend.entity.ParentEntity;
import com.example.schoolsbybackend.entity.StudentEntity;
import com.example.schoolsbybackend.entity.UserEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

import java.util.List;
import java.util.stream.Collectors;

public class Parent {
    private Long id;
    private User user;
    private List<Student> students;

    public static Parent toModel(ParentEntity entity) {
        Parent model = new Parent();
        model.setId(entity.getId());
        model.setUser(User.toModel(entity.getUser()));
        model.setStudents(entity.getStudents().stream().map(Student::toModel).collect(Collectors.toList()));
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
