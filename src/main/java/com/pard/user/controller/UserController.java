package com.pard.user.controller;

//import com.pard.gpt.service.ChatGPTService;
//import com.pard.gpt.service.GptService;
import com.pard.user.dto.UserDto;
import com.pard.user.dto.UserWithPrefDto;
import com.pard.user.exception.UserNotFoundException;
import com.pard.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
   // private final ChatGPTService gptService;

    @PostMapping("/create")
    @Operation(summary = "유저 등록", description = "여기서 쓰시면 됩니다.")
    public String createUser(@RequestBody UserDto.Create dto){
        userService.createUser(dto);
        return "추가됨";
    }

    @GetMapping("/users")
    public List<UserDto.Read> findUser(@RequestParam(value = "uid", required = false) String uid) {
        if (StringUtils.hasText(uid)) {
            try {
                return Collections.singletonList(userService.findByUid(uid));
            } catch (UserNotFoundException e) {
                return Collections.emptyList();
            }
        }
        List<UserDto.Read> allUsers = userService.readAll();
        if (allUsers.isEmpty()) {
            return Collections.emptyList();
        }
        return allUsers;
    }

    @GetMapping("/{uid}/with-pref")
    public ResponseEntity<UserWithPrefDto> getUserWithPref(@PathVariable String uid) {
        try {
            UserWithPrefDto userWithPref = userService.getUserWithPref(uid);
            return ResponseEntity.ok(userWithPref);
        } catch (Exception e) {
            logger.error("Error fetching user with preferences: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/users-with-pref")
    public ResponseEntity<Map<String, List<UserWithPrefDto>>> getUsersWithPref(@RequestParam List<String> uids) {
        logger.info("Fetching users with preferences for UIDs: {}", uids);
        try {
            List<UserWithPrefDto> usersWithPref = userService.getUsersWithPref(uids);
            Map<String, List<UserWithPrefDto>> response = new HashMap<>();
            response.put("users", usersWithPref);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error fetching users with preferences: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
/*
    @PostMapping("/users-with-pref/gpt")
    public ResponseEntity<Map<String, Object>> getUsersWithPrefAndSendToGpt(@RequestBody List<String> uids) {
        logger.info("Processing users with preferences for UIDs: {}", uids);
        try {
            // Step 1: Fetch user preferences data
            List<UserWithPrefDto> usersWithPref = userService.getUsersWithPref(uids);

            // Step 2: Send fetched data to GPT service
            //Map<String, Object> gptResponse = gptService.sendDataToGpt(usersWithPref);

            // Step 3: Return the response from the GPT service
            //return ResponseEntity.ok(gptResponse);
        } catch (Exception e) {
            logger.error("Error processing users with preferences: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
 */
}
