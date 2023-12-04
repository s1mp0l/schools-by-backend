package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.SubjectEntity;
import com.example.schoolsbybackend.entity.UserEntity;
import com.example.schoolsbybackend.exception.NoSubjectsFoundException;
import com.example.schoolsbybackend.exception.SubjectAlreadyExistsException;
import com.example.schoolsbybackend.exception.SubjectNotFoundException;
import com.example.schoolsbybackend.exception.UserNotFoundException;
import com.example.schoolsbybackend.model.User;
import com.example.schoolsbybackend.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepo subjectRepo;

    public SubjectEntity create(SubjectEntity subj) throws SubjectAlreadyExistsException {
        if(subjectRepo.findByTitle(subj.getTitle()) != null){
            throw new SubjectAlreadyExistsException("Такой предмет уже есть!");
        }
        return subjectRepo.save(subj);
    }
    public SubjectEntity getById(Long id) throws SubjectNotFoundException {
        Optional<SubjectEntity> subj =  subjectRepo.findById(id);
        if (subj.isEmpty()) throw new SubjectNotFoundException("Предмет не найден.");
        return subj.get();
    }

    public List<SubjectEntity> getAllSubjects() throws NoSubjectsFoundException{
        if(subjectRepo.findAll() == null){
            throw new NoSubjectsFoundException("Предметов не найдено!");
        }
        return subjectRepo.findAll();
    }

    public void delete(Long id) throws SubjectNotFoundException {
        Optional<SubjectEntity> subj =  subjectRepo.findById(id);
        if (subj.isEmpty()) throw new SubjectNotFoundException("Предмет не найден.");

        subjectRepo.deleteById(id);
    }
}
