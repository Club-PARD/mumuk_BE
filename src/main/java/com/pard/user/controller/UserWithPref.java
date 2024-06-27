package com.pard.user.controller;

import com.pard.user.dto.UserWithPrefDto;
import com.pard.user.service.UserWithPrefService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserWithPref {

    private static final Logger logger = LoggerFactory.getLogger(UserWithPref.class);
    private final UserWithPrefService userWithPrefService;

    @GetMapping("/{uid}/with-pref")
    @Operation(summary = "유저의 선호도와 함께 유저 데이터 조회", description = "유저 uid 필요")
    public ResponseEntity<UserWithPrefDto> getUserWithPref(@PathVariable String uid) {
        try {
            UserWithPrefDto userWithPref = userWithPrefService.getUserWithPref(uid);
            return ResponseEntity.ok(userWithPref);
        } catch (Exception e) {
            logger.error("Error fetching user with preferences: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/users-with-pref")
    @Operation(summary = "여러 유저들의 선호도와 함께 유저 데이터 조회", description = "유저 uid 리스트가 필요")
    public ResponseEntity<Map<String, List<UserWithPrefDto>>> getUsersWithPref(@RequestParam List<String> uids) {
        logger.info("Fetching users with preferences for UIDs: {}", uids);
        try {
            List<UserWithPrefDto> usersWithPref = userWithPrefService.getUsersWithPref(uids);
            Map<String, List<UserWithPrefDto>> response = new HashMap<>();
            response.put("users", usersWithPref);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error fetching users with preferences: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}
