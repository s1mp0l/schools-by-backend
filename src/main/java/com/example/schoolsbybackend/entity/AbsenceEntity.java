package com.example.schoolsbybackend.entity;

import com.example.schoolsbybackend.entity.LessonEntity;
import com.example.schoolsbybackend.entity.StudentEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class AbsenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean absence;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private LessonEntity lesson;

    public AbsenceEntity() {
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

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public LessonEntity getLesson() {
        return lesson;
    }

    public void setLesson(LessonEntity lesson) {
        this.lesson = lesson;
    }
}
