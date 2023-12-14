package com.example.schoolsbybackend.repository;

import com.example.schoolsbybackend.entity.LessonEntity;
import com.example.schoolsbybackend.model.Lesson;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface LessonRepo extends CrudRepository<LessonEntity, Long> {

    List<LessonEntity> findAll();
    List<LessonEntity> findAllByNclassId(Long id);

    List<LessonEntity> findAllByNclassIdAndDate(Long id, LocalDate date);
    List<LessonEntity> findAllByTeacherIdAndDate(Long id, LocalDate date);

    List<LessonEntity> findAllByTeacherIdAndNclassIdAndSubjectId(Long teacherid, Long nclassid, Long subjectid);

}
