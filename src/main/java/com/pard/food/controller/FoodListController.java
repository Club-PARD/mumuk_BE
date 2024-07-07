package com.pard.food.controller;

import com.pard.food.dto.FoodListDto;
import com.pard.food.dto.FoodRecommendationDto;
import com.pard.food.service.FoodListService;
import com.pard.food.service.FoodRecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodListController {
    private final FoodListService foodListService;
    private final FoodRecommendationService foodRecommendationService;

    @GetMapping("/list")
    public ResponseEntity<List<FoodListDto>> getAllFood() {
        List<FoodListDto> foodList = foodListService.getAllFoodNames();
        return ResponseEntity.ok(foodList);
    }

    @PostMapping("/recommend/{groupId}")
    public ResponseEntity<FoodRecommendationDto> getFoodRecommendations(@PathVariable String groupId) {
        FoodRecommendationDto recommendations = foodRecommendationService.getFoodRecommendations(groupId);
        return ResponseEntity.ok(recommendations);
    }
}
