package com.example.schoolsbybackend.model;

import com.example.schoolsbybackend.entity.ParentEntity;
import com.example.schoolsbybackend.entity.StudentEntity;
import com.example.schoolsbybackend.entity.TeacherEntity;
import com.example.schoolsbybackend.interfaces.PersonInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Student implements PersonInterface {
    private Long id;
    private User user;
    private NClass nclass;
    private List<Long> parents;

    public static Student toModel(StudentEntity entity) {
        Student model = new Student();
        model.setId(entity.getId());
        model.setUser(User.toModel(entity.getUser()));
        model.setNclass(NClass.toModel(entity.getNclass()));
        if (entity.getParents() != null) {
            model.setParents(entity.getParents().stream().map(ParentEntity::getId).collect(Collectors.toList()));
        } else {
            model.setParents(new ArrayList<Long>());
        }
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

    public List<Long> getParents() {
        return parents;
    }

    public void setParents(List<Long> parents) {
        this.parents = parents;
    }
}
