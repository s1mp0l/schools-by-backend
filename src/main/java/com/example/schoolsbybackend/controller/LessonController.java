package com.example.schoolsbybackend.controller;


import com.example.schoolsbybackend.entity.LessonEntity;
import com.example.schoolsbybackend.exception.LessonException;
import com.example.schoolsbybackend.service.LessonService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    @Autowired
    private LessonService lessonService;


    @PostMapping
    public ResponseEntity create(@RequestBody LessonEntity lesson,
                                 @RequestParam Long subject_id,
                                 @RequestParam Long teacher_id,
                                 @RequestParam Long class_id){
        try {
            lessonService.create(lesson, subject_id, teacher_id, class_id);
            return ResponseEntity.ok("Новый урок сохранен.");
        }
        catch (LessonException e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return  ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }
    @GetMapping
    public ResponseEntity getAllLessons() {
        try {
            return ResponseEntity.ok(lessonService.getAllLessons());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/class_id={id}")
    public ResponseEntity getAllLessonsWithClassId(@PathVariable Long id){
        try {
            return ResponseEntity.ok(lessonService.getAllLessonsWithClassId(id));
        }
        catch (LessonException e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return  ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneLesson(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(lessonService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/with_students_info/{id}")
    public ResponseEntity getOneLessonWithStudents(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(lessonService.getByIdWithStudents(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/for_class_id={id}")
    public ResponseEntity getAllLessonsByClassIdAndDate(@PathVariable Long id,
                                       @RequestParam
                                       @JsonFormat(pattern = "dd-mm-yyyy") LocalDate date) {
        try {
            return ResponseEntity.ok(lessonService.getAllLessonsByClassIdAndDate(id, date));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/teacher_id={id}")
    public ResponseEntity getAllLessonsByTeacherIdAndDate(@PathVariable Long id,
                                                        @RequestParam
                                                        @JsonFormat(pattern = "dd-mm-yyyy") LocalDate date) {
        try {
            return ResponseEntity.ok(lessonService.getAllLessonsByTeacherIdAndDate(id, date));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/lessons_for_teacher_id={id}")
    public ResponseEntity getAllLessonsByTeacherIdAndClassIdAndSubjectId(@PathVariable Long id,
                                                          @RequestParam Long class_id,
                                                            @RequestParam Long subject_id) {
        try {
            return ResponseEntity.ok(lessonService.getAllLessonsByTeacherIdAndClassIdAndSubjectId(id, class_id, subject_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity setLessonHomeTask(@PathVariable Long id, @RequestBody LessonEntity lesson) {
        try {
            return ResponseEntity.ok(lessonService.setLessonHomeTask(id, lesson.getTask()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLesson(@PathVariable Long id) {
        try {
            lessonService.delete(id);
            return ResponseEntity.ok("Урок успешно удален!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
