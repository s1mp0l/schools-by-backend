package com.example.schoolsbybackend.repository;

import com.example.schoolsbybackend.entity.NoteEntity;
import com.example.schoolsbybackend.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteRepo extends CrudRepository<NoteEntity, Long> {
    List<NoteEntity> findAll();
    List<NoteEntity> findByUserId(Long userId);
}
