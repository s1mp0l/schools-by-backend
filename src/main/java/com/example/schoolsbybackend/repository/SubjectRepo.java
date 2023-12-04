package com.example.schoolsbybackend.repository;


import com.example.schoolsbybackend.entity.SubjectEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubjectRepo extends CrudRepository<SubjectEntity, Long> {
    SubjectEntity findByTitle(String title);
    List<SubjectEntity> findAll();
}
