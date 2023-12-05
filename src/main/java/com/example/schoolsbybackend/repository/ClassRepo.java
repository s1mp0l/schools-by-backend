package com.example.schoolsbybackend.repository;

import com.example.schoolsbybackend.entity.ClassEntity;
import com.example.schoolsbybackend.entity.SubjectEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClassRepo extends CrudRepository<ClassEntity, Long> {

    ClassEntity findByGradeAndLetter(String grade, String letter);

    ClassEntity findByGrade(String grade);

    ClassEntity findByLetter(String letter);

    List<ClassEntity> findAll();
}
