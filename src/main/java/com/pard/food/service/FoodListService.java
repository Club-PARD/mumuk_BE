package com.pard.food.service;

import com.pard.food.dto.FoodListDto;
import com.pard.food.entity.FoodList;
import com.pard.food.repo.FoodListRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodListService {
    private final FoodListRepo foodListRepo;

    public List<FoodListDto> getAllFoodNames() {
        return foodListRepo.findAllFoodNames();
    }

    public FoodListDto.Read getFoodWithUrl(String foodName) {
        FoodList food = foodListRepo.findByFoodName(foodName)
                .orElseThrow(() -> new RuntimeException("Food not found: " + foodName));
        // S3 링크 생성
        String s3Link = "https://mumukbucket.s3.ap-northeast-2.amazonaws.com/foods/" + food.getS3Link();
        return new FoodListDto.Read(food.getFoodName(), s3Link);
    }
}
