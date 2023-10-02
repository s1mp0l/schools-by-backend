package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.NoteEntity;
import com.example.schoolsbybackend.entity.UserEntity;
import com.example.schoolsbybackend.exception.UserNotFoundException;
import com.example.schoolsbybackend.model.Note;
import com.example.schoolsbybackend.repository.NoteRepo;
import com.example.schoolsbybackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepo noteRepo;
    @Autowired
    private UserRepo userRepo;

    public Note createNote(NoteEntity note, Long userId) throws UserNotFoundException {
        Optional<UserEntity> user = userRepo.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Пользователь с таким id не найден.");
        }
        note.setUser(user.get());
        return Note.toModel(noteRepo.save(note));
    }

    public Note setSeenNote(Long id) throws Exception {
        Optional<NoteEntity> noteOpt = noteRepo.findById(id);
        if (noteOpt.isEmpty()) {
            throw new Exception("Пользователь с таким id не найден.");
        }
        NoteEntity note = noteOpt.get();
        note.setSeenStatus(!note.getSeenStatus());
        return Note.toModel(noteRepo.save(note));
    }
}
