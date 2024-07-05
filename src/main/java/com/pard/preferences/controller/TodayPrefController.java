package com.pard.preferences.controller;

import com.pard.preferences.dto.TodayPrefDto;
import com.pard.preferences.service.TodayPrefService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/daily")
@RequiredArgsConstructor
public class TodayPrefController {
    private final TodayPrefService todayPrefService;

    @PostMapping("/{userName}")
    public ResponseEntity<Void> saveOrUpdateTodayPreferences(@PathVariable String userName, @RequestBody TodayPrefDto.Create createDto) {
        todayPrefService.saveOrUpdateTodayPref(userName, createDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}