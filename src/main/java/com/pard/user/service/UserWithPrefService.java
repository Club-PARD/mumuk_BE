package com.pard.user.service;

import com.pard.preferences.entity.Preferences;
import com.pard.preferences.repo.PrefRepo;
import com.pard.user.dto.UserWithPrefDto;
import com.pard.user.entity.User;
import com.pard.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserWithPrefService {
    private final PrefRepo prefRepo;
    private final UserRepo userRepo;


    public UserWithPrefDto.Read getUserWithPref(String name) {
        User user = userRepo.findByName(name).orElseThrow(() -> new RuntimeException("User not found"));
        Preferences preference = prefRepo.findById(user.getPrefId()).orElse(null);
        return new UserWithPrefDto.Read(user, preference);
    }

    public List<UserWithPrefDto.Read> getUsersWithPref(String groupId) {
        List<User> users = userRepo.findByGroupId(groupId);  // Assuming there's a method to get users by group ID
        return users.stream()
                .map(user -> {
                    Preferences preference = prefRepo.findById(user.getPrefId()).orElse(null);
                    return new UserWithPrefDto.Read(user, preference);
                })
                .collect(Collectors.toList());
    }
}
