package com.example.schoolsbybackend.controller;

import com.example.schoolsbybackend.entity.StudentEntity;
import com.example.schoolsbybackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity createStudent(@RequestBody StudentEntity student,
                                        @RequestParam Long user_id,
                                        @RequestParam Long class_id) {
        try {
            return ResponseEntity.ok(studentService.createStudent(student, user_id, class_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @PutMapping("/{id}/addParent")
//    public ResponseEntity addStudentParent(@PathVariable Long id,
//                                           @RequestParam Long parent_id) {
//        try {
//            return ResponseEntity.ok(studentService.addParent(id, parent_id));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @GetMapping
    public ResponseEntity getAllStudents() {
        try {
            return ResponseEntity.ok(studentService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity getOneStudent(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(studentService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        try {
            studentService.delete(id);
            return ResponseEntity.ok("Ученик успешно удален!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

