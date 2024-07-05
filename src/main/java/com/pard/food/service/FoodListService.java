package com.pard.food.service;

import com.pard.food.entity.FoodList;
import com.pard.food.repo.FoodListRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodListService {
    private final FoodListRepo foodListRepo;

    public List<FoodList> getAllFood() {
        return foodListRepo.findAll();
    }
}
