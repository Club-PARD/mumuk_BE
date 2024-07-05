package com.pard.user.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.pard.user.repo.UserRepo;
import com.pard.user.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResetDailyService {

    @Autowired
    private UserRepo userRepo;

    @Transactional
    @Scheduled(cron = "0 0 5 * * ?") // This cron expression means "At 05:00 AM every day"
    public void resetDailyStatus() {
        List<User> users = userRepo.findAll(); // Assuming you have a method to get all users
        for (User user : users) {
            user.setDaily(false);
        }
        userRepo.saveAll(users); // Assuming you have a method to save a list of users
    }
}
