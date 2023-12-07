package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.SettingEntity;
import com.example.schoolsbybackend.entity.UserEntity;
import com.example.schoolsbybackend.exception.SettingNotFoundException;
import com.example.schoolsbybackend.repository.SettingRepo;
import com.example.schoolsbybackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SettingService {
    @Autowired
    private SettingRepo settingRepo;

    @Autowired
    private UserRepo userRepo;

    public SettingEntity create(SettingEntity setting) throws SettingNotFoundException {
        Long userId = setting.getUser().getId();
        Optional<UserEntity> userEntity = userRepo.findById(userId);
        if(userEntity.isEmpty()) {
            throw new SettingNotFoundException("Пользователь с ID " + userId + " не найден.");
        }
        setting.setUser(userEntity.get());
        return settingRepo.save(setting);
    }

    public SettingEntity getById(Long id) throws SettingNotFoundException {
        Optional<SettingEntity> obj =  settingRepo.findById(id);
        if (obj.isEmpty()) throw new SettingNotFoundException("Настройки не найдены.");
        return obj.get();
    }

    public List<SettingEntity> getAllLessons() throws SettingNotFoundException {
        if(settingRepo.findAll() == null){
            throw new SettingNotFoundException("Настройки не найдены.");
        }
        return settingRepo.findAll();
    }

    public void delete(Long id) throws SettingNotFoundException {
        Optional<SettingEntity> obj =  settingRepo.findById(id);
        if (obj.isEmpty()) throw new SettingNotFoundException("Настройки не найдены.");

        settingRepo.deleteById(id);
    }
}
