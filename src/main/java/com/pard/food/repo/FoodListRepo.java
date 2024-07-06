package com.pard.food.repo;

import com.pard.food.dto.FoodListDto;
import com.pard.food.entity.FoodList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FoodListRepo extends JpaRepository<FoodList, Long> {
    @Query("SELECT new com.pard.food.dto.FoodListDto(f.foodName) FROM FoodList f")
    List<FoodListDto> findAllFoodNames();

    @Query("SELECT f FROM FoodList f WHERE f.foodName = :foodName")
    Optional<FoodList> findByFoodName(String foodName);
}
