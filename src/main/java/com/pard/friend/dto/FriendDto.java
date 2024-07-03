package com.pard.friend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pard.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class FriendDto {
    @Setter
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Read {
        private String uid;
        private String name;
        private int imageId;
        private boolean isGrouped;
        private boolean isDaily;
        private boolean isReady;

        public Read(User user) {
            this.uid = user.getUid();
            this.name = user.getName();
            this.imageId = user.getImageId();
            this.isGrouped = user.isGrouped();
            this.isDaily = user.isDaily();
            this.isReady = user.isReady();
        }
    }

}
