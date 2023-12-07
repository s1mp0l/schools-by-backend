package com.example.schoolsbybackend.repository;

import com.example.schoolsbybackend.entity.SemesterEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SemesterRepo extends CrudRepository<SemesterEntity, Long> {
    List<SemesterEntity> findAll();
}
