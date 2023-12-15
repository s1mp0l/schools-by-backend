package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.*;
import com.example.schoolsbybackend.exception.*;
import com.example.schoolsbybackend.model.Mark;
import com.example.schoolsbybackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @Autowired
    private SubjectRepo subjectRepo;

    @Autowired
    private YearRepo yearRepo;


    public Mark create(MarkEntity mark, Long lesson_id, Long student_id) throws Exception {
        LessonEntity lesson = lessonRepo.findById(lesson_id)
                .orElseThrow(() -> new Exception("Урока с таким id не найдено."));
        StudentEntity student = studentRepo.findById(student_id)
                .orElseThrow(() -> new Exception("Студента с таким id не найдено."));
        mark.setLesson(lesson);
        mark.setStudent(student);
        mark.setSubject(lesson.getSubject().getId());

        SemesterEntity semester =
                semesterRepo.findAll().stream().filter(s
                        -> !lesson.getDate().isBefore(s.getStart_date())
                        && !lesson.getDate().isAfter(s.getEnd_date())).findFirst().orElseThrow(()
                        -> new Exception("Нет подходящего по датам семестра для данной оценки."));

        mark.setSemester(semester);
        markRepo.save(mark);

        return Mark.toModel(mark);
    }

    public Mark createSem(MarkEntity mark, Long lesson_id, Long student_id) throws Exception {
        LessonEntity lesson = lessonRepo.findById(lesson_id)
                .orElseThrow(() -> new Exception("Урока с таким id не найдено."));
        StudentEntity student = studentRepo.findById(student_id)
                .orElseThrow(() -> new Exception("Студента с таким id не найдено."));
        mark.setLesson(lesson);
        mark.setStudent(student);
        mark.setSubject(lesson.getSubject().getId());

        SemesterEntity semester =
                semesterRepo.findAll().stream().filter(s
                        -> !lesson.getDate().isBefore(s.getStart_date())
                        && !lesson.getDate().isAfter(s.getEnd_date())).findFirst().orElseThrow(()
                        -> new Exception("Нет подходящего по датам семестра для данной оценки."));

        mark.setSemester(semester);
        mark.setIs_sem(true);

        markRepo.save(mark);
        return Mark.toModel(mark);
    }

    public Mark createYear(MarkEntity mark, Long lesson_id, Long student_id) throws Exception {
        LessonEntity lesson = lessonRepo.findById(lesson_id)
                .orElseThrow(() -> new Exception("Урока с таким id не найдено."));
        StudentEntity student = studentRepo.findById(student_id)
                .orElseThrow(() -> new Exception("Студента с таким id не найдено."));
        mark.setLesson(lesson);
        mark.setStudent(student);
        mark.setSubject(lesson.getSubject().getId());

        SemesterEntity semester =
                semesterRepo.findAll().stream().filter(s
                        -> !lesson.getDate().isBefore(s.getStart_date())
                        && !lesson.getDate().isAfter(s.getEnd_date())).findFirst().orElseThrow(()
                        -> new Exception("Нет подходящего по датам семестра для данной оценки."));

        mark.setSemester(semester);
        mark.setIs_year(true);

        markRepo.save(mark);
        return Mark.toModel(mark);
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

    public List<Mark> getAllStudentMarks(Long student_id) throws  Exception {
        Long semester_id = getCurrentSemesterId();

        StudentEntity student = studentRepo.findById(student_id)
                .orElseThrow(() -> new Exception("Студента с таким id не найдено."));
        SemesterEntity semester = semesterRepo.findById(semester_id)
                .orElseThrow(() -> new Exception("Семестра с таким id не найдено."));

        List<MarkEntity> marks = markRepo.findAllByStudentAndSemester(student, semester);
        if(marks == null){
            throw new MarkNotFoundException("Оценки не найдены.!");
        }

        List<MarkEntity> filteredMarks = marks.stream().filter(MarkEntity::not_is_sem_filter).toList();
        return filteredMarks.stream().map(Mark::toModel).collect(Collectors.toList());
    }

    public void delete(Long id) throws MarkNotFoundException {
        Optional<MarkEntity> mk =  markRepo.findById(id);
        if (mk.isEmpty()) throw new MarkNotFoundException("Оценка не найдена.");

        markRepo.deleteById(id);
    }

    public MarkEntity update(Long id, Long newValue) throws MarkNotFoundException {
        Optional<MarkEntity> markOptional = markRepo.findById(id);
        if (!markOptional.isPresent()) {
            throw new MarkNotFoundException("Оценка с ID " + id + " не найдена.");
        }

        MarkEntity mark = markOptional.get();
        mark.setValue(newValue);
        return markRepo.save(mark);
    }

    public List<Mark> findAllByStudentAndSubject(Long student_id, Long subject_id) throws Exception {
        Long semester_id = getCurrentSemesterId(); // Получение ID текущего семестра

        SubjectEntity subject = subjectRepo.findById(subject_id)
                .orElseThrow(() -> new Exception("Предмета с таким id не найдено."));
        StudentEntity student = studentRepo.findById(student_id)
                .orElseThrow(() -> new Exception("Студента с таким id не найдено."));
        SemesterEntity semester = semesterRepo.findById(semester_id)
                .orElseThrow(() -> new Exception("Семестра с таким id не найдено."));

        List<MarkEntity> marks = markRepo.findAllByStudentAndSubjectAndSemester(student, subject_id, semester);
        if(marks == null) {
            throw new Exception("Оценки не найдены.");
        }
        return marks.stream().map(Mark::toModel).collect(Collectors.toList());
    }


    public List<Mark> getAllQuarterlyMarksByStudent(Long student_id) throws Exception {
        Long year_id = getCurrentYearId();

        YearEntity year = yearRepo.findById(year_id)
                .orElseThrow(() -> new Exception("Год с таким id не найден."));

        StudentEntity student = studentRepo.findById(student_id)
                .orElseThrow(() -> new Exception("Ученика с таким id не найден."));

        List<SemesterEntity> semesters = year.getSemesters();
        if (semesters == null || semesters.isEmpty()) {
            throw new Exception("В указанном году нет четвертей.");
        }

        List<Mark> allMarks = new ArrayList<>();

        for (SemesterEntity semester : semesters) {
            List<MarkEntity> marks = markRepo.findAllByStudentAndSemester(student, semester);
            List<MarkEntity> filteredMarks = marks.stream().filter(MarkEntity::sem_filter).toList();
            allMarks.addAll(filteredMarks.stream().map(Mark::toModel).toList());
        }

        return allMarks;
    }

    private Long getCurrentYearId() {
        LocalDate currentDate = LocalDate.now();
        List<YearEntity> allYears = yearRepo.findAll();

        for (YearEntity year : allYears) {
            if (!currentDate.isBefore(year.getStartDate()) && !currentDate.isAfter(year.getEndDate())) {
                return year.getId();
            }
        }
        throw new IllegalStateException("Для текущей даты не найден учебный год");
    }

    private Long getCurrentSemesterId() {
        LocalDate currentDate = LocalDate.now();
        List<SemesterEntity> allSemesters = semesterRepo.findAll();

        for (SemesterEntity semester : allSemesters) {
            if (!currentDate.isBefore(semester.getStart_date()) && !currentDate.isAfter(semester.getEnd_date())) {
                return semester.getId();
            }
        }
        throw new IllegalStateException("Не найден текущий семестр");
    }
}
