package com.pard.group.repo;

import com.pard.group.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.ZonedDateTime;
import java.util.List;

public interface GroupRepo extends JpaRepository<Group, String> {
    List<Group> findByCreatedAtBefore(ZonedDateTime dateTime);  // Ensure this method is correct
}