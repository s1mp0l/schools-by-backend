package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.ClassEntity;
import com.example.schoolsbybackend.entity.ParentEntity;
import com.example.schoolsbybackend.entity.StudentEntity;
import com.example.schoolsbybackend.entity.UserEntity;
import com.example.schoolsbybackend.model.Parent;
import com.example.schoolsbybackend.model.Student;
import com.example.schoolsbybackend.repository.ClassRepo;
import com.example.schoolsbybackend.repository.ParentRepo;
import com.example.schoolsbybackend.repository.StudentRepo;
import com.example.schoolsbybackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParentService {

    @Autowired
    private ParentRepo parentRepo;

    @Autowired
    private UserRepo userRepo;

    public Parent createParent(ParentEntity parent, Long user_id) throws Exception {
        Optional<UserEntity> userEntity = userRepo.findById(user_id);
        if(userEntity.isEmpty()) {throw new Exception("Пользователь с ID " + user_id + " не найден.");}
        parent.setUser(userEntity.get());

        return Parent.toModel(parentRepo.save(parent));
    }

    public Parent getById(Long id) throws Exception {
        Optional<ParentEntity> parent =  parentRepo.findById(id);
        if (parent.isEmpty()) throw new Exception("Родитель не найден.");
        return Parent.toModel(parent.get());
    }

    public List<Parent> getAll() throws Exception {
        if(parentRepo.findAll() == null){
            throw new Exception("Родителей не найдено!");
        }
        return parentRepo.findAll().stream().map(Parent::toModel).collect(Collectors.toList());
    }

    public void delete(Long id) throws Exception {
        Optional<ParentEntity> parent =  parentRepo.findById(id);
        if (parent.isEmpty()) throw new Exception("Родитель не найден.");

        parentRepo.deleteById(id);
    }
}


