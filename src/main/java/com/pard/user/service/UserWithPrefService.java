package com.pard.user.service;

import com.pard.preferences.dto.TodayPrefDto;
import com.pard.preferences.entity.Preferences;
import com.pard.preferences.entity.TodayPreferences;
import com.pard.preferences.repo.PrefRepo;
import com.pard.user.dto.UserWithPrefDto;
import com.pard.user.entity.User;
import com.pard.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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


    public Map<String, UserWithPrefDto.Read> getUsersWithPref(String groupId) {
        List<User> users = userRepo.findByGroupId(groupId);  // Assuming there's a method to get users by group ID
        return IntStream.range(0, users.size())
                .boxed()
                .collect(Collectors.toMap(
                        i -> "user" + (i + 1),
                        i -> {
                            User user = users.get(i);
                            Preferences preference = prefRepo.findById(user.getPrefId()).orElse(null);
                            return new UserWithPrefDto.Read(user, preference);
                        }
                ));
    }

    public Map<String, UserWithPrefDto.ReadFriend> getFriendsPref(String name) {
        User user = userRepo.findByName(name).orElseThrow(() -> new RuntimeException("User not found"));
        List<String> friendNames = user.getFriendNameList();
        List<User> friends = friendNames.stream()
                .map(friendName -> userRepo.findByName(friendName).orElse(null))
                .filter(friend -> friend != null)
                .collect(Collectors.toList());

        return IntStream.range(0, friends.size())
                .boxed()
                .collect(Collectors.toMap(
                        i -> "user" + (i + 1),
                        i -> {
                            User friend = friends.get(i);
                            Preferences preference = prefRepo.findById(friend.getPrefId()).orElse(null);
                            return new UserWithPrefDto.ReadFriend(friend, preference);
                        }
                ));
    }
    public Map<String, Object> getGroupDaily(String groupId) {
        List<User> users = userRepo.findByGroupId(groupId);
        return IntStream.range(0, users.size())
                .boxed()
                .collect(Collectors.toMap(
                        i -> "user" + (i + 1),
                        i -> {
                            User user = users.get(i);
                            if (user.isDaily()) {
                                TodayPreferences todayPreferences = user.getTodayPreferences();
                                Preferences preferences = prefRepo.findById(user.getPrefId()).orElse(null);
                                return new TodayPrefDto.Read(todayPreferences, preferences, user);
                            } else {
                                Preferences preferences = prefRepo.findById(user.getPrefId()).orElse(null);
                                return new UserWithPrefDto.ReadGroup(user, preferences);
                            }
                        }
                ));
    }
    public Object getUserDaily(String name) {
        User user = userRepo.findByName(name).orElseThrow(() -> new RuntimeException("User not found"));

        if (user.isDaily()) {
            TodayPreferences todayPreferences = user.getTodayPreferences();
            Preferences preferences = prefRepo.findById(user.getPrefId()).orElse(null);
            return new TodayPrefDto.Read(todayPreferences, preferences, user);
        } else {
            Preferences preferences = prefRepo.findById(user.getPrefId()).orElse(null);
            return new UserWithPrefDto.ReadGroup(user, preferences);
        }
    }

}
