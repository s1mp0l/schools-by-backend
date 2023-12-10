package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.LessonEntity;
import com.example.schoolsbybackend.entity.NoteEntity;
import com.example.schoolsbybackend.entity.UserEntity;
import com.example.schoolsbybackend.exception.LessonNotFoundException;
import com.example.schoolsbybackend.exception.NoLessonsFoundException;
import com.example.schoolsbybackend.exception.UserNotFoundException;
import com.example.schoolsbybackend.model.Lesson;
import com.example.schoolsbybackend.model.Note;
import com.example.schoolsbybackend.repository.NoteRepo;
import com.example.schoolsbybackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    private NoteRepo noteRepo;
    @Autowired
    private UserRepo userRepo;

    public Note createNote(NoteEntity note, Long user_id) throws UserNotFoundException {
        Optional<UserEntity> user = userRepo.findById(user_id);
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

    public Note getById(Long id) throws Exception {
        Optional<NoteEntity> note =  noteRepo.findById(id);
        if (note.isEmpty()) throw new Exception("Уведомление с таким id не найдено.");
        return Note.toModel(note.get());
    }

    public List<Note> getAllNotes() throws Exception {
        if(noteRepo.findAll() == null){
            throw new Exception("Уведомления не найдены");
        }
        List<Note> note = noteRepo.findAll().stream().map(Note::toModel).collect(Collectors.toList());
        return note;
    }

    public void delete(Long id) throws Exception {
        Optional<NoteEntity> note =  noteRepo.findById(id);
        if (note.isEmpty()) throw new Exception("Уведомление с таким id не найдено.");

        noteRepo.deleteById(id);
    }
    public void deleteAllByUserId(Long userId) throws Exception {
        List<NoteEntity> notes = noteRepo.findByUserId(userId);
        if (notes.isEmpty()) {
            throw new Exception("No notes found for the user with ID: " + userId);
        }
        noteRepo.deleteAll(notes);
    }
}
