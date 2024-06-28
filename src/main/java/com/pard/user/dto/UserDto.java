package com.pard.user.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.pard.preferences.dto.PrefDto;
import com.pard.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class UserDto {
    @Setter
    @Getter
    public static class Create{
        private String uid;
        private String name;
        private Integer imageId;
    }

    @Setter
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Read{
        private String uid;
        private String name;
        private Integer imageId;
        private PrefDto preferences;



        public Read(User user) {
            this.uid = user.getUid();
            this.name = user.getName();
            this.imageId = user.getImageId();
        }
    }
}
