package com.example.schoolsbybackend.model;

import com.example.schoolsbybackend.entity.MarkEntity;
import com.example.schoolsbybackend.entity.SemesterEntity;
import com.example.schoolsbybackend.entity.YearEntity;

import java.time.LocalDate;
import java.util.List;

public class Semester {
    private Long id;
    private LocalDate start_date;
    private LocalDate end_date;
    private Long year;
    private List<MarkEntity> marks;

    public static Semester toModel(SemesterEntity entity){
        Semester model = new Semester();

        model.setId(entity.getId());
        model.setStart_date(entity.getStart_date());
        model.setEnd_date(entity.getEnd_date());
        model.setYear(entity.getYear().getId());

        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public List<MarkEntity> getMarks() {
        return marks;
    }

    public void setMarks(List<MarkEntity> marks) {
        this.marks = marks;
    }
}
