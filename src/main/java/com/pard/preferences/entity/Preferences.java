package com.pard.preferences.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pard.preferences.dto.PrefDto;
import jakarta.persistence.*;

import com.pard.user.entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Preferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "spicy_type")
    private boolean spicyType;

    @Column(name = "korean_food")
    private int koreanFood;

    @Column(name = "japanese_food")
    private int japaneseFood;

    @Column(name = "chinese_food")
    private int chineseFood;

    @Column(name = "western_food")
    private int westernFood;

    @Column(name = "southeast_asian_food")
    private int southeastAsianFood;

    @Column(name = "else_food")
    private int elseFood;

    @Column(name = "meat")
    private int meat;

    @Column(name = "sea_food")
    private int seafood;

    private int carbohydrate;

    private int vegetable;

    @Column(name = "rice")
    private int rice;

    @Column(name = "bread")
    private int bread;

    @Column(name = "noodle")
    private int noodle;

    private int heavy;

    private int light;

    @Column(name = "soup")
    private int soup;

    @Column(name = "no_soup")
    private int noSoup;

    @ManyToOne
    @JoinColumn(name = "food_type_id")
    private FoodType foodType;


    @ManyToMany
    @JoinTable(
            name = "user_exceptionalFood",
            joinColumns = @JoinColumn(name = "preferences_id"),
            inverseJoinColumns = @JoinColumn(name = "exceptionalFood_id")
    )
    private List<ExceptionalFood> exceptionalFoods = new ArrayList<>();



    @OneToOne(mappedBy = "preferences")
    private User user;

    public static Preferences toEntity(PrefDto.Create dto, FoodType foodType) {
        return Preferences.builder()
                .spicyType(dto.isSpicyType())
                .koreanFood(dto.getKoreanFood())
                .japaneseFood(dto.getJapaneseFood())
                .chineseFood(dto.getChineseFood())
                .westernFood(dto.getWesternFood())
                .southeastAsianFood(dto.getSoutheastAsianFood())
                .meat(dto.getMeat())
                .seafood(dto.getSeafood())
                .rice(dto.getRice())
                .bread(dto.getBread())
                .noodle(dto.getNoodle())
                .heavy(dto.getHeavy())
                .light(dto.getLight())
                .soup(dto.getSoup())
                .noSoup(dto.getNoSoup())
                .carbohydrate(dto.getCarbohydrate())
                .vegetable(dto.getVegetable())
                .foodType(foodType)
                .exceptionalFoods(new ArrayList<>())
                .build();
    }

    public void setUser(User user) {
        this.user = user;
        if (user.getPreferences() != this) {
            user.setPreferences(this);
        }
    }
    public String getFoodTypeName() {
        return foodType != null ? foodType.getName() : null;
    }

}
