package com.example.schoolsbybackend.repository;

import com.example.schoolsbybackend.entity.TeacherEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeacherRepo extends CrudRepository<TeacherEntity, Long> {
    List<TeacherEntity> findAll();
}
