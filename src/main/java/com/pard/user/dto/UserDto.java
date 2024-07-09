package com.pard.user.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.pard.user.entity.User;
import com.pard.group.entity.Group;
import lombok.Getter;
import lombok.Setter;



public class UserDto {
    @Setter
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Create {
        private String uid;
        private String name;
        private int imageId;
    }

    @Setter
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Read {
        private String uid;
        private String name;
        private int imageId;
        private String groupId;
        private boolean isDaily;
        private boolean isGrouped;
        private boolean isResult;

        public Read(User user) {
            this.uid = user.getUid();
            this.name = user.getName();
            this.imageId = user.getImageId();
            this.groupId = (user.getGroup() != null) ? user.getGroup().getId() : null; // Null check
            this.isDaily = user.isDaily();
            this.isGrouped = user.isGrouped();
            this.isResult = (user.getGroup() != null) ? user.getGroup().isResult() : false;
        }
    }


}
