package com.arnavgpt.assistantbackend.controller;

import com.arnavgpt.assistantbackend.dto.friendDto.CreateFriendCommand;
import com.arnavgpt.assistantbackend.dto.friendDto.FriendDto;
import com.arnavgpt.assistantbackend.dto.friendDto.UpdateFriendCommand;
import com.arnavgpt.assistantbackend.service.template.FriendService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/friends")
public class FriendController {

    private final FriendService friends;

    public FriendController(FriendService friends) { this.friends = friends; }

    @PostMapping
    public FriendDto create(@Valid @RequestBody CreateFriendCommand cmd) {
        return friends.create(cmd);
    }

    @GetMapping
    public List<FriendDto> list(@RequestParam UUID userId) {
        return friends.listByUser(userId);
    }

    @PatchMapping("/{id}")
    public FriendDto update(@PathVariable UUID id, @RequestBody UpdateFriendCommand body) {
        var cmd = new UpdateFriendCommand(
                id,
                body.fullName(), body.dobMonth(), body.dobDay(), body.dobYear(),
                body.phone(), body.email(), body.notes()
        );
        return friends.update(cmd);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        friends.delete(id);
    }
}
