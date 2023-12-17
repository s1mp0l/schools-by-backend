package com.example.schoolsbybackend.repository;


import com.example.schoolsbybackend.entity.AbsenceEntity;
import com.example.schoolsbybackend.entity.LessonEntity;
import com.example.schoolsbybackend.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AbsenceRepo extends CrudRepository<AbsenceEntity, Long> {

    List<AbsenceEntity> findAll();
    AbsenceEntity findByStudentAndLesson(StudentEntity student, LessonEntity lesson);
}
