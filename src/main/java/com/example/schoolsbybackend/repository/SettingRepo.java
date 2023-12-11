package com.example.schoolsbybackend.repository;

import com.example.schoolsbybackend.entity.SettingEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SettingRepo extends CrudRepository<SettingEntity, Long> {
    List<SettingEntity> findAll();
    SettingEntity findByUserId(Long id);
}
