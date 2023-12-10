package com.example.schoolsbybackend.repository;

import com.example.schoolsbybackend.entity.ParentEntity;
import com.example.schoolsbybackend.entity.StudentEntity;
import com.example.schoolsbybackend.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepo extends CrudRepository<StudentEntity, Long> {
    List<StudentEntity> findAll();
    StudentEntity findByUser(UserEntity user);
}
