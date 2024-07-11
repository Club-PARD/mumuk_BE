package com.pard.group.controller;

import com.pard.group.dto.GroupDto;
import com.pard.group.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    @Operation(summary = "그룹 추가", description = "uid list로 그룹을 추가함 가장 먼저 들어오는 uid가 groupId가 됨.\n사용예시 : [\"uid1\", \"uid2\"]")
    public ResponseEntity<Void> createGroup(@RequestBody GroupDto.Create groupDto) {
        groupService.createGroup(groupDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{groupId}")
    @Operation(summary = "그룹Id로 해당 그룹 구성원 확인", description = "uid list를 반환함")
    public ResponseEntity<GroupDto.Read> getGroup(@PathVariable String groupId) {
        GroupDto.Read group = groupService.getGroup(groupId);
        return ResponseEntity.ok(group);
    }

    @DeleteMapping("/{groupId}")
    @Operation(summary = "그룹Id로 해당 그룹 삭제")
    public ResponseEntity<Void> deleteGroup(@PathVariable String groupId) {
        groupService.deleteGroup(groupId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/isResult/{groupId}")
    @Operation(summary = "그룹Id로 해당 그룹 결과 확인", description = "그룹 결과를 반환함")
    public ResponseEntity<Boolean> getGroupResult(@PathVariable String groupId) {
        Boolean result = groupService.getGroupResult(groupId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/allGroup")
    @Operation(summary = "그룹Id로 해당 그룹 삭제")
    public ResponseEntity<Void> deleteAllGroup() {
        groupService.deleteAllGroups();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
