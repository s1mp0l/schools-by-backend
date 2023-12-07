package com.example.schoolsbybackend.controller;

import com.example.schoolsbybackend.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/settings")
public class SettingController {
    @Autowired
    private SettingService settingService;

    @GetMapping
    public ResponseEntity getAllLessons() {
        try {
            return ResponseEntity.ok(settingService.getAllLessons());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneLesson(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(settingService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLesson(@PathVariable Long id) {
        try {
            settingService.delete(id);
            return ResponseEntity.ok("Найстройка удалена!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
