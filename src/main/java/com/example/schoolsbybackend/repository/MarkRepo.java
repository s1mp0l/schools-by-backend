package com.example.schoolsbybackend.repository;

import com.example.schoolsbybackend.entity.MarkEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface MarkRepo extends CrudRepository<MarkEntity, Long> {

    MarkEntity findAllByDate(LocalDate date);

    List<MarkEntity> findAll();

    MarkEntity findByDateAndValue(LocalDate date, Long value);
}
