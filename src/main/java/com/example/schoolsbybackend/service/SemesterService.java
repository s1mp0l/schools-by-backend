package com.example.schoolsbybackend.service;


import com.example.schoolsbybackend.entity.SemesterEntity;

import com.example.schoolsbybackend.entity.YearEntity;
import com.example.schoolsbybackend.exception.NoSemesterFoundException;
import com.example.schoolsbybackend.model.Semester;
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
        List<YearEntity> years = yearRepo.findAll();
        YearEntity associatedYear = years.stream()
                .filter(year -> !semester.getStart_date().isBefore(year.getStartDate())
                        && !semester.getEnd_date().isAfter(year.getEndDate()))
                .findFirst()
                .orElseThrow(() -> new Exception("Год для данного семестра не найден."));

        semester.setYear(associatedYear);
        return semesterRepo.save(semester);
    }
    public Semester getById(Long id) throws NoSemesterFoundException {
        Optional<SemesterEntity> obj =  semesterRepo.findById(id);
        if (obj.isEmpty()) throw new NoSemesterFoundException("Семестр не найден.");
        return Semester.toModel(obj.get());
    }
    public List<Semester> getAllSemesters() throws NoSemesterFoundException {
        if(semesterRepo.findAll() == null){
            throw new NoSemesterFoundException("Семестры не найдены!");
        }
        List<Semester> semesters = semesterRepo.findAll().stream().map(Semester::toModel).toList();
        return semesters;
    }
    public void delete(Long id) throws NoSemesterFoundException {
        Optional<SemesterEntity> cl =  semesterRepo.findById(id);
        if (cl.isEmpty()) throw new NoSemesterFoundException("Семестр не найден.");

        semesterRepo.deleteById(id);
    }
}
