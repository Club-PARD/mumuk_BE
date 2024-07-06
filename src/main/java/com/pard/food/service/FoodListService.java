package com.pard.food.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.pard.food.dto.FoodListDto;
import com.pard.food.entity.FoodList;
import com.pard.food.repo.FoodListRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodListService {

    private final FoodListRepo foodListRepo;
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.s3}")
    private String s3BaseUrl;

    public List<FoodListDto> getAllFoodNames() {
        return foodListRepo.findAllFoodNames();
    }

    public FoodListDto.Read getFoodWithUrl(String foodName) {
        Optional<FoodList> optionalFood = foodListRepo.findByFoodName(foodName);

        if (optionalFood.isPresent()) {
            FoodList food = optionalFood.get();
            String presignedUrl = generatePresignedUrl(food.getS3Link());
            return new FoodListDto.Read(food.getFoodName(), presignedUrl);
        } else {
            throw new RuntimeException("Food not found");
        }
    }

    private String generatePresignedUrl(String objectKey) {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60; // 1 hour
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, objectKey)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);

        URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }
}
