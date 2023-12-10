package com.example.schoolsbybackend.controller;

import com.example.schoolsbybackend.entity.ParentEntity;
import com.example.schoolsbybackend.entity.StudentEntity;
import com.example.schoolsbybackend.service.ParentService;
import com.example.schoolsbybackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/parents")
public class ParentController {

    @Autowired
    private ParentService parentService;

    @PostMapping("/{id}/addParent")
    public ResponseEntity createStudent(@RequestBody ParentEntity parent,
                                        @RequestParam Long user_id) {
        try {
            return ResponseEntity.ok(parentService.createParent(parent, user_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllStudents() {
        try {
            return ResponseEntity.ok(parentService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity getOneStudent(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(parentService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        try {
            parentService.delete(id);
            return ResponseEntity.ok("Родитель успешно удален!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}



