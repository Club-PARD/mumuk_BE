package com.pard.preferences.dto;

import com.pard.preferences.entity.FoodType;
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

        private boolean spicyType;

        private int koreanFood;
        private int japaneseFood;
        private int chineseFood;
        private int westernFood;
        private int southeastAsianFood;
        private int elseFood;

        private int foodTypeId;  // Use ID to reference FoodType
        private List<String> exceptionalFoods;
    }

    @Getter
    @Setter
    public static class Read {
//        private String uid;
        private boolean spicyType;

        private int koreanFood;
        private int japaneseFood;
        private int chineseFood;
        private int westernFood;
        private int southeastAsianFood;
        private int elseFood;

        private List<String> exceptionalFoods;
        private String foodTypeName;


        public Read(Preferences preferences) {
//            this.uid = preferences.getUser().getUid();
            this.spicyType = preferences.isSpicyType();
            this.koreanFood = preferences.getKoreanFood();
            this.japaneseFood = preferences.getJapaneseFood();
            this.chineseFood = preferences.getChineseFood();
            this.westernFood = preferences.getWesternFood();
            this.southeastAsianFood = preferences.getSoutheastAsianFood();
            this.elseFood = preferences.getElseFood();


            this.exceptionalFoods = preferences.getExceptionalFoods().stream()
                    .map(exceptionalFood -> exceptionalFood.getName())
                    .toList();

            this.foodTypeName = preferences.getFoodTypeName();
        }
    }
}
