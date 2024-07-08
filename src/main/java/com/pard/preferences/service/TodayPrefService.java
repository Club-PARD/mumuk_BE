package com.pard.preferences.service;

import com.pard.preferences.dto.TodayPrefDto;
import com.pard.preferences.entity.Preferences;
import com.pard.preferences.entity.TodayPreferences;
import com.pard.preferences.repo.PrefRepo;
import com.pard.preferences.repo.TodayPrefRepo;
import com.pard.user.entity.User;
import com.pard.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodayPrefService {
    private final TodayPrefRepo todayPrefRepo;
    private final PrefRepo prefRepo;
    private final UserRepo userRepo;

    @Transactional
    public void saveOrUpdateTodayPref(String uid, TodayPrefDto.Create createDto) {
        User user = userRepo.findByUid(uid).orElseThrow(() -> new RuntimeException("User not found"));

        TodayPreferences todayPreferences = user.getTodayPreferences();
        if (todayPreferences == null) {
            todayPreferences = new TodayPreferences();
        }
        user.setDaily(true);

        todayPreferences.setTodayKoreanFood(createDto.getTodayKoreanFood());
        todayPreferences.setTodayJapaneseFood(createDto.getTodayJapaneseFood());
        todayPreferences.setTodayChineseFood(createDto.getTodayChineseFood());
        todayPreferences.setTodayWesternFood(createDto.getTodayWesternFood());
        todayPreferences.setTodaySoutheastAsianFood(createDto.getTodaySoutheastAsianFood());
        todayPreferences.setTodayElseFood(createDto.getTodayElseFood());
        todayPreferences.setTodayMeat(createDto.getTodayMeat());
        todayPreferences.setTodaySeafood(createDto.getTodaySeafood());
        todayPreferences.setTodayCarbohydrate(createDto.getTodayCarbohydrate());
        todayPreferences.setTodayVegetable(createDto.getTodayVegetable());
        todayPreferences.setRedFood(createDto.getRedFood());
        todayPreferences.setNotRedFood(createDto.getNotRedFood());
        todayPreferences.setTodayRice(createDto.getTodayRice());
        todayPreferences.setTodayBread(createDto.getTodayBread());
        todayPreferences.setTodayNoodle(createDto.getTodayNoodle());
        todayPreferences.setTodayHeavy(createDto.getTodayHeavy());
        todayPreferences.setTodayLight(createDto.getTodayLight());
        todayPreferences.setTodaySoup(createDto.getTodaySoup());
        todayPreferences.setTodayNoSoup(createDto.getTodayNoSoup());
        todayPreferences.setNotToday(createDto.getNotToday());

        todayPrefRepo.save(todayPreferences);

        user.setTodayPreferences(todayPreferences);
        userRepo.save(user);
    }

    @Transactional
    public TodayPrefDto.Read getTodayPreferences(String userName) {
        User user = userRepo.findByName(userName).orElseThrow(() -> new RuntimeException("User not found"));
        TodayPreferences todayPreferences = user.getTodayPreferences();
        Preferences preferences = prefRepo.findById(user.getPrefId()).orElse(null);

        return new TodayPrefDto.Read(todayPreferences, preferences, user);
    }
}
