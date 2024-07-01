package com.pard.user.controller;

import com.pard.user.exception.UserNotFoundException;
import com.pard.user.service.FriendService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friend")
public class FriendController {
    private static final Logger logger = LoggerFactory.getLogger(FriendController.class);
    private final FriendService friendService;

    @PostMapping("/add")
    @Operation(summary = "친구 추가", description = "닉네임으로 친구를 추가합니다.")
    public ResponseEntity<String> addFriend(@RequestParam String userName, @RequestParam String friendName) {
        try {
            friendService.addFriend(userName, friendName);
            return ResponseEntity.ok("친구 추가됨");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error adding friend: {}", e.getMessage());
            return ResponseEntity.status(500).body("친구 추가 중 오류 발생");
        }
    }

    @PostMapping("/delete")
    @Operation(summary = "친구 삭제", description = "닉네임으로 친구를 삭제합니다.")
    public ResponseEntity<String> deleteFriend(@RequestParam String userName, @RequestParam String friendName) {
        try {
            friendService.deleteFriend(userName, friendName);
            return ResponseEntity.ok("친구 삭제됨");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body("User not found: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error deleting friend: {}", e.getMessage());
            return ResponseEntity.status(500).body("친구 삭제 중 오류 발생");
        }
    }

    @GetMapping("/{name}")
    @Operation(summary = "친구 목록 조회", description = "사용자의 친구 목록을 조회합니다.")
    public ResponseEntity<List<String>> getFriendList(@PathVariable String name) {
        try {
            List<String> friends = friendService.getFriendList(name);
            return ResponseEntity.ok(friends);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            logger.error("Error fetching friend list: {}", e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }
}
