package com.pard.user.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pard.preferences.entity.Preferences;
import com.pard.user.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "uid")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String uid;
    private String name;
    private String email;

    private Integer image = 0;

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


    public Long getPrefId() {
        return preferences == null ? null : preferences.getId();
    }
}
