package com.pard.user.service;

import com.pard.preferences.entity.Preferences;
import com.pard.preferences.repo.PrefRepo;
import com.pard.user.dto.UserDto;
import com.pard.user.dto.UserWithPrefDto;
import com.pard.user.entity.User;
import com.pard.user.exception.UserFoundException;
import com.pard.user.exception.UserNotFoundException;
import com.pard.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PrefRepo prefRepo;

    public void createUser(UserDto.Create dto) {
        Optional<User> existingUser = userRepo.findByUid(dto.getUid());
        if (existingUser.isPresent()) {
            throw new UserFoundException("User uid already exists");
        }

        userRepo.save(User.toEntity(dto));
    }


    public UserDto.Read findByUid(String uid) {
        User user = userRepo.findByUid(uid)
                .orElseThrow(() -> new UserNotFoundException("User not found with uid: " + uid));
        return new UserDto.Read(user);
    }

    public List<UserDto.Read> readAll(){

        return userRepo.findAll()
                .stream()
                .map(user -> new UserDto.Read(user))
                .collect(Collectors.toList());
    }

    public UserWithPrefDto getUserWithPref(String uid) {
        User user = userRepo.findByUid(uid).orElseThrow(() -> new RuntimeException("User not found"));
        UserWithPrefDto userWithPrefDTO = new UserWithPrefDto();
        userWithPrefDTO.setUser(user);  // Corrected this line

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
