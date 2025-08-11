package com.arnavgpt.assistantbackend.dto.friendDto;

import jakarta.validation.constraints.*;

import java.util.UUID;

public record CreateFriendCommand(
        @NotNull UUID userId,
        @NotBlank @Size(max=200) String fullName,
        @Min(1) @Max(12) int dobMonth,
        @Min(1) @Max(31) int dobDay,
        @Min(1900) @Max(3000) Integer dobYear, // nullable allowed; controller will pass null
        @Size(max=50) String phone,
        @Email @Size(max=320) String email,
        @Size(max=500) String notes
) {}

