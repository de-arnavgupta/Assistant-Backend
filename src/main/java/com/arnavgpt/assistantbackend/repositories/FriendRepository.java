package com.arnavgpt.assistantbackend.repositories;

import com.arnavgpt.assistantbackend.entities.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FriendRepository extends JpaRepository<Friend, UUID> {
    List<Friend> findByUser_Id(UUID userId);
}
