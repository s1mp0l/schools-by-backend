package com.example.schoolsbybackend.controller;

import com.example.schoolsbybackend.entity.TeacherEntity;
import com.example.schoolsbybackend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
public class TeacherController {


    @Autowired
    private TeacherService teacherService;

    @PostMapping
    public ResponseEntity createTeacher(@RequestBody TeacherEntity teacher,
                                        @RequestParam Long user_id) {
        try {
            return ResponseEntity.ok(teacherService.createTeacher(teacher, user_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/addClass")
    public ResponseEntity addTeacherClass(@PathVariable Long id,
                                          @RequestParam Long class_id) {
        try {
            return ResponseEntity.ok(teacherService.addClass(id, class_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/addSubject")
    public ResponseEntity addTeacherSubject(@PathVariable Long id,
                                            @RequestParam Long subject_id) {
        try {
            return ResponseEntity.ok(teacherService.addSubject(id, subject_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllTeachers() {
        try {
            return ResponseEntity.ok(teacherService.getAllTeachers());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity getOneTeacher(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(teacherService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTeacher(@PathVariable Long id) {
        try {
            teacherService.delete(id);
            return ResponseEntity.ok("Учитель успешно удален!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
