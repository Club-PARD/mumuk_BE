package com.pard.preferences.service;

import com.pard.preferences.dto.PrefDto;
import com.pard.preferences.entity.ExceptionalFood;
import com.pard.preferences.entity.FoodType;
import com.pard.preferences.entity.Preferences;
import com.pard.preferences.repo.FoodTypeRepo;
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
    private final ExceptionalFoodRepo exceptionalFoodRepo;

    @Autowired
    private final FoodTypeRepo foodTypeRepo;

    public void createPref(PrefDto.Create dto, String uid) {
        User user = userRepo.findByUid(uid)
                .orElseThrow(() -> new UserNotFoundException("User not found with uid: " + uid));

        FoodType foodType = foodTypeRepo.findById(dto.getFoodTypeId())
                .orElseThrow(() -> new RuntimeException("Food type not found: " + dto.getFoodTypeId()));

        Preferences preferences = Preferences.toEntity(dto, foodType);

        List<ExceptionalFood> exceptionalFoods = dto.getExceptionalFoods().stream()
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
        preferences.setSpicyType(newPreferences.isSpicyType());
        preferences.setKoreanFood(newPreferences.getKoreanFood());
        preferences.setJapaneseFood(newPreferences.getJapaneseFood());
        preferences.setChineseFood(newPreferences.getChineseFood());
        preferences.setWesternFood(newPreferences.getWesternFood());
        preferences.setSoutheastAsianFood(newPreferences.getSoutheastAsianFood());
        preferences.setElseFood(newPreferences.getElseFood());

        FoodType foodType = foodTypeRepo.findById(newPreferences.getFoodType().getId())
                .orElseThrow(() -> new RuntimeException("Food type not found: " + newPreferences.getFoodType().getId()));
        preferences.setFoodType(foodType);
        preferences.setExceptionalFoods(newPreferences.getExceptionalFoods());
        return prefRepo.save(preferences);
    }





}
