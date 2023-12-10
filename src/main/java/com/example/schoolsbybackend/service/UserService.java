package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.ParentEntity;
import com.example.schoolsbybackend.entity.StudentEntity;
import com.example.schoolsbybackend.entity.TeacherEntity;
import com.example.schoolsbybackend.entity.UserEntity;
import com.example.schoolsbybackend.exception.TeacherNotFoundException;
import com.example.schoolsbybackend.exception.UserAlreadyExistsException;
import com.example.schoolsbybackend.exception.UserNotFoundException;
import com.example.schoolsbybackend.interfaces.PersonInterface;
import com.example.schoolsbybackend.model.Parent;
import com.example.schoolsbybackend.model.Student;
import com.example.schoolsbybackend.model.Teacher;
import com.example.schoolsbybackend.model.User;
import com.example.schoolsbybackend.repository.ParentRepo;
import com.example.schoolsbybackend.repository.StudentRepo;
import com.example.schoolsbybackend.repository.TeacherRepo;
import com.example.schoolsbybackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ParentRepo parentRepo;

    public Long registration(UserEntity user) throws UserAlreadyExistsException {
        if (userRepo.findByLogin(user.getLogin()) != null) {
            throw new UserAlreadyExistsException("Пользователь с таким логином уже существует!");
        }

        if (userRepo.findByPhoneNumber(user.getPhoneNumber()) != null) {
            throw new UserAlreadyExistsException("Пользователь с таким номером телефона уже существует!");
        }

        return userRepo.save(user).getId();
    }

    public PersonInterface authorization(String login, String password) throws UserAlreadyExistsException, Exception {
        UserEntity user = userRepo.findByLogin(login);
        if (user == null) {
            throw new UserAlreadyExistsException("Пользователь с таким логином не найден!");
        }

        if (!Objects.equals(user.getPassword(), password)) {
            throw new Exception("Неверный пароль!");
        }


        return switch (user.getUserType()) {
            case "parent" -> {
                ParentEntity parent = parentRepo.findByUser(user);
                yield Parent.toModel(parent);
            }
            case "student" -> {
                StudentEntity student = studentRepo.findByUser(user);
                yield Student.toModel(student);
            }
            case "teacher" -> {
                TeacherEntity teacher = teacherRepo.findByUser(user);
                yield Teacher.toModel(teacher);
            }
            default -> User.toModel(user);
        };
    }

    public List<User> getAll() throws Exception {
        if(userRepo.findAll() == null) {
            throw new TeacherNotFoundException("Пользователей не найдено!");
        }
        return userRepo.findAll().stream().map(User::toModel).collect(Collectors.toList());
    }

    public User getById(Long id) throws UserNotFoundException {
        Optional<UserEntity> user =  userRepo.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("Пользователь не найден.");
        return User.toModel(user.get());
    }

    public void delete(Long id) throws UserNotFoundException {
        Optional<UserEntity> user =  userRepo.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("Пользователь не найден.");

        userRepo.deleteById(id);
    }
}
