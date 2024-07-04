package com.pard.preferences.service;

import com.pard.preferences.dto.PrefDto;
import com.pard.preferences.entity.ExceptionalFood;
import com.pard.preferences.entity.FoodType;
import com.pard.preferences.entity.NotToday;
import com.pard.preferences.entity.Preferences;
import com.pard.preferences.repo.FoodTypeRepo;
import com.pard.preferences.repo.NotTodayRepo;
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
    private final NotTodayRepo notTodayRepo;

    @Autowired
    private final ExceptionalFoodRepo exceptionalFoodRepo;

    @Autowired
    private final FoodTypeRepo foodTypeRepo;

    public void createPref(PrefDto.Create dto, String uid) {
        User user = userRepo.findByUid(uid)
                .orElseThrow(() -> new UserNotFoundException("User not found with uid: " + uid));
        Preferences preferences = Preferences.toEntity(dto);

        List<NotToday> notToday = dto.getNotToday().stream()
                .map(allergyId -> notTodayRepo.findById(Integer.parseInt(allergyId))
                        .orElseThrow(() -> new RuntimeException("Not Today not found: " + allergyId)))
                .collect(Collectors.toList());
        preferences.setNotToday(notToday);

        List<ExceptionalFood> exceptionalFoods = dto.getExceptionalFoods().stream()
                .map(foodId -> exceptionalFoodRepo.findById(Integer.parseInt(foodId))
                        .orElseThrow(() -> new RuntimeException("Exceptional food not found: " + foodId)))
                .collect(Collectors.toList());
        preferences.setExceptionalFoods(exceptionalFoods);

        List<FoodType> foodTypes = dto.getFoodTypes().stream()
                .map(foodId -> foodTypeRepo.findById(Integer.parseInt(foodId))
                        .orElseThrow(() -> new RuntimeException("Food type not found: " + foodId)))
                .collect(Collectors.toList());

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
        preferences.setMeat(newPreferences.getMeat());
        preferences.setSeafood(newPreferences.getSeafood());
        preferences.setCarbohydrate(newPreferences.getCarbohydrate());
        preferences.setVegetable(newPreferences.getVegetable());
        preferences.setRice(newPreferences.getRice());
        preferences.setBread(newPreferences.getBread());
        preferences.setNoodle(newPreferences.getNoodle());
        preferences.setSoup(newPreferences.getSoup());
        preferences.setNoSoup(newPreferences.getNoSoup());
        preferences.setHeavy(newPreferences.getHeavy());
        preferences.setLight(newPreferences.getLight());
//        preferences.setExceptionalFoods(newPreferences.getExceptionalFoods());
        preferences.setNotToday(newPreferences.getNotToday());
        return prefRepo.save(preferences);
    }





}
