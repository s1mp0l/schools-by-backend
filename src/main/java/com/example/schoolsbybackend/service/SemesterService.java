package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.ClassEntity;
import com.example.schoolsbybackend.entity.SemesterEntity;
import com.example.schoolsbybackend.exception.ClassAlreadyExistsException;
import com.example.schoolsbybackend.exception.ClassNotFoundException;
import com.example.schoolsbybackend.exception.NoSemesterFoundException;
import com.example.schoolsbybackend.repository.SemesterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemesterService {
    @Autowired
    private SemesterRepo semesterRepo;

    public SemesterEntity getById(Long id) throws NoSemesterFoundException {
        Optional<SemesterEntity> obj =  semesterRepo.findById(id);
        if (obj.isEmpty()) throw new NoSemesterFoundException("Семестр не найден.");
        return obj.get();
    }
    public List<SemesterEntity> getAllSemesters() throws NoSemesterFoundException {
        if(semesterRepo.findAll() == null){
            throw new NoSemesterFoundException("Семестры не найдены!");
        }
        return semesterRepo.findAll();
    }
    public void delete(Long id) throws NoSemesterFoundException {
        Optional<SemesterEntity> cl =  semesterRepo.findById(id);
        if (cl.isEmpty()) throw new NoSemesterFoundException("Семестр не найден.");

        semesterRepo.deleteById(id);
    }
}
