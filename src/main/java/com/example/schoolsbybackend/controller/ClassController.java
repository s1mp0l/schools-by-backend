package com.example.schoolsbybackend.controller;

import com.example.schoolsbybackend.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    //@PostMapping

    @GetMapping
    public ResponseEntity getClassById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(classService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //@DeleteMapping("/{id}")

}
