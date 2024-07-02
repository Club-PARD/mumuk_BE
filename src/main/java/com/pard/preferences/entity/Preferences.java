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

    @Column(name = "seafood")
    private int seafood;

    @Column(name = "rice")
    private int rice;

    @Column(name = "noodles")
    private int noodles;

    @Column(name = "soup")
    private int soup;

    @Column(name = "grilled")
    private int grilled;

    @Column(name = "healthy_food")
    private int healthyFood;

    @Column(name = "fast_food")
    private int fastFood;

    @Column(name = "spicy_food")
    private int spicyFood;

    @ManyToMany
    @JoinTable(
            name = "user_exceptionalFood",
            joinColumns = @JoinColumn(name = "preferences_id"),
            inverseJoinColumns = @JoinColumn(name = "exceptionalFood_id")
    )
    private List<ExceptionalFood> exceptionalFoods = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_allergies",
            joinColumns = @JoinColumn(name = "preferences_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id")
    )
    private List<Allergy> allergies = new ArrayList<>();

    @OneToOne(mappedBy = "preferences")
    private User user;

    public static Preferences toEntity(PrefDto.Create dto) {
        return Preferences.builder()
                .koreanFood(dto.getKoreanFood())
                .japaneseFood(dto.getJapaneseFood())
                .chineseFood(dto.getChineseFood())
                .westernFood(dto.getWesternFood())
                .southeastAsianFood(dto.getSoutheastAsianFood())
                .meat(dto.getMeat())
                .seafood(dto.getSeafood())
                .rice(dto.getRice())
                .noodles(dto.getNoodles())
                .soup(dto.getSoup())
                .grilled(dto.getGrilled())
                .healthyFood(dto.getHealthyFood())
                .fastFood(dto.getFastFood())
                .spicyFood(dto.getSpicyFood())
                .build();
    }

    public void setUser(User user) {
        this.user = user;
        if (user.getPreferences() != this) {
            user.setPreferences(this);
        }
    }
}
