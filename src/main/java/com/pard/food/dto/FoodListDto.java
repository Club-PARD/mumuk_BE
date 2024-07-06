package com.pard.food.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FoodListDto {
    private String foodName;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Read {
        private String foodName;
        private String presignedUrl;
    }
}
