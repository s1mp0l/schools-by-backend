package com.example.schoolsbybackend.controller;


import com.example.schoolsbybackend.entity.LessonEntity;
import com.example.schoolsbybackend.entity.SubjectEntity;
import com.example.schoolsbybackend.exception.LessonAlreadyExistsException;
import com.example.schoolsbybackend.exception.LessonException;
import com.example.schoolsbybackend.exception.SubjectAlreadyExistsException;
import com.example.schoolsbybackend.service.LessonService;
import com.example.schoolsbybackend.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity getOneLesson(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(lessonService.getById(id));
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
