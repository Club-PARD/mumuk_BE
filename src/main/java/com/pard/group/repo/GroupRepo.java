package com.pard.group.repo;

import com.pard.group.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<Group, String> {
}