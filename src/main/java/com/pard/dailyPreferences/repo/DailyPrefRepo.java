package com.pard.dailyPreferences.repo;

import com.pard.preferences.entity.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyPrefRepo extends JpaRepository<Preferences, Long> {
}