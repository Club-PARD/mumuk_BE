package com.pard.preferences.repo;

import com.pard.preferences.entity.TodayPreferences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodayPrefRepo extends JpaRepository<TodayPreferences, Long> {
}