package com.example.schoolsbybackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "year_id")
    private YearEntity year;

    @OneToMany(mappedBy = "semester")
    private List<MarkEntity> marks;

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

    public YearEntity getYear() {
        return year;
    }

    public void setYear(YearEntity year) {
        this.year = year;
    }

    public List<MarkEntity> getMarks() {
        return marks;
    }

    public void setMarks(List<MarkEntity> marks) {
        this.marks = marks;
    }
}
