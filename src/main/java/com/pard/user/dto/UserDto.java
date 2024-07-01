package com.pard.user.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.pard.user.entity.User;
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
        private boolean isDaily;
        private boolean isGrouped;

        public Read(User user) {
            this.uid = user.getUid();
            this.name = user.getName();
            this.imageId = user.getImageId();
            this.isDaily = user.isDaily();
            this.isGrouped = user.isGrouped();
        }
    }

        @Setter
        @Getter
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class ReadGroup {
            private String uid;
            private String name;
            private int imageId;
            private boolean isDaily;
            private String groupId;

            public ReadGroup(User user) {
                this.uid = user.getUid();
                this.name = user.getName();
                this.imageId = user.getImageId();
                this.isDaily = user.isDaily();
                this.groupId = user.getGroup().getId();
            }
    }


}
