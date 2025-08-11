package com.arnavgpt.assistantbackend.service.template;


import com.arnavgpt.assistantbackend.dto.friendDto.CreateFriendCommand;
import com.arnavgpt.assistantbackend.dto.friendDto.FriendDto;
import com.arnavgpt.assistantbackend.dto.friendDto.UpdateFriendCommand;
import java.util.List;
import java.util.UUID;

public interface FriendService {
    FriendDto create(CreateFriendCommand cmd);
    List<FriendDto> listByUser(UUID userId);
    FriendDto update(UpdateFriendCommand cmd);
    void delete(UUID friendId);
}
