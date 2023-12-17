package com.example.schoolsbybackend.repository;

import com.example.schoolsbybackend.entity.LessonEntity;
import com.example.schoolsbybackend.entity.MarkEntity;
import com.example.schoolsbybackend.entity.SemesterEntity;
import com.example.schoolsbybackend.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MarkRepo extends CrudRepository<MarkEntity, Long> {
    List<MarkEntity> findAll();
    List<MarkEntity> findAllByStudent(StudentEntity student);

    MarkEntity findByStudentAndLesson(StudentEntity student, LessonEntity lesson);

    List<MarkEntity> findAllByStudentAndSubjectAndSemester(StudentEntity student, Long subject_id, SemesterEntity semester);

    List<MarkEntity> findAllByStudentAndSemester(StudentEntity student, SemesterEntity semester);
}
