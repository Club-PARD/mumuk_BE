package com.pard.preferences.service;

import com.pard.preferences.dto.PrefDto;
import com.pard.preferences.entity.Preferences;
import com.pard.preferences.repo.PrefRepo;
import com.pard.user.entity.User;
import com.pard.user.exception.UserNotFoundException;
import com.pard.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrefService {

    @Autowired
    private final PrefRepo prefRepo;

    @Autowired
    private final UserRepo userRepo;

    public void createPref(PrefDto.Create dto, String uid) {
        User user = userRepo.findByUid(uid)
                .orElseThrow(() -> new UserNotFoundException("User not found with uid: " + uid));
        Preferences preferences = Preferences.toEntity(dto);
        user.setPreferences(preferences);
        userRepo.save(user);

    }


    public Preferences getUserByUid(String uid) {
        User user = userRepo.findByUid(uid).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getPreferences();
    }


    public Preferences updatePreferences(Long id, Preferences newPreferences) {
        Preferences preferences = prefRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Preferences not found"));
        preferences.setKoreanFood(newPreferences.getKoreanFood());
        preferences.setJapaneseFood(newPreferences.getJapaneseFood());
        preferences.setChineseFood(newPreferences.getChineseFood());
        preferences.setWesternFood(newPreferences.getWesternFood());
        preferences.setSoutheastAsianFood(newPreferences.getSoutheastAsianFood());
        preferences.setMeat(newPreferences.getMeat());
        preferences.setSeafood(newPreferences.getSeafood());
        preferences.setRice(newPreferences.getRice());
        preferences.setNoodles(newPreferences.getNoodles());
        preferences.setSoup(newPreferences.getSoup());
        preferences.setGrilled(newPreferences.getGrilled());
        preferences.setHealthyFood(newPreferences.getHealthyFood());
        preferences.setFastFood(newPreferences.getFastFood());
        preferences.setSpicyFood(newPreferences.getSpicyFood());
        preferences.setExceptionalFood(newPreferences.getExceptionalFood());
        preferences.setAllergies(newPreferences.getAllergies());
        return prefRepo.save(preferences);
    }





}
