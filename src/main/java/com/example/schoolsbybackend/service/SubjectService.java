package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.SubjectEntity;
import com.example.schoolsbybackend.exception.SubjectAlreadyExistsException;
import com.example.schoolsbybackend.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
