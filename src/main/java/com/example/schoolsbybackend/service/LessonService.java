package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.*;
import com.example.schoolsbybackend.exception.*;
import com.example.schoolsbybackend.model.*;
import com.example.schoolsbybackend.repository.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private MarkRepo markRepo;
    @Autowired
    private AbsenceRepo absenceRepo;

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

    public Lesson getByIdWithStudents(Long id) throws LessonNotFoundException {
        Optional<LessonEntity> lesson =  lessonRepo.findById(id);
        if (lesson.isEmpty()) throw new LessonNotFoundException("Урока с таким id не найдено.");

        Long classId = lesson.get().getNclass().getId();
        List<StudentEntity> students =  studentRepo.findAllByNclassId(classId);
        List<StudentWithMark> studentsWithMarks = students.stream().map(st -> StudentWithMark.toModel(
                st,
                markRepo.findByStudentAndLesson(st, lesson.get()),
                absenceRepo.findByStudentAndLesson(st, lesson.get())
        )).toList();

        return LessonWithStudents.toModel(lesson.get(), studentsWithMarks);
    }

    public List<Lesson> getAllLessons() throws NoLessonsFoundException{
        if(lessonRepo.findAll() == null){
            throw new NoLessonsFoundException("Уроков не найдена");
        }
        List<Lesson> lessons = lessonRepo.findAll().stream().map(less -> Lesson.toModel(less)).collect(Collectors.toList());
        return lessons;
    }

    public List<Lesson> getAllLessonsWithClassId(Long id) throws Exception{
        if(lessonRepo.findAll() == null){
            throw new Exception("Уроков не найдено.");
        }
        return lessonRepo.findAllByNclassId(id).stream().map(Lesson::toModel).collect(Collectors.toList());
    }

    public List<Lesson> getAllLessonsByClassIdAndDate(Long id,
                                                      LocalDate date) throws Exception {
        if(lessonRepo.findAllByNclassIdAndDate(id, date) == null){
            throw new Exception("Уроков не найдено.");
        }
        return lessonRepo.findAllByNclassIdAndDate(id, date).stream().map(less -> Lesson.toModel(less)).collect(Collectors.toList());
    }

    public List<Lesson> getAllLessonsByTeacherIdAndDate(Long id,
                                                      LocalDate date) throws Exception {
        if(lessonRepo.findAllByTeacherIdAndDate(id, date) == null){
            throw new Exception("Уроков не найдено.");
        }
        return lessonRepo.findAllByTeacherIdAndDate(id, date).stream().map(less -> Lesson.toModel(less)).collect(Collectors.toList());
    }

    public List<Lesson> getAllLessonsByTeacherIdAndClassIdAndSubjectId(Long id, Long class_id, Long subject_id) throws Exception {
        if(lessonRepo.findAllByTeacherIdAndNclassIdAndSubjectId(id, class_id, subject_id) == null){
            throw new Exception("Уроков не найдено.");
        }
        return lessonRepo.findAllByTeacherIdAndNclassIdAndSubjectId(id, class_id, subject_id).stream().map(less -> Lesson.toModel(less)).collect(Collectors.toList());
    }
    public Lesson setLessonHomeTask(Long id, String hometask) throws LessonNotFoundException{
        Optional<LessonEntity> lesson =  lessonRepo.findById(id);
        if (lesson.isEmpty()) throw new LessonNotFoundException("Урока с таким id не найдено.");
        lesson.get().setTask(hometask);
        lessonRepo.save(lesson.get());
        return Lesson.toModel(lesson.get());
    }
    public void delete(Long id) throws LessonNotFoundException {
        Optional<LessonEntity> subj =  lessonRepo.findById(id);

        if (subj.isEmpty()) throw new LessonNotFoundException("Урока с таким id не найдено.");

        lessonRepo.deleteById(id);
    }
}
