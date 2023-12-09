package com.example.schoolsbybackend.repository;

import com.example.schoolsbybackend.entity.MarkEntity;
import com.example.schoolsbybackend.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface MarkRepo extends CrudRepository<MarkEntity, Long> {
    List<MarkEntity> findAll();
    List<MarkEntity> findAllByStudent(StudentEntity student);
}
