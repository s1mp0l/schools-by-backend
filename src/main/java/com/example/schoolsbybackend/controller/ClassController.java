package com.example.schoolsbybackend.controller;

import com.example.schoolsbybackend.entity.ClassEntity;
import com.example.schoolsbybackend.entity.SubjectEntity;
import com.example.schoolsbybackend.exception.ClassAlreadyExistsException;
import com.example.schoolsbybackend.exception.SubjectAlreadyExistsException;
import com.example.schoolsbybackend.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @PostMapping
    public ResponseEntity create(@RequestBody ClassEntity cl){
        try {
            classService.create(cl);
            return ResponseEntity.ok("Новый класс сохранен.");
        }
        catch (ClassAlreadyExistsException e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return  ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }
    @GetMapping
    public ResponseEntity getAllClasses() {
        try {
            return ResponseEntity.ok(classService.getAllClasses());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClass(@PathVariable Long id) {
        try {
            classService.delete(id);
            return ResponseEntity.ok("Класс успешно удален!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
