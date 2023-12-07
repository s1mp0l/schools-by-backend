package com.example.schoolsbybackend.controller;

import com.example.schoolsbybackend.service.SubjectService;
import com.example.schoolsbybackend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
public class TeacherController {


    @Autowired
    private TeacherService teacherService;

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
