package com.pard.preferences.controller;

import com.pard.preferences.dto.PrefDto;
import com.pard.preferences.entity.Preferences;
import com.pard.preferences.service.PrefService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pref")
public class PrefController {

    @Autowired
    private PrefService prefService;

    @GetMapping("/{uid}")
    @Operation(summary = "특정 유저의 선호도 조회", description = "유저 uid 필요")
    public Preferences getPreferencesByUid(@PathVariable String uid) {
        return prefService.getUserByUid(uid);
    }
    @PostMapping("/{uid}")
    @Operation(summary = "선호도 생성", description = "유저 uid 필요")
    public void createPref(@RequestBody PrefDto.Create preferences, @PathVariable String uid) {
        prefService.createPref(preferences, uid);
    }
    @PutMapping("/{prefId}")
    @Operation(summary = "prefId로 선호도 업데이트", description = "해당 선호도 id 필요 모든 필드를 넘겨주어야함 아니면 값에 0 들어감")
    public Preferences updatePreferences(@PathVariable Long prefId, @RequestBody Preferences newPreferences) {
        return prefService.updatePreferences(prefId, newPreferences);
    }

}
