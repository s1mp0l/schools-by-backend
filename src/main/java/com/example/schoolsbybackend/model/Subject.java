package com.example.schoolsbybackend.model;

import com.example.schoolsbybackend.entity.SubjectEntity;


public class Subject {
    private Long id;
    private String title;

    public static Subject toModel(SubjectEntity entity) {
        Subject model = new Subject();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

