package com.pard.user.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pard.group.entity.Group;
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

    @Column(unique = true, nullable = false)
    private String name;


    @Column(nullable = false)
    private int imageId;

    @Column(nullable = false)
    private boolean isDaily = false;

    @Column(nullable = false)
    private boolean isGrouped = false;

    @Column(nullable = false)
    private boolean isReady = false;
/*
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private UserTag tag;

 */


    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;




    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preferences_id", referencedColumnName = "id")
    private Preferences preferences;


    @ElementCollection
    @CollectionTable(name = "friend_name_list", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "friend_name")
    private List<String> friendNameList = new ArrayList<>();


    public static User toEntity(UserDto.Create dto){
        return User.builder()
                .uid(dto.getUid())
                .name(dto.getName())
                .imageId(dto.getImageId())
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

    public void setIsGrouped(boolean b) {
        isGrouped = b;
    }
    public void setIsReady(boolean b) {
        isReady = b;
        isDaily = b;
    }

}
