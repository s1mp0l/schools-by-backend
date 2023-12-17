package com.example.schoolsbybackend.model;

import com.example.schoolsbybackend.entity.LessonEntity;
import com.example.schoolsbybackend.entity.MarkEntity;
import com.example.schoolsbybackend.entity.StudentEntity;

import java.util.List;
import java.util.stream.Collectors;

public class LessonWithStudents extends Lesson {
    private List<StudentWithMark> students;
    public static LessonWithStudents toModel(LessonEntity entity, List<StudentWithMark> students) {
        LessonWithStudents model = new LessonWithStudents();
        model.setId(entity.getId());
        model.setDate(entity.getDate());
        model.setTime(entity.getTime());
        model.setTask(entity.getTask());
        model.setNclass(entity.getNclass().getGrade() + " " + entity.getNclass().getLetter());
        model.setTeacher(entity.getTeacher().getId());
        model.setSubject(entity.getSubject().getTitle());
        model.setClassroom(entity.getClassroom());
        model.setHometask(entity.getTask());
        model.setStudents(students);
        return model;
    }

    public List<StudentWithMark> getStudents() {
        return students;
    }

    public void setStudents(List<StudentWithMark> students) {
        this.students = students;
    }
}
