package com.example.schoolsbybackend.controller;

import com.example.schoolsbybackend.entity.NoteEntity;
import com.example.schoolsbybackend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public ResponseEntity createNote(
            @RequestBody NoteEntity note,
            @RequestParam Long userId
    ) {
        try {
            return ResponseEntity.ok(noteService.createNote(note, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity setSeenNote(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(noteService.setSeenNote(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
