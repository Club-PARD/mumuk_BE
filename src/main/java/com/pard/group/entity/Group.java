package com.pard.group.entity;

import com.pard.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_group")
public class Group {
    @Id
    private String id;  // Use String for creatorId

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<User> users;

}
