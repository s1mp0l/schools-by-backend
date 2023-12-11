package com.example.schoolsbybackend.controller;

import com.example.schoolsbybackend.entity.MarkEntity;
import com.example.schoolsbybackend.exception.MarkAlreadyExistsException;
import com.example.schoolsbybackend.model.Mark;
import com.example.schoolsbybackend.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marks")
public class MarkController {

    @Autowired
    private MarkService markService;

    @PostMapping
    public ResponseEntity createMark(@RequestBody MarkEntity mark,
                                     @RequestParam Long student_id,
                                     @RequestParam Long lesson_id) {
        try {
            markService.create(mark, lesson_id, student_id);
            return ResponseEntity.ok("Новая оценка сохранена.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/sem")
    public ResponseEntity createSemMark(@RequestBody MarkEntity mark,
                                        @RequestParam Long student_id,
                                        @RequestParam Long lesson_id) {
        try {
            markService.createSem(mark, lesson_id, student_id);
            return ResponseEntity.ok("Новая оценка сохранена.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/year")
    public ResponseEntity createYearMark(@RequestBody MarkEntity mark,
                                         @RequestParam Long student_id,
                                         @RequestParam Long lesson_id) {
        try {
            markService.createYear(mark, lesson_id, student_id);
            return ResponseEntity.ok("Новая оценка сохранена.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity getAllMarks() {
        try {
            return ResponseEntity.ok(markService.getAllMarks());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{mark_id}")
    public ResponseEntity getAllStudentMarks(@PathVariable Long mark_id) {
        try {
            return ResponseEntity.ok(markService.getAllStudentMarks(mark_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMark(@PathVariable Long id) {
        try {
            markService.delete(id);
            return ResponseEntity.ok("Оценка успешно удалена!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateMarkValue(@PathVariable Long id,
                                          @RequestParam Long value) {
        try {
            MarkEntity updatedMark = markService.update(id, value);
            return ResponseEntity.ok("Оценка успешно обновлена. Новое значение: " + updatedMark.getValue());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/semester_marks")
    public ResponseEntity getMarksByLessonStudentSemester(@RequestParam Long student_id,
                                                          @RequestParam Long subject_id,
                                                          @RequestParam Long semester_id) {
        try {
            List<Mark> marks = markService.findAllByStudentAndSubjectAndSemester(student_id, subject_id, semester_id);
            return ResponseEntity.ok(marks);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/quarterly")
    public ResponseEntity getAllQuarterlyMarksByStudentAndYear(@RequestParam Long student_id,
                                                               @RequestParam Long year_id) {
        try {
            List<Mark> marks = markService.getAllQuarterlyMarksByStudentAndYear(student_id, year_id);
            return ResponseEntity.ok(marks);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
