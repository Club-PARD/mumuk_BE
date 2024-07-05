package com.pard.group.entity;

import com.pard.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
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

    @OneToMany(mappedBy = "group", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> users;

    @Column(nullable = false)
    private ZonedDateTime createdAt;  // Ensure this field is present
}
