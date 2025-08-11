package com.arnavgpt.assistantbackend.dto.userDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserCommand(
        @NotBlank @Size(max=200) String displayName,
        @Email @Size(max=320) String email,
        @NotBlank @Size(max=100) String timezone
) {}

