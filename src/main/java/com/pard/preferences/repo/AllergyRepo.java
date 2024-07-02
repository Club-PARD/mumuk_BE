package com.pard.preferences.repo;

import com.pard.preferences.entity.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergyRepo extends JpaRepository<Allergy, Integer> {
}
