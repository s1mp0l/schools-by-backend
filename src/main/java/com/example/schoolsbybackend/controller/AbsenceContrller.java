package com.example.schoolsbybackend.controller;

import com.example.schoolsbybackend.entity.AbsenceEntity;
import com.example.schoolsbybackend.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/absence")
public class AbsenceContrller {
    @Autowired
    private AbsenceService absenceService;

    @PostMapping
    public ResponseEntity<?> createAbsence(@RequestParam Long student_id, @RequestParam Long lesson_id) {
        try {
            return ResponseEntity.ok(absenceService.create(student_id, lesson_id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public AbsenceEntity getAbsenceById(@PathVariable Long id) throws Exception {
        return absenceService.getById(id);
    }

    @GetMapping
    public List<AbsenceEntity> getAllAbsences() throws Exception {
        return absenceService.getAllAbsence();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbsence(@PathVariable Long id) throws Exception {
        absenceService.delete(id);
        return ResponseEntity.ok().build();
    }
}
