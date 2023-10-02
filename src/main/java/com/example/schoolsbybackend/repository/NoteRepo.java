package com.example.schoolsbybackend.repository;

import com.example.schoolsbybackend.entity.NoteEntity;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepo extends CrudRepository<NoteEntity, Long> {

}
