package com.example.schoolsbybackend.controller;

import com.example.schoolsbybackend.entity.SubjectEntity;
import com.example.schoolsbybackend.exception.SubjectAlreadyExistsException;
import com.example.schoolsbybackend.repository.SubjectRepo;
import com.example.schoolsbybackend.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public ResponseEntity create(@RequestBody SubjectEntity subj){
        try {
            subjectService.create(subj);
            return ResponseEntity.ok("Новый предмет сохранен.");
        }
        catch (SubjectAlreadyExistsException e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return  ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }


}
