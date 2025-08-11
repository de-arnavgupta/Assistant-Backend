package com.arnavgpt.assistantbackend.service.template;

import com.arnavgpt.assistantbackend.dto.userDto.CreateUserCommand;
import com.arnavgpt.assistantbackend.dto.userDto.UserDto;
import java.util.UUID;

public interface UserService {
    UserDto create(CreateUserCommand cmd);
    UserDto get(UUID id);
}
