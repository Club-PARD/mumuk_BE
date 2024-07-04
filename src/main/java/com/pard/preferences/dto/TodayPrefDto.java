package com.pard.preferences.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pard.preferences.entity.Preferences;
import com.pard.preferences.entity.TodayPreferences;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class TodayPrefDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Create {
        private int todayKoreanFood;
        private int todayJapaneseFood;
        private int todayChineseFood;
        private int todayWesternFood;
        private int todaySoutheastAsianFood;
        private int todayElseFood;
        private int todayMeat;
        private int todaySeafood;
        private int todayCarbohydrate;
        private int todayVegetable;
        private int redFood;
        private int notRedFood;
        private int todayRice;
        private int todayBread;
        private int todayNoodle;
        private int todayHeavy;
        private int todayLight;
        private int todaySoup;
        private int todayNoSoup;
        private String notToday;
    }

    @Getter
    @Setter
    public static class Read {
        private boolean spicyType;
        private int todayKoreanFood;
        private int todayJapaneseFood;
        private int todayChineseFood;
        private int todayWesternFood;
        private int todaySoutheastAsianFood;
        private int todayElseFood;
        private int todayMeat;
        private int todaySeafood;
        private int todayCarbohydrate;
        private int todayVegetable;
        private int redFood;
        private int notRedFood;
        private int todayRice;
        private int todayBread;
        private int todayNoodle;
        private int todayHeavy;
        private int todayLight;
        private int todaySoup;
        private int todayNoSoup;
        private String notToday;
        private String foodType;
        private List<String> exceptionalFoods;

    public Read(TodayPreferences todayPreferences, Preferences preferences) {
        this.spicyType = preferences.isSpicyType();
        this.todayKoreanFood = todayPreferences.getTodayKoreanFood();
        this.todayJapaneseFood = todayPreferences.getTodayJapaneseFood();
        this.todayChineseFood = todayPreferences.getTodayChineseFood();
        this.todayWesternFood = todayPreferences.getTodayWesternFood();
        this.todaySoutheastAsianFood = todayPreferences.getTodaySoutheastAsianFood();
        this.todayElseFood = todayPreferences.getTodayElseFood();
        this.todayMeat = todayPreferences.getTodayMeat();
        this.todaySeafood = todayPreferences.getTodaySeafood();
        this.todayCarbohydrate = todayPreferences.getTodayCarbohydrate();
        this.todayVegetable = todayPreferences.getTodayVegetable();
        this.redFood = todayPreferences.getRedFood();
        this.notRedFood = todayPreferences.getNotRedFood();
        this.todayRice = todayPreferences.getTodayRice();
        this.todayBread = todayPreferences.getTodayBread();
        this.todayNoodle = todayPreferences.getTodayNoodle();
        this.todayHeavy = todayPreferences.getTodayHeavy();
        this.todayLight = todayPreferences.getTodayLight();
        this.todaySoup = todayPreferences.getTodaySoup();
        this.todayNoSoup = todayPreferences.getTodayNoSoup();
        this.notToday = todayPreferences.getNotToday();

        this.exceptionalFoods = preferences.getExceptionalFoods().stream()
                .map(exceptionalFood -> exceptionalFood.getName())
                .toList();

        this.foodType = preferences.getFoodType().getName();

        }

    }

}
