package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.LessonEntity;
import com.example.schoolsbybackend.entity.SubjectEntity;
import com.example.schoolsbybackend.entity.UserEntity;
import com.example.schoolsbybackend.exception.*;
import com.example.schoolsbybackend.model.User;
import com.example.schoolsbybackend.repository.LessonRepo;
import com.example.schoolsbybackend.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
    @Autowired
    private LessonRepo lessonRepo;


    public LessonEntity getById(Long id) throws LessonNotFoundException {
        Optional<LessonEntity> lesson =  lessonRepo.findById(id);
        if (lesson.isEmpty()) throw new LessonNotFoundException("Урока с таким id не найдено.");
        return lesson.get();
    }

    public List<LessonEntity> getAllLessons() throws NoLessonsFoundException{
        if(lessonRepo.findAll() == null){
            throw new NoLessonsFoundException("Уроков не найдена");
        }
        return lessonRepo.findAll();
    }

    public void delete(Long id) throws LessonNotFoundException {
        Optional<LessonEntity> subj =  lessonRepo.findById(id);
        if (subj.isEmpty()) throw new LessonNotFoundException("Урока с таким id не найдено.");

        lessonRepo.deleteById(id);
    }
}
