package com.arnavgpt.assistantbackend.dto.userDto;

import java.util.UUID;

public record UserDto(UUID id, String displayName, String email, String timezone) {}
