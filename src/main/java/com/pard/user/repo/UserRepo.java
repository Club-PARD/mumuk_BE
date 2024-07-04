package com.pard.user.repo;

import com.pard.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUid(String uid);

    List<User> findByGroupId(String groupId);
    Optional<User> findByName(String name);

}
