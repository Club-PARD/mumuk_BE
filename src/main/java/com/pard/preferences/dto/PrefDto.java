package com.pard.preferences.dto;

import com.pard.preferences.entity.Preferences;
import lombok.*;

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
        private int fastFood;
        private int spicyFood;
        private int herbs;
        private int seafood;
        private int vegetables;
        private String allergies;
    }

    @Getter
    @Setter
    public static class Read {
        private String uid;
        private int koreanFood;
        private int japaneseFood;
        private int chineseFood;
        private int westernFood;
        private int southeastAsianFood;
        private int fastFood;
        private int spicyFood;
        private int herbs;
        private int seafood;
        private int vegetables;
        private String allergies;

        public Read(Preferences preferences) {
            this.uid = preferences.getUser().getUid();
            this.koreanFood = preferences.getKoreanFood();
            this.japaneseFood = preferences.getJapaneseFood();
            this.chineseFood = preferences.getChineseFood();
            this.westernFood = preferences.getWesternFood();
            this.southeastAsianFood = preferences.getSoutheastAsianFood();
            this.fastFood = preferences.getFastFood();
            this.spicyFood = preferences.getSpicyFood();
            this.herbs = preferences.getHerbs();
            this.seafood = preferences.getSeafood();
            this.vegetables = preferences.getVegetables();
            this.allergies = preferences.getAllergies();
        }
    }
}
