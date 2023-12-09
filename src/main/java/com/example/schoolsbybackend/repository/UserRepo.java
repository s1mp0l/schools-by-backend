package com.example.schoolsbybackend.repository;

import com.example.schoolsbybackend.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    List<UserEntity> findAll();
    UserEntity findByLogin(String login);
    UserEntity findByPhoneNumber(String phoneNumber);
}
