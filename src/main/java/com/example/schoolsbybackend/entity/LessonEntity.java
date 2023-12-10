package com.example.schoolsbybackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class LessonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate date;

    @JsonFormat(pattern="HH:mm")
    private LocalTime time;

    private String task;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;

    @ManyToOne
    @JoinColumn(name="class_id")
    private ClassEntity nclass;


    @ManyToOne
    @JoinColumn(name="teacher_id")
    private TeacherEntity teacher;

    @JsonManagedReference
    @OneToMany(mappedBy = "lesson")
    private List<AbsenceEntity> absence;

    private String classroom;

    public ClassEntity getNclass() {
        return nclass;
    }

    public void setNclass(ClassEntity nclass) {
        this.nclass = nclass;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
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

    public SubjectEntity getSubject() {
        return subject;
    }

    public void setSubject(SubjectEntity subject) {
        this.subject = subject;
    }

    public List<AbsenceEntity> getAbsence() {
        return absence;
    }

    public void setAbsence(List<AbsenceEntity> absence) {
        this.absence = absence;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}
