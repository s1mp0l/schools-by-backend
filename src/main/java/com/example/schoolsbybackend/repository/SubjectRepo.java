package com.example.schoolsbybackend.repository;


import com.example.schoolsbybackend.entity.SubjectEntity;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepo extends CrudRepository<SubjectEntity, Long> {
    SubjectEntity findByTitle(String title);
}
