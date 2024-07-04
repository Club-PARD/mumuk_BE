package com.pard.preferences.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

}
