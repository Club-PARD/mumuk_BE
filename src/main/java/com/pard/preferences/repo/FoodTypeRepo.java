package com.pard.preferences.repo;

import com.pard.preferences.entity.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTypeRepo extends JpaRepository<FoodType, Integer> {
}
