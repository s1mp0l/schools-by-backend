package com.example.schoolsbybackend.repository;

import com.example.schoolsbybackend.entity.LessonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LessonRepo extends CrudRepository<LessonEntity, Long> {

    List<LessonEntity> findAll();


}
