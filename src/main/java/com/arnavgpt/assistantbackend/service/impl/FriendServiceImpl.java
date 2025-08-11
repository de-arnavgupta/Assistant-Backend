package com.arnavgpt.assistantbackend.service.impl;


import com.arnavgpt.assistantbackend.dto.friendDto.CreateFriendCommand;
import com.arnavgpt.assistantbackend.dto.friendDto.FriendDto;
import com.arnavgpt.assistantbackend.entities.AppUser;
import com.arnavgpt.assistantbackend.repositories.AppUserRepository;
import com.arnavgpt.assistantbackend.repositories.FriendRepository;
import com.arnavgpt.assistantbackend.service.template.FriendService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friends;
    private final AppUserRepository users;

    public FriendServiceImpl(FriendRepository friends, AppUserRepository users) {
        this.friends = friends; this.users = users;
    }

    @Override
    public FriendDto create(CreateFriendCommand cmd) {
        BirthdayValidator.validateMonthDay(cmd.dobMonth(), cmd.dobDay(), cmd.dobYear());
        AppUser user = users.findById(cmd.userId()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        var f = new Friend();
        f.setUser(user);
        f.setFullName(cmd.fullName());
        f.setDobMonth(cmd.dobMonth());
        f.setDobDay(cmd.dobDay());
        f.setDobYear(cmd.dobYear());
        f.setPhone(cmd.phone());
        f.setEmail(cmd.email());
        f.setNotes(cmd.notes());

        var saved = friends.save(f);
        return map(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FriendDto> listByUser(UUID userId) {
        return friends.findByUser_Id(userId).stream().map(this::map).toList();
    }

    @Override
    public FriendDto update(UpdateFriendCommand cmd) {
        var f = friends.findById(cmd.id()).orElseThrow(() -> new IllegalArgumentException("Friend not found"));

        if (cmd.fullName() != null) f.setFullName(cmd.fullName());
        if (cmd.dobMonth() != null) f.setDobMonth(cmd.dobMonth());
        if (cmd.dobDay() != null) f.setDobDay(cmd.dobDay());
        if (cmd.dobYear() != null) f.setDobYear(cmd.dobYear());
        if (cmd.phone() != null) f.setPhone(cmd.phone());
        if (cmd.email() != null) f.setEmail(cmd.email());
        if (cmd.notes() != null) f.setNotes(cmd.notes());

        BirthdayValidator.validateMonthDay(f.getDobMonth(), f.getDobDay(), f.getDobYear());

        var saved = friends.save(f);
        return map(saved);
    }

    @Override
    public void delete(UUID friendId) {
        if (!friends.existsById(friendId)) throw new IllegalArgumentException("Friend not found");
        friends.deleteById(friendId);
    }

    private FriendDto map(Friend f) {
        return new FriendDto(
                f.getId(),
                f.getUser().getId(),
                f.getFullName(),
                f.getDobMonth(),
                f.getDobDay(),
                f.getDobYear(),
                f.getPhone(),
                f.getEmail(),
                f.getNotes()
        );
    }
}
