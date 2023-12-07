package com.example.schoolsbybackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Entity
public class SemesterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate start_date;
    @NonNull
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate end_date;

    @Nullable
    private Long semester_Mark;

    public SemesterEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(@NonNull LocalDate start_date) {
        this.start_date = start_date;
    }

    @NonNull
    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(@NonNull LocalDate end_date) {
        this.end_date = end_date;
    }

    @Nullable
    public Long getSemester_Mark() {
        return semester_Mark;
    }

    public void setSemester_Mark(@Nullable Long semester_Mark) {
        this.semester_Mark = semester_Mark;
    }
}
