package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.AbsenceEntity;
import com.example.schoolsbybackend.entity.LessonEntity;
import com.example.schoolsbybackend.entity.StudentEntity;
import com.example.schoolsbybackend.repository.AbsenceRepo;
import com.example.schoolsbybackend.repository.LessonRepo;
import com.example.schoolsbybackend.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AbsenceService {

    @Autowired
    private AbsenceRepo absenceRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private LessonRepo lessonRepo;

    public AbsenceEntity create(Long studentId, Long lessonId) throws Exception {
        StudentEntity student = studentRepo.findById(studentId)
                .orElseThrow(() -> new Exception("Не найден студент с таким ID: " + studentId));
        LessonEntity lesson = lessonRepo.findById(lessonId)
                .orElseThrow(() -> new Exception("Не найден урок с таким ID: " + lessonId));

        AbsenceEntity absence = new AbsenceEntity();
        absence.setStudent(student);
        absence.setLesson(lesson);
        absence.setAbsence(false);
        return absenceRepo.save(absence);
    }

    public AbsenceEntity getById(Long id) throws Exception {
        Optional<AbsenceEntity> abs =  absenceRepo.findById(id);
        if (abs.isEmpty()) throw new Exception("Посещаемость с таким id не найдено.");
        return abs.get();
    }

    public List<AbsenceEntity> getAllAbsence() throws   Exception {
        if(absenceRepo.findAll() == null){
            throw new Exception("Посещения не найдены");
        }
        List<AbsenceEntity> abs = absenceRepo.findAll();
        return abs;
    }

    public void delete(Long id) throws Exception {
        Optional<AbsenceEntity> note =  absenceRepo.findById(id);
        if (note.isEmpty()) throw new Exception("Посещение с таким id не найдено.");

        absenceRepo.deleteById(id);
    }

}
