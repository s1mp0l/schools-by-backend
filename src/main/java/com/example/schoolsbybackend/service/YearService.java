package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.YearEntity;
import com.example.schoolsbybackend.exception.YearNotFoundException;
import com.example.schoolsbybackend.repository.YearRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class YearService {
    @Autowired
    private YearRepo yearRepo;

    public YearEntity create(YearEntity obj) throws Exception {
        YearEntity existingYears = yearRepo.findByStartDateAndEndDate(obj.getStartDate(), obj.getEndDate());
        if (existingYears != null) {
            throw new Exception("Такой год уже существует.");
        }
        return yearRepo.save(obj);
    }
    public YearEntity getById(Long id) throws YearNotFoundException {
        Optional<YearEntity> obj =  yearRepo.findById(id);
        if (obj.isEmpty()) throw new YearNotFoundException("Год не найден.");
        return obj.get();
    }

    public List<YearEntity> getAllYears() throws YearNotFoundException {
        if(yearRepo.findAll() == null){
            throw new YearNotFoundException("Года не найдены");
        }
        return yearRepo.findAll();
    }

    public void delete(Long id) throws YearNotFoundException {
        Optional<YearEntity> obj =  yearRepo.findById(id);
        if (obj.isEmpty()) throw new YearNotFoundException("Год не найден.");

        yearRepo.deleteById(id);
    }

}
