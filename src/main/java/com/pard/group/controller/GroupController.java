package com.pard.group.controller;

import com.pard.group.dto.GroupDto;
import com.pard.group.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<Void> createGroup(@RequestBody GroupDto.Create groupDto) {
        groupService.createGroup(groupDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupDto.Read> getGroup(@PathVariable String groupId) {
        GroupDto.Read group = groupService.getGroup(groupId);
        return ResponseEntity.ok(group);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable String groupId) {
        groupService.deleteGroup(groupId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
