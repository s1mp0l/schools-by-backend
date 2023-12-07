package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.TeacherEntity;
import com.example.schoolsbybackend.exception.TeacherAlreadyExistsException;
import com.example.schoolsbybackend.exception.TeacherNotFoundException;
import com.example.schoolsbybackend.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepo teacherRepo;


    public TeacherEntity getById(Long id) throws TeacherNotFoundException {
        Optional<TeacherEntity> teacher =  teacherRepo.findById(id);
        if (teacher.isEmpty()) throw new TeacherNotFoundException("Предмет не найден.");
        return teacher.get();
    }

    public List<TeacherEntity> getAllTeachers() throws TeacherNotFoundException {
        if(teacherRepo.findAll() == null){
            throw new TeacherNotFoundException("Предметов не найдено!");
        }
        return teacherRepo.findAll();
    }

    public void delete(Long id) throws TeacherNotFoundException {
        Optional<TeacherEntity> teacher =  teacherRepo.findById(id);
        if (teacher.isEmpty()) throw new TeacherNotFoundException("Предмет не найден.");

        teacherRepo.deleteById(id);
    }
}
