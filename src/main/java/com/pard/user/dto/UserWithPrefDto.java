package com.pard.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pard.preferences.entity.Preferences;
import com.pard.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class UserWithPrefDto {
    @Setter
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Read {
        private String uid;
        private String name;
        private int imageId;
        private boolean isDaily;
        private boolean isReady;
        private boolean spicyType;
        private int koreanFood;
        private int japaneseFood;
        private int chineseFood;
        private int westernFood;
        private int southeastAsianFood;
        private int elseFood;
        private int meat;
        private int seafood;
        private int carbohydrate;
        private int vegetable;
        private int rice;
        private int bread;
        private int noodle;
        private int soup;
        private int noSoup;
        private int heavy;
        private int light;
        private List<String> notToday;
        private List<String> foodTypes;
        private List<String> exceptionalFoods;

        public Read(User user, Preferences preferences) {
            this.uid = user.getUid();
            this.name = user.getName();
            this.imageId = user.getImageId();
            this.isDaily = user.isDaily();
            this.isReady = user.isReady();
            this.spicyType = preferences.isSpicyType();
            this.koreanFood = preferences.getKoreanFood();
            this.japaneseFood = preferences.getJapaneseFood();
            this.chineseFood = preferences.getChineseFood();
            this.westernFood = preferences.getWesternFood();
            this.southeastAsianFood = preferences.getSoutheastAsianFood();
            this.elseFood = preferences.getElseFood();
            this.meat = preferences.getMeat();
            this.seafood = preferences.getSeafood();
            this.carbohydrate = preferences.getCarbohydrate();
            this.vegetable = preferences.getVegetable();
            this.rice = preferences.getRice();
            this.bread = preferences.getBread();
            this.noodle = preferences.getNoodle();
            this.soup = preferences.getSoup();
            this.noSoup = preferences.getNoSoup();
            this.heavy = preferences.getHeavy();
            this.light = preferences.getLight();

            this.exceptionalFoods = preferences.getExceptionalFoods().stream()
                    .map(exceptionalFood -> exceptionalFood.getName())
                    .toList();

            this.notToday = preferences.getNotToday().stream()
                    .map(notToday -> notToday.getName())
                    .toList();

            this.foodTypes = preferences.getFoodTypes().stream()
                    .map(foodType -> foodType.getName())
                    .toList();
        }

    }
}
