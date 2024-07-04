package com.pard.preferences.dto;

import com.pard.preferences.entity.Preferences;
import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class PrefDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Create {
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
        private List<String> exceptionalFood;
        private List<String> allergies;
    }

    @Getter
    @Setter
    public static class Read {
//        private String uid;
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
        private List<String> exceptionalFood;
        private List<String> allergies;


        public Read(Preferences preferences) {
//            this.uid = preferences.getUser().getUid();
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

            this.exceptionalFood = preferences.getExceptionalFoods().stream()
                    .map(exceptionalFood -> exceptionalFood.getName())
                    .toList();
            this.allergies = preferences.getAllergies().stream()
                    .map(allergy -> allergy.getName())
                    .toList();
        }
    }
}
