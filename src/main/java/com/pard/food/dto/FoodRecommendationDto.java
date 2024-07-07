package com.pard.food.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodRecommendationDto {
    private Map<String, Object> rank1;
    private Map<String, Object> rank2;
    private Map<String, Object> rank3;
}