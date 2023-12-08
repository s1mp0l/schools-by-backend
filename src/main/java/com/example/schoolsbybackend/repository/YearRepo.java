package com.example.schoolsbybackend.repository;

import com.example.schoolsbybackend.entity.YearEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface YearRepo extends CrudRepository<YearEntity, Long> {

    List<YearEntity> findAll();

    List<YearEntity> findByStartDateAndEndDate(LocalDate startDate, LocalDate endDate);
}
