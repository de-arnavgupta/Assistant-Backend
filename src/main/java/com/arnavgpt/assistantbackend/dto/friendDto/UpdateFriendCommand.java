package com.arnavgpt.assistantbackend.dto.friendDto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateFriendCommand(
        @NotNull UUID id,
        String fullName,
        Integer dobMonth,
        Integer dobDay,
        Integer dobYear,
        String phone,
        String email,
        String notes
) {}
