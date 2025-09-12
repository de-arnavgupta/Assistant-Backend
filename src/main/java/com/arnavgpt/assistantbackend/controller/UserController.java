package com.arnavgpt.assistantbackend.controller;

import com.arnavgpt.assistantbackend.dto.userDto.CreateUserCommand;
import com.arnavgpt.assistantbackend.dto.userDto.UserDto;
import com.arnavgpt.assistantbackend.service.template.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService users;

    public UserController(UserService users) { this.users = users; }

    @PostMapping
    public UserDto create(@Valid @RequestBody CreateUserCommand cmd) {
        return users.create(cmd);
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable UUID id) {
        return users.get(id);
    }
}


