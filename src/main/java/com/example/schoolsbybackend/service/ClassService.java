package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.ClassEntity;
import com.example.schoolsbybackend.entity.SubjectEntity;
import com.example.schoolsbybackend.exception.*;
import com.example.schoolsbybackend.exception.ClassNotFoundException;
import com.example.schoolsbybackend.model.NClass;
import com.example.schoolsbybackend.model.Subject;
import com.example.schoolsbybackend.repository.ClassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassService {
    @Autowired
    private ClassRepo classRepo;

    public ClassEntity create(ClassEntity cl) throws ClassAlreadyExistsException {

        ClassEntity existingClass = classRepo.findByGradeAndLetter(cl.getGrade(), cl.getLetter());
        if (existingClass != null) {
            throw new ClassAlreadyExistsException("Класс " + cl.getGrade() + " " + cl.getLetter() + " уже существует.");
        }
        return classRepo.save(cl);
    }
    public NClass getById(Long id) throws ClassNotFoundException {
        Optional<ClassEntity> cl =  classRepo.findById(id);
        if (cl.isEmpty()) throw new ClassNotFoundException("Класс не найден.");
        return NClass.toModel(cl.get());
    }
    public List<NClass> getAllClasses() throws ClassNotFoundException{
        if(classRepo.findAll() == null){
            throw new ClassNotFoundException("Классов не найдено!");
        }
        return classRepo.findAll().stream().map(NClass::toModel).collect(Collectors.toList());
    }
    public void delete(Long id) throws ClassNotFoundException {
        Optional<ClassEntity> cl =  classRepo.findById(id);
        if (cl.isEmpty()) throw new ClassNotFoundException("Класс не найден.");

        classRepo.deleteById(id);
    }
}
