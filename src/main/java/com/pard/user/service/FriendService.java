package com.pard.user.service;

import com.pard.user.entity.User;
import com.pard.user.exception.UserNotFoundException;
import com.pard.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final UserRepo userRepo;

    public void addFriend(String userUid, String friendUid) {
        User user = userRepo.findByUid(userUid)
                .orElseThrow(() -> new UserNotFoundException("User not found with uid: " + userUid));
        User friend = userRepo.findByUid(friendUid)
                .orElseThrow(() -> new UserNotFoundException("User not found with uid: " + friendUid));

        if (!user.getFriendUidList().contains(friendUid)) {
            user.getFriendUidList().add(friendUid);
            userRepo.save(user);
        }

        if (!friend.getFriendUidList().contains(userUid)) {
            friend.getFriendUidList().add(userUid);
            userRepo.save(friend);
        }
    }

    public void deleteFriend(String userUid, String friendUid) {
        User user = userRepo.findByUid(userUid)
                .orElseThrow(() -> new UserNotFoundException("User not found with uid: " + userUid));
        User friend = userRepo.findByUid(friendUid)
                .orElseThrow(() -> new UserNotFoundException("User not found with uid: " + friendUid));

        if (user.getFriendUidList().contains(friendUid)) {
            user.getFriendUidList().remove(friendUid);
            userRepo.save(user);
        }

        if (friend.getFriendUidList().contains(userUid)) {
            friend.getFriendUidList().remove(userUid);
            userRepo.save(friend);
        }
    }

    public List<String> getFriendList(String uid) {
        User user = userRepo.findByUid(uid)
                .orElseThrow(() -> new UserNotFoundException("User not found with uid: " + uid));
        return user.getFriendUidList();
    }
}
