package com.pard.user.entity;

import com.pard.preferences.entity.Preferences;
import com.pard.user.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String uid;
    private String name;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preferences_id", referencedColumnName = "id")
    private Preferences preferences;


    public static User toEntity(UserDto.Create dto){
        return User.builder()
                .uid(dto.getUid())
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }
    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
        if (preferences.getUser() != this) {
            preferences.setUser(this);
        }
    }

}
