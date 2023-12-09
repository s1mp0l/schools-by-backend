package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.*;
import com.example.schoolsbybackend.model.Student;
import com.example.schoolsbybackend.model.Teacher;
import com.example.schoolsbybackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ClassRepo classRepo;

    public Student createStudent(StudentEntity student, Long user_id, Long class_id) throws Exception {
        Optional<UserEntity> userEntity = userRepo.findById(user_id);
        if(userEntity.isEmpty()) {throw new Exception("Пользователь с ID " + user_id + " не найден.");}
        student.setUser(userEntity.get());

        Optional<ClassEntity> classEntity = classRepo.findById(class_id);
        if(classEntity.isEmpty()) {throw new Exception("Класс с ID " + class_id + " не найден.");}
        student.setNclass(classEntity.get());

        return Student.toModel(studentRepo.save(student));
    }

//    public StudentEntity addParent(Long id, Long class_id) throws TeacherNotFoundException, ClassNotFoundException {
//        Optional<TeacherEntity> teacherOpt =  teacherRepo.findById(id);
//        if (teacherOpt.isEmpty()) throw new TeacherNotFoundException("Учитель не найден.");
//
//        return Teacher.toModel(teacher);
//    }

    public Student getById(Long id) throws Exception {
        Optional<StudentEntity> student =  studentRepo.findById(id);
        if (student.isEmpty()) throw new Exception("Ученик не найден.");
        return Student.toModel(student.get());
    }

    public List<Student> getAll() throws Exception {
        if(studentRepo.findAll() == null){
            throw new Exception("Учеников не найдено!");
        }
        return studentRepo.findAll().stream().map(Student::toModel).collect(Collectors.toList());
    }

    public void delete(Long id) throws Exception {
        Optional<StudentEntity> student =  studentRepo.findById(id);
        if (student.isEmpty()) throw new Exception("Ученик не найден.");

        studentRepo.deleteById(id);
    }
}