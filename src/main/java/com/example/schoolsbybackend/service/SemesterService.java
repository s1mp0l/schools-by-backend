package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.ClassEntity;
import com.example.schoolsbybackend.entity.SemesterEntity;
import com.example.schoolsbybackend.entity.SubjectEntity;
import com.example.schoolsbybackend.entity.YearEntity;
import com.example.schoolsbybackend.exception.ClassAlreadyExistsException;
import com.example.schoolsbybackend.exception.ClassNotFoundException;
import com.example.schoolsbybackend.exception.NoSemesterFoundException;
import com.example.schoolsbybackend.repository.SemesterRepo;
import com.example.schoolsbybackend.repository.YearRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemesterService {
    @Autowired
    private SemesterRepo semesterRepo;

    @Autowired
    private YearRepo yearRepo;

    public SemesterEntity create(SemesterEntity semester) throws Exception {
        // Проверяем, что даты начала и окончания не null
        if (semester.getStart_date() == null || semester.getEnd_date() == null) {
            throw new IllegalArgumentException("Даты начала и окончания семестра должны быть заданы.");
        }

        // Определение года для семестра
        List<YearEntity> years = yearRepo.findAll();
        YearEntity associatedYear = years.stream()
                .filter(year -> !semester.getStart_date().isBefore(year.getStartDate())
                        && !semester.getEnd_date().isAfter(year.getEndDate()))
                .findFirst()
                .orElseThrow(() -> new Exception("Год для данного семестра не найден."));

        semester.setYear(associatedYear);
        return semesterRepo.save(semester);
    }
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
