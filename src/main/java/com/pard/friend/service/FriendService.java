package com.pard.friend.service;

import com.pard.friend.dto.FriendDto;
import com.pard.user.entity.User;
import com.pard.user.exception.UserNotFoundException;
import com.pard.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final UserRepo userRepo;

    public boolean addFriend(String userName, String friendName) {
        User user = userRepo.findByName(userName)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + userName));
        User friend = userRepo.findByName(friendName)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + friendName));
        if (user.getFriendNameList().contains(friendName)) {
            return false;
        }
        if (!user.getFriendNameList().contains(friendName)) {
            user.getFriendNameList().add(friendName);
            userRepo.save(user);
        }

        if (!friend.getFriendNameList().contains(userName)) {
            friend.getFriendNameList().add(userName);
            userRepo.save(friend);
        }
        return true;
    }

    public void deleteFriend(String userName, String friendName) {
        User user = userRepo.findByName(userName)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + userName));
        User friend = userRepo.findByName(friendName)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + friendName));

        if (user.getFriendNameList().contains(friendName)) {
            user.getFriendNameList().remove(friendName);
            userRepo.save(user);
        }

        if (friend.getFriendNameList().contains(userName)) {
            friend.getFriendNameList().remove(userName);
            userRepo.save(friend);
        }
    }

    public List<FriendDto.Read> getFriendList(String name) {
        User user = userRepo.findByName(name)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + name));
        return user.getFriendNameList().stream()
                .map(friendName -> userRepo.findByName(friendName)
                        .map(FriendDto.Read::new)
                        .orElseThrow(() -> new UserNotFoundException("Friend not found: " + friendName)))
                .collect(Collectors.toList());
    }

}
