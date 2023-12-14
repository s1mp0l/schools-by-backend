package com.example.schoolsbybackend.service;

import com.example.schoolsbybackend.entity.SettingEntity;
import com.example.schoolsbybackend.entity.UserEntity;
import com.example.schoolsbybackend.exception.SettingNotFoundException;
import com.example.schoolsbybackend.model.Lesson;
import com.example.schoolsbybackend.model.Setting;
import com.example.schoolsbybackend.repository.SettingRepo;
import com.example.schoolsbybackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SettingService {
    @Autowired
    private SettingRepo settingRepo;

    @Autowired
    private UserRepo userRepo;

    public Setting createSetting(SettingEntity setting, Long user_id) throws SettingNotFoundException {
        Optional<UserEntity> userEntity = userRepo.findById(user_id);
        if(userEntity.isEmpty()) {throw new SettingNotFoundException("Пользователь с ID " + user_id + " не найден.");}
        setting.setUser(userEntity.get());
        return Setting.toModel(settingRepo.save(setting));
    }

    public Setting getById(Long id) throws SettingNotFoundException {
        Optional<SettingEntity> setting =  settingRepo.findById(id);
        if (setting.isEmpty()) throw new SettingNotFoundException("Настройки не найдены.");
        return Setting.toModel(setting.get());
    }

    public List<Setting> getAllSetting() throws SettingNotFoundException {
        if(settingRepo.findAll() == null){
            throw new SettingNotFoundException("Настройки не найдены.");
        }
        List<Setting> settings = settingRepo.findAll().stream().map(Setting::toModel).toList();
        return settings;
    }

    public Setting setSettingsByUser(Long user_id, SettingEntity setting) throws SettingNotFoundException{
        Optional<UserEntity> userEntity = userRepo.findById(user_id);
        if(userEntity.isEmpty()) throw new SettingNotFoundException("Пользователь с ID " + user_id + " не найден.");
        SettingEntity userSetting = settingRepo.findByUserId(user_id);

        if(setting.getDarkTheme()!=null)userSetting.setDarkTheme(setting.getDarkTheme());
        if(setting.getFontSize()!=null)userSetting.setFontSize(setting.getFontSize());
        if(setting.getLang()!=null)userSetting.setLang(setting.getLang());
        if(setting.getNotificationsOn()!=null)userSetting.setNotificationsOn(setting.getNotificationsOn());
        if(setting.getPushNotificationsOn()!=null)userSetting.setPushNotificationsOn(setting.getPushNotificationsOn());
        settingRepo.save(userSetting);
        return Setting.toModel(userSetting);
    }

    public void delete(Long id) throws SettingNotFoundException {
        Optional<SettingEntity> obj =  settingRepo.findById(id);
        if (obj.isEmpty()) throw new SettingNotFoundException("Настройки не найдены.");

        settingRepo.deleteById(id);
    }
}
