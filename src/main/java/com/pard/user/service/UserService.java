package com.pard.user.service;

import com.pard.user.dto.UserDto;
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
    public boolean updateUser(String uid, Integer imageId, String name) throws UserNotFoundException {
        User user = userRepo.findByUid(uid).orElseThrow(() -> new UserNotFoundException("User not found with uid: " + uid));

        boolean updated = false;
        if (imageId != null && imageId >= 1 && imageId <= 20) {
            user.setImageId(imageId);
            updated = true;
        }

        if (name != null && !name.isEmpty()) {
            user.setName(name);
            updated = true;
        }

        if (updated) {
            userRepo.save(user);
        }

        return updated;
    }


}



