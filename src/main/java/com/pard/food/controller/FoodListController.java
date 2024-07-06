package com.pard.food.controller;

import com.pard.food.dto.FoodListDto;
import com.pard.food.service.FoodListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodListController {
    private final FoodListService foodListService;

    @GetMapping("/list")
    public ResponseEntity<List<FoodListDto>> getAllFood() {
        List<FoodListDto> foodList = foodListService.getAllFoodNames();
        return ResponseEntity.ok(foodList);
    }
    @GetMapping("/{foodName}")
    public ResponseEntity<FoodListDto.Read> getFoodWithUrl(@PathVariable String foodName) {
        FoodListDto.Read food = foodListService.getFoodWithUrl(foodName);
        return ResponseEntity.ok(food);
    }
}
