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
    @Operation(summary = "유저 등록", description = "모든 필드 null 불가")
    public String createUser(@RequestBody UserDto.Create dto){
        userService.createUser(dto);
        return "추가됨";
    }
    @Operation(summary = "모든 유저 조회 또는 특정 유저 조회", description = "특정 유저 조회 시 uid 필수")
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

