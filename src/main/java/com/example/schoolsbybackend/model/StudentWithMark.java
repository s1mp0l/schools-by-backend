package com.example.schoolsbybackend.model;

import com.example.schoolsbybackend.entity.AbsenceEntity;
import com.example.schoolsbybackend.entity.MarkEntity;
import com.example.schoolsbybackend.entity.ParentEntity;
import com.example.schoolsbybackend.entity.StudentEntity;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class StudentWithMark extends Student {
    private Mark mark;
    private Absence absence;

    public StudentWithMark() {
    }

    public static StudentWithMark toModel(StudentEntity entity, MarkEntity mark, AbsenceEntity absence) {
        StudentWithMark model = new StudentWithMark();
        model.setId(entity.getId());
        model.setUser(User.toModel(entity.getUser()));
        model.setNclass(NClass.toModel(entity.getNclass()));
        if (absence != null) model.setAbsence(Absence.toModel(absence));
        if (mark != null) model.setMark(Mark.toModel(mark));
        return model;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public Absence getAbsence() {
        return absence;
    }

    public void setAbsence(Absence absence) {
        this.absence = absence;
    }
}
