package com.pard.userGroup.dto;

import com.pard.preferences.entity.Preferences;
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
        private int meat;
        private int seafood;
        private int rice;
        private int noodles;
        private int soup;
        private int grilled;
        private int healthyFood;
        private int fastFood;
        private int spicyFood;
        private List<String> exceptionalFood;
        private List<String> allergies;
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
        private int meat;
        private int seafood;
        private int rice;
        private int noodles;
        private int soup;
        private int grilled;
        private int healthyFood;
        private int fastFood;
        private int spicyFood;
        private List<String> exceptionalFood;
        private List<String> allergies;

        public Read(Preferences preferences) {
            this.uid = preferences.getUser().getUid();
            this.koreanFood = preferences.getKoreanFood();
            this.japaneseFood = preferences.getJapaneseFood();
            this.chineseFood = preferences.getChineseFood();
            this.westernFood = preferences.getWesternFood();
            this.southeastAsianFood = preferences.getSoutheastAsianFood();
            this.meat = preferences.getMeat();
            this.seafood = preferences.getSeafood();
            this.rice = preferences.getRice();
            this.noodles = preferences.getNoodles();
            this.soup = preferences.getSoup();
            this.grilled = preferences.getGrilled();
            this.healthyFood = preferences.getHealthyFood();
            this.fastFood = preferences.getFastFood();
            this.spicyFood = preferences.getSpicyFood();
            this.exceptionalFood = preferences.getExceptionalFood();
            this.allergies = preferences.getAllergies();
        }
    }
}
