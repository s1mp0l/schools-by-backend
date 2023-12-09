package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.SubjectEntity;
import com.example.schoolsbybackend.exception.SubjectAlreadyExistsException;
import com.example.schoolsbybackend.exception.SubjectNotFoundException;
import com.example.schoolsbybackend.model.NClass;
import com.example.schoolsbybackend.model.Subject;
import com.example.schoolsbybackend.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Subject getById(Long id) throws SubjectNotFoundException {
        Optional<SubjectEntity> subj =  subjectRepo.findById(id);
        if (subj.isEmpty()) throw new SubjectNotFoundException("Предмет не найден.");
        return Subject.toModel(subj.get());
    }

    public List<Subject> getAllSubjects() throws SubjectNotFoundException{
        if(subjectRepo.findAll() == null){
            throw new SubjectNotFoundException("Предметов не найдено!");
        }
        return subjectRepo.findAll().stream().map(Subject::toModel).collect(Collectors.toList());
    }

    public void delete(Long id) throws SubjectNotFoundException {
        Optional<SubjectEntity> subj =  subjectRepo.findById(id);
        if (subj.isEmpty()) throw new SubjectNotFoundException("Предмет не найден.");

        subjectRepo.deleteById(id);
    }
}
