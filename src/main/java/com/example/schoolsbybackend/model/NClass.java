package com.example.schoolsbybackend.model;

import com.example.schoolsbybackend.entity.ClassEntity;

public class NClass {
    private Long id;
    private String letter;
    private String grade;

    public static NClass toModel(ClassEntity entity) {
        NClass model = new NClass();
        model.setId(entity.getId());
        model.setLetter(entity.getLetter());
        model.setGrade(entity.getGrade());
        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
