package com.example.schoolsbybackend.controller;

import com.example.schoolsbybackend.entity.MarkEntity;
import com.example.schoolsbybackend.exception.MarkAlreadyExistsException;
import com.example.schoolsbybackend.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/marks")
public class MarkController {

    @Autowired
    private MarkService markService;

    @PostMapping
    public ResponseEntity create(@RequestBody MarkEntity mk){
        try {
            markService.create(mk);
            return ResponseEntity.ok("Новая оценка сохранена.");
        }
        catch (MarkAlreadyExistsException e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return  ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }
    @GetMapping
    public ResponseEntity getAllClasses() {
        try {
            return ResponseEntity.ok(markService.getAllMarks());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClass(@PathVariable Long id) {
        try {
            markService.delete(id);
            return ResponseEntity.ok("Оценка успешно удалена!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
