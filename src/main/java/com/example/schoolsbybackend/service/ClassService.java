package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.ClassEntity;
import com.example.schoolsbybackend.exception.ClassNotFoundException;
import com.example.schoolsbybackend.repository.ClassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassService {
    @Autowired
    private ClassRepo classRepo;
    public ClassEntity getById(Long id) throws ClassNotFoundException {
        Optional<ClassEntity> class1 =  classRepo.findById(id);
        if (class1.isEmpty()) throw new ClassNotFoundException("Класс не найден.");
        return class1.get();
    }

}
