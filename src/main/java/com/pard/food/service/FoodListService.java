package com.pard.food.service;

import com.pard.food.dto.FoodListDto;
import com.pard.food.entity.FoodList;
import com.pard.food.repo.FoodListRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodListService {
    private final FoodListRepo foodListRepo;

    @Value("${aws.s3}")
    private String s3BaseUrl;

    public List<FoodListDto> getAllFoodNames() {
        return foodListRepo.findAllFoodNames();
    }

    public FoodListDto.Read getFoodWithUrl(String foodName) {
        FoodList food = foodListRepo.findByFoodName(foodName)
                .orElseThrow(() -> new RuntimeException("Food not found: " + foodName));

        String s3Link = s3BaseUrl + food.getS3Link();
        return new FoodListDto.Read(food.getFoodName(), s3Link);
    }
}
