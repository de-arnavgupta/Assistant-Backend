package com.arnavgpt.assistantbackend.service.impl;

import com.arnavgpt.assistantbackend.dto.userDto.CreateUserCommand;
import com.arnavgpt.assistantbackend.dto.userDto.UserDto;
import com.arnavgpt.assistantbackend.entities.AppUser;
import com.arnavgpt.assistantbackend.repositories.AppUserRepository;
import com.arnavgpt.assistantbackend.service.template.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final AppUserRepository users;

    public UserServiceImpl(AppUserRepository users) { this.users = users; }

    @Override
    public UserDto create(CreateUserCommand cmd) {
        var u = new AppUser();
        u.setDisplayName(cmd.displayName());
        u.setEmail(cmd.email());
        u.setTimezone(cmd.timezone());
        var saved = users.save(u);
        return new UserDto(saved.getId(), saved.getDisplayName(), saved.getEmail(), saved.getTimezone());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto get(UUID id) {
        var u = users.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return new UserDto(u.getId(), u.getDisplayName(), u.getEmail(), u.getTimezone());
    }
}
