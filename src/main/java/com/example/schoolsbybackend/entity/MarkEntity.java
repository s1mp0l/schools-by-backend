package com.example.schoolsbybackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class MarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate date;
    @Nullable
    private Long value;
    @Nullable
    private Boolean absence;
    @Nullable
    private String comment;

    public MarkEntity() {
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
}
