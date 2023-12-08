package com.example.schoolsbybackend.controller;

import com.example.schoolsbybackend.entity.SettingEntity;
import com.example.schoolsbybackend.exception.SettingNotFoundException;
import com.example.schoolsbybackend.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/settings")
public class SettingController {
    @Autowired
    private SettingService settingService;

    @PostMapping
    public ResponseEntity createSetting(@RequestBody SettingEntity setting,
                                        @RequestParam Long user_id) {
        try {
            SettingEntity createdSetting = settingService.create(setting, user_id);
            return ResponseEntity.ok(createdSetting);
        } catch (SettingNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity getAllSettings() {
        try {
            return ResponseEntity.ok(settingService.getAllSetting());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneSetting(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(settingService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSetting(@PathVariable Long id) {
        try {
            settingService.delete(id);
            return ResponseEntity.ok("Найстройка удалена!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
