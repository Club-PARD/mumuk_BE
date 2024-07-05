package com.pard.preferences.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pard.preferences.dto.TodayPrefDto;
import com.pard.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TodayPreferences {
    //today's menu
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int todayKoreanFood;
    private int todayJapaneseFood;
    private int todayChineseFood;
    private int todayWesternFood;
    private int todaySoutheastAsianFood;
    private int todayElseFood;
    private int todayMeat;
    private int todaySeafood;
    private int todayCarbohydrate;
    private int todayVegetable;
    private int redFood;
    private int notRedFood;
    private int todayRice;
    private int todayBread;
    private int todayNoodle;
    private int todayHeavy;
    private int todayLight;
    private int todaySoup;
    private int todayNoSoup;
    private String notToday;

    private boolean isDaily;

    @OneToOne(mappedBy = "todayPreferences")
    private User user;

    public static TodayPreferences toEntity(TodayPrefDto.Create dto) {
        return TodayPreferences.builder()
                .todayKoreanFood(dto.getTodayKoreanFood())
                .todayJapaneseFood(dto.getTodayJapaneseFood())
                .todayChineseFood(dto.getTodayChineseFood())
                .todayWesternFood(dto.getTodayWesternFood())
                .todaySoutheastAsianFood(dto.getTodaySoutheastAsianFood())
                .todayElseFood(dto.getTodayElseFood())
                .todayMeat(dto.getTodayMeat())
                .todaySeafood(dto.getTodaySeafood())
                .todayCarbohydrate(dto.getTodayCarbohydrate())
                .todayVegetable(dto.getTodayVegetable())
                .redFood(dto.getRedFood())
                .notRedFood(dto.getNotRedFood())
                .todayRice(dto.getTodayRice())
                .todayBread(dto.getTodayBread())
                .todayNoodle(dto.getTodayNoodle())
                .todayHeavy(dto.getTodayHeavy())
                .todayLight(dto.getTodayLight())
                .todaySoup(dto.getTodaySoup())
                .todayNoSoup(dto.getTodayNoSoup())
                .build();
    }


}
