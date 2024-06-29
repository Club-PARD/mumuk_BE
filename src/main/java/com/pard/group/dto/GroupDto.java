package com.pard.group.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pard.group.entity.Group;
import com.pard.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class GroupDto {

    @Setter
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Create {
        private String groupId;
        private List<String> userUids;

    }

    @Setter
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Read {
        private String groupId;
        private List<String> userUids;

        public Read(Group group) {
            this.groupId = group.getUsers().stream().findFirst().orElse(null).getUid(); // Assuming the first user is the creator
            this.userUids = group.getUsers().stream().map(User::getUid).collect(Collectors.toList());
        }
    }
}
