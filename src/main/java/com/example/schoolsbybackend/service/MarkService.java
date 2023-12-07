package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.MarkEntity;
import com.example.schoolsbybackend.exception.*;
import com.example.schoolsbybackend.repository.MarkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarkService {

    @Autowired
    private MarkRepo markRepo;

    public MarkEntity create(MarkEntity mk) throws MarkAlreadyExistsException {

        MarkEntity existingMark = markRepo.findByDateAndValue(mk.getDate(), mk.getValue());

        return markRepo.save(mk);
    }

    public MarkEntity getById(Long id) throws MarkNotFoundException {
        Optional<MarkEntity> mk =  markRepo.findById(id);
        if (mk.isEmpty()) throw new MarkNotFoundException("Оценка не найдена.");
        return mk.get();
    }

    public List<MarkEntity> getAllMarks() throws MarkNotFoundException{
        if(markRepo.findAll() == null){
            throw new MarkNotFoundException("Оценки не найдены.!");
        }
        return markRepo.findAll();
    }

    public void delete(Long id) throws MarkNotFoundException {
        Optional<MarkEntity> mk =  markRepo.findById(id);
        if (mk.isEmpty()) throw new MarkNotFoundException("Оценка не найдена.");

        markRepo.deleteById(id);
    }
}
