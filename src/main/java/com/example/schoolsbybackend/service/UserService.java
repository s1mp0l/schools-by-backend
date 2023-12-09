package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.UserEntity;
import com.example.schoolsbybackend.exception.TeacherNotFoundException;
import com.example.schoolsbybackend.exception.UserAlreadyExistsException;
import com.example.schoolsbybackend.exception.UserNotFoundException;
import com.example.schoolsbybackend.model.Teacher;
import com.example.schoolsbybackend.model.User;
import com.example.schoolsbybackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public Long registration(UserEntity user) throws UserAlreadyExistsException {
        if (userRepo.findByLogin(user.getLogin()) != null) {
            throw new UserAlreadyExistsException("Пользователь с таким логином уже существует!");
        }

        if (userRepo.findByPhoneNumber(user.getPhoneNumber()) != null) {
            throw new UserAlreadyExistsException("Пользователь с таким номером телефона уже существует!");
        }

        return userRepo.save(user).getId();
    }

    public List<User> getAll() throws Exception {
        if(userRepo.findAll() == null) {
            throw new TeacherNotFoundException("Пользователей не найдено!");
        }
        return userRepo.findAll().stream().map(User::toModel).collect(Collectors.toList());
    }

    public User getById(Long id) throws UserNotFoundException {
        Optional<UserEntity> user =  userRepo.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("Пользователь не найден.");
        return User.toModel(user.get());
    }

    public void delete(Long id) throws UserNotFoundException {
        Optional<UserEntity> user =  userRepo.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("Пользователь не найден.");

        userRepo.deleteById(id);
    }
}
