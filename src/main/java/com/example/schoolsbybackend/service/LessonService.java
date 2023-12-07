package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.*;
import com.example.schoolsbybackend.exception.*;
import com.example.schoolsbybackend.model.User;
import com.example.schoolsbybackend.repository.ClassRepo;
import com.example.schoolsbybackend.repository.LessonRepo;
import com.example.schoolsbybackend.repository.SubjectRepo;
import com.example.schoolsbybackend.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
    @Autowired
    private LessonRepo lessonRepo;

    @Autowired
    private SubjectRepo subjectRepo;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private ClassRepo classRepo;

    public LessonEntity create(LessonEntity lesson, Long subject_id, Long teacher_id, Long class_id) throws LessonAlreadyExistsException {
        if(false){
            throw new LessonAlreadyExistsException("Урок уже существует");
        }

        SubjectEntity subj = subjectRepo.findById(subject_id).get();
        TeacherEntity teacher = teacherRepo.findById(subject_id).get();
        ClassEntity nclass = classRepo.findById(class_id).get();
        lesson.setSubject(subj);
        lesson.setTeacher(teacher);
        lesson.setNclass(nclass);
        return lessonRepo.save(lesson);
    }
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
