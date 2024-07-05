package com.pard.food.controller;

import com.pard.food.entity.FoodList;
import com.pard.food.service.FoodListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodListController {
    private final FoodListService foodListService;

    @GetMapping("/list")
    public ResponseEntity<List<FoodList>> getAllFood() {
        List<FoodList> foodList = foodListService.getAllFood();
        return ResponseEntity.ok(foodList);
    }
}
