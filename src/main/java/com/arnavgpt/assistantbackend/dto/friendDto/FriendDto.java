package com.arnavgpt.assistantbackend.dto.friendDto;

import java.util.UUID;

public record FriendDto(
        UUID id,
        UUID userId,
        String fullName,
        int dobMonth,
        int dobDay,
        Integer dobYear,
        String phone,
        String email,
        String notes
) {}
