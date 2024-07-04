package com.pard.preferences.repo;

import com.pard.preferences.entity.NotToday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotTodayRepo extends JpaRepository<NotToday, Integer> {
}
