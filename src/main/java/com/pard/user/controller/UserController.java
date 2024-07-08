package com.pard.user.controller;

import com.pard.user.dto.UserDto;
import com.pard.user.exception.UserNotFoundException;
import com.pard.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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

    @Operation(summary = "uid 또는 name이 이미 있는지 없는지 체크", description = "이미 있으면 true 없으면 false")
    @GetMapping("/checkExists")
    public boolean findUser(@RequestParam(value = "uid", required = false) String uid,
                            @RequestParam(value = "name", required = false) String name) {
        return userService.checkExists(uid, name);
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


    @Operation(summary = "유저의 이미지 또는 name Update", description = "uid랑 imageId 또는 name을 보내주면 해당 imageId 또는 name으로 update")
    @PutMapping("/{uid}")
    public ResponseEntity<String> updateUser(@PathVariable String uid,
                                             @RequestParam(value = "imageId", required = false) Integer imageId,
                                             @RequestParam(value = "name", required = false) String name) {
        try {
            boolean isUpdated = userService.updateUser(uid, imageId, name);
            if (isUpdated) {
                return ResponseEntity.ok("업데이트 완료");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("업데이트할 정보가 없습니다.");
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error updating user: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업데이트 중 오류 발생");
        }
    }

    @Operation(summary = "유저 삭제", description = "uid로 유저 삭제")
    @DeleteMapping("/{uid}")
    public ResponseEntity<String> deleteUser(@PathVariable String uid) {
        try {
            userService.deleteUser(uid);
            return ResponseEntity.ok("삭제 완료");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error deleting user: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 중 오류 발생");
        }
    }
}


