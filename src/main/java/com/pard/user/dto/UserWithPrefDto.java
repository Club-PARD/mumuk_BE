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

        private boolean spicyType;

        private int koreanFood;
        private int japaneseFood;
        private int chineseFood;
        private int westernFood;
        private int southeastAsianFood;
        private int elseFood;

        private String foodTypes;
        private List<String> exceptionalFoods;

        public Read(User user, Preferences preferences) {
            this.uid = user.getUid();
            this.name = user.getName();
            this.imageId = user.getImageId();

            this.isDaily = user.isDaily();

            this.spicyType = preferences.isSpicyType();

            this.koreanFood = preferences.getKoreanFood();
            this.japaneseFood = preferences.getJapaneseFood();
            this.chineseFood = preferences.getChineseFood();
            this.westernFood = preferences.getWesternFood();
            this.southeastAsianFood = preferences.getSoutheastAsianFood();
            this.elseFood = preferences.getElseFood();

            this.foodTypes = preferences.getFoodType().getName();

            this.exceptionalFoods = preferences.getExceptionalFoods().stream()
                    .map(exceptionalFood -> exceptionalFood.getName())
                    .toList();

        }
    }

    @Setter
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ReadFriend {
        private boolean isDaily;

        private String name;
        private int imageId;

        private boolean spicyType;

        private String foodTypes;
        private List<String> exceptionalFoods;

        public ReadFriend(User user, Preferences preferences) {
            this.isDaily = user.isDaily();

            this.name = user.getName();
            this.imageId = user.getImageId();

            this.spicyType = preferences.isSpicyType();


            this.foodTypes = preferences.getFoodType().getName();

            this.exceptionalFoods = preferences.getExceptionalFoods().stream()
                    .map(exceptionalFood -> exceptionalFood.getName())
                    .toList();

        }
    }
    @Setter
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ReadGroup {
        private boolean isDaily;

        private String name;
        private int imageId;

        private boolean spicyType;

        private int koreanFood;
        private int japaneseFood;
        private int chineseFood;
        private int westernFood;
        private int southeastAsianFood;
        private int elseFood;

        private String foodTypes;
        private List<String> exceptionalFoods;

        public ReadGroup(User user, Preferences preferences) {
            this.isDaily = user.isDaily();

            this.name = user.getName();
            this.imageId = user.getImageId();

            this.spicyType = preferences.isSpicyType();

            this.koreanFood = preferences.getKoreanFood();
            this.japaneseFood = preferences.getJapaneseFood();
            this.chineseFood = preferences.getChineseFood();
            this.westernFood = preferences.getWesternFood();
            this.southeastAsianFood = preferences.getSoutheastAsianFood();
            this.elseFood = preferences.getElseFood();

            this.foodTypes = preferences.getFoodType().getName();

            this.exceptionalFoods = preferences.getExceptionalFoods().stream()
                    .map(exceptionalFood -> exceptionalFood.getName())
                    .toList();

        }
    }
}
