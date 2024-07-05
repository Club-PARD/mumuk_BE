package com.pard.group.config;

import com.pard.group.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulingConfig {
    private final GroupService groupService;

    @Scheduled(fixedRate = 600000, zone = "Asia/Seoul")
    public void scheduleOldGroupDeletion() {
        groupService.deleteOldGroups();
    }
}

