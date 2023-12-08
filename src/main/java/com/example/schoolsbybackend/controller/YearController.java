package com.example.schoolsbybackend.controller;

import com.example.schoolsbybackend.entity.YearEntity;
import com.example.schoolsbybackend.service.YearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/years")
public class YearController {
    @Autowired
    private YearService yearService;


    @PostMapping
    public ResponseEntity createYear(@RequestBody YearEntity obj) {
        try {
            yearService.create(obj);
            return ResponseEntity.ok("Новый год сохранен.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity getAllYears() {
        try {
            return ResponseEntity.ok(yearService.getAllYears());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneYear(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(yearService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteYear(@PathVariable Long id) {
        try {
            yearService.delete(id);
            return ResponseEntity.ok("Год успешно удален!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
