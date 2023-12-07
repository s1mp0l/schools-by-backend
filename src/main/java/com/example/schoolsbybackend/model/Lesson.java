package com.example.schoolsbybackend.model;

import com.example.schoolsbybackend.entity.LessonEntity;
import com.example.schoolsbybackend.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;

public class Lesson {

    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String task;
    private String nclass;
    private String teacher;
    private String subject;


    public static Lesson toModel(LessonEntity entity) {
        Lesson model = new Lesson();
        model.setId(entity.getId());
        model.setDate(entity.getDate());
        model.setTime(entity.getTime());
        model.setTask(entity.getTask());
        model.setNclass(entity.getNclass().getGrade() + " " + entity.getNclass().getLetter());
        model.setTeacher(entity.getTeacher().getUser().getLastName() + " " + entity.getTeacher().getUser().getFirstName() + " " + entity.getTeacher().getUser().getPatronymic());
        model.setSubject(entity.getSubject().getTitle());
        return model;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getNclass() {
        return nclass;
    }

    public void setNclass(String nclass) {
        this.nclass = nclass;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
