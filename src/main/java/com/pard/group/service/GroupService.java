package com.pard.group.service;

import com.pard.group.dto.GroupDto;
import com.pard.group.entity.Group;
import com.pard.user.entity.User;
import com.pard.group.repo.GroupRepo;
import com.pard.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final UserRepo userRepo;
    private final GroupRepo groupRepo;

    public void createGroup(GroupDto.Create groupDto) {
        // Check if the user who creates the group exists
        User creatingUser = userRepo.findByUid(groupDto.getGroupId())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + groupDto.getGroupId()));

        // Create new group using the creator's UID as the group ID
        Group group = new Group();
        group.setId(groupDto.getGroupId());
        groupRepo.save(group);

        // Add the creating user and other users to the group
        List<User> users = groupDto.getUserUids().stream()
                .map(uid -> userRepo.findByUid(uid)
                        .orElseThrow(() -> new IllegalArgumentException("User not found: " + uid)))
                .collect(Collectors.toList());

        // Add the creating user to the users list
        users.add(creatingUser);

        // Set group for each user and mark them as grouped
        for (User user : users) {
            user.setGroup(group);
            user.setIsGrouped(true); // Assuming you have a field `isGrouped` in `User` entity
            userRepo.save(user);
        }

        // Set users for the group and save the group
        group.setUsers(users);
        groupRepo.save(group);
    }

    public void deleteGroup(String groupId) {
        Group group = groupRepo.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found: " + groupId));

        // Set group to null and isGrouped to false for all users in the group
        for (User user : group.getUsers()) {
            user.setGroup(null);
            user.setIsGrouped(false); // Reset the isGrouped status
            userRepo.save(user);
        }

        // Delete the group
        groupRepo.delete(group);

    }

    public GroupDto.Read getGroup(String groupId) {
        Group group = groupRepo.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found: " + groupId));

        return new GroupDto.Read(group);
    }
}
