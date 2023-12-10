package com.example.schoolsbybackend.repository;


import com.example.schoolsbybackend.entity.AbsenceEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AbsenceRepo extends CrudRepository<AbsenceEntity, Long> {

    List<AbsenceEntity> findAll();
}
