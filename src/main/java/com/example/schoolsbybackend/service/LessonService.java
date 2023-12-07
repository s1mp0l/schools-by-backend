package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.*;
import com.example.schoolsbybackend.exception.*;
import com.example.schoolsbybackend.model.Lesson;
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
import java.util.stream.Collectors;

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

    public LessonEntity create(LessonEntity lesson, Long subject_id, Long teacher_id, Long class_id) throws LessonException {
        Optional<SubjectEntity> subj = subjectRepo.findById(subject_id);
        Optional<TeacherEntity> teacher = teacherRepo.findById(teacher_id);
        Optional<ClassEntity> nclass = classRepo.findById(class_id);
        if(subj.isEmpty()) throw new LessonException("Урока с таким id не найдено.");
        if(teacher.isEmpty()) throw new LessonException("Учителя с таким id не найдено.");
        if(nclass.isEmpty()) throw new LessonException("Класса с таким id не найдено.");
        lesson.setSubject(subj.get());
        lesson.setTeacher(teacher.get());
        lesson.setNclass(nclass.get());
        return lessonRepo.save(lesson);
    }
    public Lesson getById(Long id) throws LessonNotFoundException {
        Optional<LessonEntity> lesson =  lessonRepo.findById(id);
        if (lesson.isEmpty()) throw new LessonNotFoundException("Урока с таким id не найдено.");
        return Lesson.toModel(lesson.get());
    }

    public List<Lesson> getAllLessons() throws NoLessonsFoundException{
        if(lessonRepo.findAll() == null){
            throw new NoLessonsFoundException("Уроков не найдена");
        }
        List<Lesson> lessons = lessonRepo.findAll().stream().map(less -> Lesson.toModel(less)).collect(Collectors.toList());
        return lessons;
    }

    public void delete(Long id) throws LessonNotFoundException {
        Optional<LessonEntity> subj =  lessonRepo.findById(id);
        if (subj.isEmpty()) throw new LessonNotFoundException("Урока с таким id не найдено.");

        lessonRepo.deleteById(id);
    }
}
