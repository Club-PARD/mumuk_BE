package com.pard.food.repo;

import com.pard.food.entity.FoodList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodListRepo extends JpaRepository<FoodList, Long> {
}
