package com.example.schoolsbybackend.model;

import com.example.schoolsbybackend.entity.MarkEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Mark {
    private Long id;
    private Long value;
    private String comment;
    private Boolean is_sem;
    private Boolean is_year;
    private Long semester;
    private Long student;
    private Long lesson;
    private Long subject;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate lessonDate;

    public static Mark toModel(MarkEntity entity){
        Mark model = new Mark();

        model.setId(entity.getId());
        model.setValue(entity.getValue());
        model.setIs_sem(entity.getIs_sem());
        model.setIs_year(entity.getIs_year());
        model.setSemester(entity.getSemester().getId());
        model.setStudent(entity.getStudent().getId());
        model.setLesson(entity.getLesson().getId());
        model.setSubject(entity.getSubject());
        model.setLessonDate(entity.getLesson().getDate());

        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getIs_sem() {
        return is_sem;
    }

    public void setIs_sem(Boolean is_sem) {
        this.is_sem = is_sem;
    }

    public Boolean getIs_year() {
        return is_year;
    }

    public void setIs_year(Boolean is_year) {
        this.is_year = is_year;
    }

    public Long getSemester() {
        return semester;
    }

    public void setSemester(Long semester) {
        this.semester = semester;
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

    public Long getSubject() {
        return subject;
    }

    public void setSubject(Long subject) {
        this.subject = subject;
    }

    public LocalDate getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(LocalDate lessonDate) {
        this.lessonDate = lessonDate;
    }
}
