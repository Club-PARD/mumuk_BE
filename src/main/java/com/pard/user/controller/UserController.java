package com.pard.user.controller;


import com.pard.user.dto.UserDto;
import com.pard.user.exception.UserNotFoundException;
import com.pard.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private  final UserService userService;

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

}
