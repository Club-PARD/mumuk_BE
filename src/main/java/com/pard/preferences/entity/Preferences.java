package com.pard.preferences.entity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pard.preferences.dto.PrefDto;
import jakarta.persistence.*;

import com.pard.user.entity.User;
import lombok.*;

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

    @Column(name = "fast_food")
    private int fastFood;

    @Column(name = "spicy_food")
    private int spicyFood;

    @Column(name = "herbs")
    private int herbs;

    @Column(name = "seafood")
    private int seafood;

    @Column(name = "vegetables")
    private int vegetables;

    @Column(name = "allergies")
    private String allergies;

    @OneToOne(mappedBy = "preferences")
    private User user;

    public static Preferences toEntity(PrefDto.Create dto, User user){
        return Preferences.builder()
                .koreanFood(dto.getKoreanFood())
                .japaneseFood(dto.getJapaneseFood())
                .chineseFood(dto.getChineseFood())
                .westernFood(dto.getWesternFood())
                .southeastAsianFood(dto.getSoutheastAsianFood())
                .fastFood(dto.getFastFood())
                .spicyFood(dto.getSpicyFood())
                .herbs(dto.getHerbs())
                .seafood(dto.getSeafood())
                .vegetables(dto.getVegetables())
                .allergies(dto.getAllergies())
                .user(user)
                .build();
    }
    public void setUser(User user) {
        this.user = user;
        if (user.getPreferences() != this) {
            user.setPreferences(this);
        }
    }


}
