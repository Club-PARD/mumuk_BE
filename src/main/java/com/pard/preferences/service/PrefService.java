package com.pard.preferences.service;

import com.pard.preferences.dto.PrefDto;
import com.pard.preferences.entity.Allergy;
import com.pard.preferences.entity.ExceptionalFood;
import com.pard.preferences.entity.Preferences;
import com.pard.preferences.repo.AllergyRepo;
import com.pard.preferences.repo.ExceptionalFoodRepo;
import com.pard.preferences.repo.PrefRepo;
import com.pard.user.entity.User;
import com.pard.user.exception.UserNotFoundException;
import com.pard.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrefService {

    @Autowired
    private final PrefRepo prefRepo;

    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private final AllergyRepo allergyRepo;

    @Autowired
    private final ExceptionalFoodRepo exceptionalFoodRepo;

    public void createPref(PrefDto.Create dto, String uid) {
        User user = userRepo.findByUid(uid)
                .orElseThrow(() -> new UserNotFoundException("User not found with uid: " + uid));
        Preferences preferences = Preferences.toEntity(dto);

        List<Allergy> allergies = dto.getAllergies().stream()
                .map(allergyId -> allergyRepo.findById(Integer.parseInt(allergyId))
                        .orElseThrow(() -> new RuntimeException("Allergy not found: " + allergyId)))
                .collect(Collectors.toList());
        preferences.setAllergies(allergies);

        List<ExceptionalFood> exceptionalFoods = dto.getExceptionalFood().stream()
                .map(foodId -> exceptionalFoodRepo.findById(Integer.parseInt(foodId))
                        .orElseThrow(() -> new RuntimeException("Exceptional food not found: " + foodId)))
                .collect(Collectors.toList());
        preferences.setExceptionalFoods(exceptionalFoods);

        preferences.setUser(user);
        prefRepo.save(preferences);
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

        return prefRepo.save(preferences);
    }





}
