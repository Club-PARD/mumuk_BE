package com.pard.preferences.controller;

import com.pard.preferences.dto.PrefDto;
import com.pard.preferences.entity.Preferences;
import com.pard.preferences.service.PrefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pref")
public class PrefController {

    @Autowired
    private PrefService prefService;

    @GetMapping("/{uid}")
    public Preferences getPreferencesByUid(@PathVariable String uid) {
        return prefService.getUserByUid(uid);
    }
    @PostMapping("/{uid}")
    public void createPref(@RequestBody PrefDto.Create preferences, @PathVariable String uid) {
        prefService.createPref(preferences, uid);
    }
    @PutMapping("/{prefId}")
    public Preferences updatePreferences(@PathVariable Long prefId, @RequestBody Preferences newPreferences) {
        return prefService.updatePreferences(prefId, newPreferences);
    }

}
