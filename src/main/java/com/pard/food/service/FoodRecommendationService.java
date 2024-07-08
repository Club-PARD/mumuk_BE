package com.pard.food.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pard.food.dto.FoodRecommendationDto;
import com.pard.group.repo.GroupRepo;
import com.pard.preferences.entity.Preferences;
import com.pard.preferences.entity.TodayPreferences;
import com.pard.preferences.repo.PrefRepo;
import com.pard.user.entity.User;
import com.pard.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodRecommendationService {
    private final UserRepo userRepo;
    private final PrefRepo prefRepo;
    private final GroupRepo groupRepo;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate = new RestTemplate();
    private final FoodListService foodListService;

    public FoodRecommendationDto getFoodRecommendations(String groupId) {
        Map<String, Object> userPreferences = getGroupDaily(groupId);
        String recommendationsJson = executePythonScript(userPreferences);
        return parseRecommendations(recommendationsJson);
    }

    public Map<String, Object> getGroupDaily(String groupId) {
        List<User> users = userRepo.findByGroupId(groupId);

        return users.stream().collect(Collectors.toMap(
                User::getUid,
                user -> {
                    try {
                        Map<String, Object> userMap = new HashMap<>();
                        Preferences preferences = prefRepo.findById(user.getPrefId()).orElse(new Preferences());

                        userMap.put("spicyType", user.getPreferences().isSpicyType());
                        userMap.put("foodType", user.getPreferences().getFoodType().getName());
                        userMap.put("exceptionalFood", user.getPreferences().getExceptionalFoods().stream()
                                .map(exceptionalFood -> exceptionalFood.getName())
                                .collect(Collectors.toList()));
                        userMap.put("yesterdayFood", user.isDaily() ? user.getTodayPreferences().getNotToday() : "");

                        Map<String, Integer> preferenceFood = new HashMap<>();
                        if (user.isDaily()) {
                            TodayPreferences todayPreferences = user.getTodayPreferences();
                            preferenceFood.put("koreanFood", todayPreferences.getTodayKoreanFood());
                            preferenceFood.put("japaneseFood", todayPreferences.getTodayJapaneseFood());
                            preferenceFood.put("chineseFood", todayPreferences.getTodayChineseFood());
                            preferenceFood.put("westernFood", todayPreferences.getTodayWesternFood());
                            preferenceFood.put("southeastAsianFood", todayPreferences.getTodaySoutheastAsianFood());
                            preferenceFood.put("elseFood", todayPreferences.getTodayElseFood());
                            preferenceFood.put("meat", todayPreferences.getTodayMeat());
                            preferenceFood.put("seafood", todayPreferences.getTodaySeafood());
                            preferenceFood.put("carbohydrate", todayPreferences.getTodayCarbohydrate());
                            preferenceFood.put("vegetable", todayPreferences.getTodayVegetable());
                            preferenceFood.put("redFood", todayPreferences.getRedFood());
                            preferenceFood.put("nonRedFood", todayPreferences.getNotRedFood());
                            preferenceFood.put("rice", todayPreferences.getTodayRice());
                            preferenceFood.put("bread", todayPreferences.getTodayBread());
                            preferenceFood.put("noodle", todayPreferences.getTodayNoodle());
                            preferenceFood.put("heavy", todayPreferences.getTodayHeavy());
                            preferenceFood.put("light", todayPreferences.getTodayLight());
                            preferenceFood.put("soup", todayPreferences.getTodaySoup());
                            preferenceFood.put("noSoup", todayPreferences.getTodayNoSoup());
                        } else {
                            preferenceFood.put("koreanFood", preferences.getKoreanFood());
                            preferenceFood.put("japaneseFood", preferences.getJapaneseFood());
                            preferenceFood.put("chineseFood", preferences.getChineseFood());
                            preferenceFood.put("westernFood", preferences.getWesternFood());
                            preferenceFood.put("southeastAsianFood", preferences.getSoutheastAsianFood());
                            preferenceFood.put("elseFood", preferences.getElseFood());
                            // 기본값으로 0 설정
                            preferenceFood.put("meat" ,1);
                            preferenceFood.put("seafood" ,1);
                            preferenceFood.put("carbohydrate" ,1);
                            preferenceFood.put("vegetable" ,1);
                            preferenceFood.put("redFood" ,1);
                            preferenceFood.put("nonRedFood" ,1);
                            preferenceFood.put("rice" ,1);
                            preferenceFood.put("bread" ,1);
                            preferenceFood.put("noodle" ,1);
                            preferenceFood.put("heavy" ,1);
                            preferenceFood.put("light" ,1);
                            preferenceFood.put("soup" ,1);
                            preferenceFood.put("noSoup" ,1);
                        }

                        userMap.put("PreferenceFood", preferenceFood);
                        groupRepo.findById(groupId).ifPresent(group -> {
                            group.setIsResult(true);
                            groupRepo.save(group);
                            userMap.put("isResult", true);
                        });
                        return userMap;
                    } catch (Exception e) {
                        throw new RuntimeException("Error converting user data to Map", e);
                    }
                }
        ));
    }

    private String executePythonScript(Map<String, Object> userPreferences) {
        try {
            String url = "http://localhost:5000/recommend"; // Flask 서버 URL

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String jsonString = objectMapper.writeValueAsString(userPreferences);
            HttpEntity<String> request = new HttpEntity<>(jsonString, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);


            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute Python script", e);
        }
    }

    private FoodRecommendationDto parseRecommendations(String recommendationsJson) {
        try {
            List<Map<String, Object>> recommendations = objectMapper.readValue(recommendationsJson, List.class);

            if (recommendations.size() > 0) {
                addImageUrls(recommendations);
            }

            Map<String, Object> rank1 = recommendations.size() > 0 ? recommendations.get(0) : null;
            Map<String, Object> rank2 = recommendations.size() > 1 ? recommendations.get(1) : null;
            Map<String, Object> rank3 = recommendations.size() > 2 ? recommendations.get(2) : null;
            boolean isResult = recommendations.size() > 0;
            return new FoodRecommendationDto(rank1, rank2, rank3, isResult);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse recommendations", e);
        }
    }
    private void addImageUrls(List<Map<String, Object>> recommendations) {
        for (Map<String, Object> recommendation : recommendations) {
            String foodName = (String) recommendation.get("foodname");
            String imageUrl = foodListService.getFoodWithUrl(foodName);
            recommendation.put("image_url", imageUrl);
        }
    }
}
