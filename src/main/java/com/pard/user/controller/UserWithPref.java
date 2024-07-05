package com.pard.user.controller;

import com.pard.user.dto.UserWithPrefDto;
import com.pard.user.service.UserWithPrefService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserWithPref {

    private static final Logger logger = LoggerFactory.getLogger(UserWithPref.class);
    private final UserWithPrefService userWithPrefService;

    @GetMapping("/with-pref/{name}")
    @Operation(summary = "유저의 선호도와 함께 유저 데이터 조회", description = "유저 uid 필요")
    public ResponseEntity<UserWithPrefDto.Read> getUserWithPref(@PathVariable String name) {
        try {
            UserWithPrefDto.Read userWithPref = userWithPrefService.getUserWithPref(name);
            return ResponseEntity.ok(userWithPref);
        } catch (Exception e) {
            logger.error("Error fetching user with preferences: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/with-pref/group")
    @Operation(summary = "그룹 안 유저들의 선호도와 함께 유저 데이터 조회", description = "유저 group id 필요")
    public ResponseEntity<Map<String, UserWithPrefDto.Read>> getUsersWithPref(@RequestParam String groupId) {
        try {
            Map<String, UserWithPrefDto.Read> usersWithPref = userWithPrefService.getUsersWithPref(groupId);
            return ResponseEntity.ok(usersWithPref);
        } catch (Exception e) {
            logger.error("Error fetching users with preferences: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/with-pref/friend")
    @Operation(summary = "친구들의 고정 선호도와 함께 유저 데이터 조회", description = "유저 name 필요")
    public ResponseEntity<Map<String, UserWithPrefDto.ReadFriend>> getFriendsPref(@RequestParam String name) {
        try {
            Map<String, UserWithPrefDto.ReadFriend> friendsWithPref = userWithPrefService.getFriendsPref(name);
            return ResponseEntity.ok(friendsWithPref);
        } catch (Exception e) {
            logger.error("Error fetching friends with preferences: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/with-pref/daily/group")
    public ResponseEntity<Map<String, Object>> getUserDetails(@RequestParam String groupId)  {
        Map<String, Object> userDetails = userWithPrefService.getGroupDaily(groupId);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }
    @GetMapping("/with-pref/daily/{name}")
    public ResponseEntity<Object> getUserDaily(@PathVariable String name) {
        Object userDetails = userWithPrefService.getUserDaily(name);
        return ResponseEntity.ok(userDetails);
    }

}
