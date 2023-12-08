package com.example.schoolsbybackend.controller;

import com.example.schoolsbybackend.entity.SemesterEntity;
import com.example.schoolsbybackend.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/semesters")
public class SemesterController {
    @Autowired
    private SemesterService semesterService;

    @PostMapping
    public ResponseEntity createSemester(@RequestBody SemesterEntity semester) {
        try {
            semesterService.create(semester);
            return ResponseEntity.ok("Новый семестр сохранен.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity getAllSemesters() {
        try {
            return ResponseEntity.ok(semesterService.getAllSemesters());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneSemester(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(semesterService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteSemester(@PathVariable Long id) {
        try {
            semesterService.delete(id);
            return ResponseEntity.ok("Семестр успешно удален!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
