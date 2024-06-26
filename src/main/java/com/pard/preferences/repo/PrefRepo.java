package com.pard.preferences.repo;

import com.pard.preferences.entity.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrefRepo extends JpaRepository<Preferences, Long> {
}