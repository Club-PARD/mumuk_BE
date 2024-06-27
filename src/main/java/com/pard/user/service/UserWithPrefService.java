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

    public UserWithPrefDto getUserWithPref(String uid) {
        User user = userRepo.findByUid(uid).orElseThrow(() -> new RuntimeException("User not found"));
        UserWithPrefDto userWithPrefDTO = new UserWithPrefDto();
        userWithPrefDTO.setUser(user);

        if (user.getPrefId() != null) {
            Preferences preference = prefRepo.findById(user.getPrefId()).orElse(null);
            userWithPrefDTO.setPreference(preference);
        }

        return userWithPrefDTO;
    }
    public List<UserWithPrefDto> getUsersWithPref(List<String> uids) {
        return uids.stream()
                .map(this::getUserWithPref)
                .collect(Collectors.toList());
    }
}
