package com.example.schoolsbybackend.model;

import com.example.schoolsbybackend.entity.AbsenceEntity;
import com.example.schoolsbybackend.entity.LessonEntity;
import com.example.schoolsbybackend.entity.StudentEntity;

public class Absence {

    private Long id;

    private Boolean absence;

    private Long student;

    private Long lesson;

    public static Absence toModel(AbsenceEntity entity) {
        Absence model = new Absence();
        model.setId(entity.getId());
        model.setAbsence(entity.getAbsence());
        model.setStudent(entity.getStudent().getId());
        model.setLesson(entity.getLesson().getId());
        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAbsence() {
        return absence;
    }

    public void setAbsence(Boolean absence) {
        this.absence = absence;
    }

    public Long getStudent() {
        return student;
    }

    public void setStudent(Long student) {
        this.student = student;
    }

    public Long getLesson() {
        return lesson;
    }

    public void setLesson(Long lesson) {
        this.lesson = lesson;
    }
}
