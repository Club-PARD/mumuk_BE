package com.pard.user.controller;

import com.pard.user.dto.UserDto;
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
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @PostMapping("/create")
    @Operation(summary = "유저 등록", description = "모든 필드 null 불가")
    public String createUser(@RequestBody UserDto.Create dto) {
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

    @Operation(summary = "유저의 이미지 Update", description = "uid랑 imageId 보내주면 해당 imageId로 update")
    @PutMapping("/{uid}")
    public ResponseEntity<String> imageUpdate(@PathVariable String uid, @RequestParam Integer imageId) {
        try {
            userService.updateImage(uid, imageId);
            return ResponseEntity.ok("이미지 업데이트 완료");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body("User not found: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error updating image: {}", e.getMessage());
            return ResponseEntity.status(500).body("이미지 업데이트 중 오류 발생");
        }

    }
}


