package com.pard.user.dto;

import com.pard.preferences.entity.Preferences;
import com.pard.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
public class UserWithPrefDto {
    private User user;
    private Preferences preference;
}
