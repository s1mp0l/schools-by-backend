package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.LessonEntity;
import com.example.schoolsbybackend.entity.MarkEntity;
import com.example.schoolsbybackend.entity.SemesterEntity;
import com.example.schoolsbybackend.entity.StudentEntity;
import com.example.schoolsbybackend.exception.*;
import com.example.schoolsbybackend.model.Lesson;
import com.example.schoolsbybackend.model.Mark;
import com.example.schoolsbybackend.repository.LessonRepo;
import com.example.schoolsbybackend.repository.MarkRepo;
import com.example.schoolsbybackend.repository.SemesterRepo;
import com.example.schoolsbybackend.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MarkService {

    @Autowired
    private MarkRepo markRepo;

    @Autowired
    private LessonRepo lessonRepo;

    @Autowired
    private SemesterRepo semesterRepo;

    @Autowired
    private StudentRepo studentRepo;

    public MarkEntity create(MarkEntity mark, Long lesson_id, Long student_id) throws Exception {
        LessonEntity lesson = lessonRepo.findById(lesson_id)
                .orElseThrow(() -> new Exception("Урока с таким id не найдено."));
        StudentEntity student = studentRepo.findById(student_id)
                .orElseThrow(() -> new Exception("Студента с таким id не найдено."));

        mark.setLesson(lesson);
        mark.setStudent(student);

        SemesterEntity semester =
                semesterRepo.findAll().stream().filter(s
                -> !lesson.getDate().isBefore(s.getStart_date())
                && !lesson.getDate().isAfter(s.getEnd_date())).findFirst().orElseThrow(()
                -> new Exception("Нет подходящего по датам семестра для данной оценки."));

        mark.setSemester(semester);

        return markRepo.save(mark);
    }
    public Mark getById(Long id) throws MarkNotFoundException {
        Optional<MarkEntity> mk =  markRepo.findById(id);
        if (mk.isEmpty()) throw new MarkNotFoundException("Оценка не найдена.");
        return Mark.toModel(mk.get());
    }

    public List<Mark> getAllMarks() throws MarkNotFoundException{
        List<MarkEntity> marks = markRepo.findAll();
        if(marks == null){
            throw new MarkNotFoundException("Оценки не найдены.!");
        }
        return marks.stream().map(Mark::toModel).collect(Collectors.toList());
    }

    public List<Mark> getAllStudentMarks(Long student_id) throws MarkNotFoundException, Exception {
        StudentEntity student = studentRepo.findById(student_id)
                .orElseThrow(() -> new Exception("Студента с таким id не найдено."));

        List<MarkEntity> marks = markRepo.findAllByStudent(student);
        if(marks == null){
            throw new MarkNotFoundException("Оценки не найдены.!");
        }
        return marks.stream().map(Mark::toModel).collect(Collectors.toList());
    }

    public void delete(Long id) throws MarkNotFoundException {
        Optional<MarkEntity> mk =  markRepo.findById(id);
        if (mk.isEmpty()) throw new MarkNotFoundException("Оценка не найдена.");

        markRepo.deleteById(id);
    }
}
