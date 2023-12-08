package com.example.schoolsbybackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class MarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    private Long value;
    @Nullable
    private Boolean absence;
    @Nullable
    private String comment;

    private Boolean is_sem;

    private Boolean is_year;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private SemesterEntity semester;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private LessonEntity lessom;

    public MarkEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nullable
    public Long getValue() {
        return value;
    }

    public void setValue(@Nullable Long value) {
        this.value = value;
    }

    @Nullable
    public Boolean getAbsence() {
        return absence;
    }

    public void setAbsence(@Nullable Boolean absence) {
        this.absence = absence;
    }

    @Nullable
    public String getComment() {
        return comment;
    }

    public void setComment(@Nullable String comment) {
        this.comment = comment;
    }

    public Boolean getIs_year() {
        return is_year;
    }

    public void setIs_year(Boolean is_year) {
        this.is_year = is_year;
    }

    public Boolean getIs_sem() {
        return is_sem;
    }

    public void setIs_sem(Boolean is_sem) {
        this.is_sem = is_sem;
    }

    public SemesterEntity getSemester() {
        return semester;
    }

    public void setSemester(SemesterEntity semester) {
        this.semester = semester;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public LessonEntity getLessom() {
        return lessom;
    }

    public void setLessom(LessonEntity lessom) {
        this.lessom = lessom;
    }
}
