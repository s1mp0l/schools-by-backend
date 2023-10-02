package com.example.schoolsbybackend.repository;

import com.example.schoolsbybackend.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);
    UserEntity findByPhoneNumber(String phoneNumber);
}
