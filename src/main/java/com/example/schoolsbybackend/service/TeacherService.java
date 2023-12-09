package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.*;
import com.example.schoolsbybackend.exception.*;
import com.example.schoolsbybackend.exception.ClassNotFoundException;
import com.example.schoolsbybackend.model.Teacher;
import com.example.schoolsbybackend.repository.ClassRepo;
import com.example.schoolsbybackend.repository.SubjectRepo;
import com.example.schoolsbybackend.repository.TeacherRepo;
import com.example.schoolsbybackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private ClassRepo classRepo;

    @Autowired
    private SubjectRepo subjectRepo;

    @Autowired
    private UserRepo userRepo;

    public TeacherEntity createTeacher(TeacherEntity teacher, Long user_id) throws SettingNotFoundException {
        Optional<UserEntity> userEntity = userRepo.findById(user_id);
        if(userEntity.isEmpty()) {throw new SettingNotFoundException("Пользователь с ID " + user_id + " не найден.");}
        teacher.setUser(userEntity.get());
        return teacherRepo.save(teacher);
    }

    public Teacher addClass(Long id, Long class_id) throws TeacherNotFoundException, ClassNotFoundException {
        Optional<TeacherEntity> teacherOpt =  teacherRepo.findById(id);
        if (teacherOpt.isEmpty()) throw new TeacherNotFoundException("Учитель не найден.");

        Optional<ClassEntity> NclassOpt = classRepo.findById(class_id);
        if (NclassOpt.isEmpty()) throw new ClassNotFoundException("Класс с таким id не найден.");

        TeacherEntity teacher = teacherOpt.get();
        ClassEntity Nclass = NclassOpt.get();
        teacher.getClasses().add(Nclass);
        Nclass.getTeachers().add(teacher);
        teacherRepo.save(teacher);
        classRepo.save(Nclass);
        return Teacher.toModel(teacher);
    }

    public Teacher addSubject(Long id, Long subject_id) throws TeacherNotFoundException, SubjectNotFoundException {
        Optional<TeacherEntity> teacherOpt =  teacherRepo.findById(id);
        if (teacherOpt.isEmpty()) throw new TeacherNotFoundException("Учитель не найден.");

        Optional<SubjectEntity> subjectOpt = subjectRepo.findById(subject_id);
        if (subjectOpt.isEmpty()) throw new SubjectNotFoundException("Премдет с таким id не найден.");

        TeacherEntity teacher = teacherOpt.get();
        SubjectEntity subject = subjectOpt.get();
        teacher.getSubjects().add(subject);
        subject.getTeachers().add(teacher);
        teacherRepo.save(teacher);
        subjectRepo.save(subject);
        return Teacher.toModel(teacher);
    }

    public Teacher getById(Long id) throws TeacherNotFoundException {
        Optional<TeacherEntity> teacher =  teacherRepo.findById(id);
        if (teacher.isEmpty()) throw new TeacherNotFoundException("Учитель не найден.");
        return Teacher.toModel(teacher.get());
    }

    public List<Teacher> getAllTeachers() throws TeacherNotFoundException {
        if(teacherRepo.findAll() == null){
            throw new TeacherNotFoundException("Предметов не найдено!");
        }
        return teacherRepo.findAll().stream().map(Teacher::toModel).collect(Collectors.toList());
    }

    public void delete(Long id) throws TeacherNotFoundException {
        Optional<TeacherEntity> teacher =  teacherRepo.findById(id);
        if (teacher.isEmpty()) throw new TeacherNotFoundException("Предмет не найден.");

        teacherRepo.deleteById(id);
    }
}
