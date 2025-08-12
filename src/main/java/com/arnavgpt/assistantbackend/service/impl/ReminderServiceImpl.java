package com.arnavgpt.assistantbackend.service.impl;

import com.arnavgpt.assistantbackend.dto.reminderDto.BirthdayItem;
import com.arnavgpt.assistantbackend.dto.reminderDto.TodayBirthdaysResponse;
import com.arnavgpt.assistantbackend.dto.reminderDto.UpcomingBirthdaysResponse;
import com.arnavgpt.assistantbackend.entities.AppUser;
import com.arnavgpt.assistantbackend.repositories.AppUserRepository;
import com.arnavgpt.assistantbackend.repositories.FriendRepository;
import com.arnavgpt.assistantbackend.service.template.ReminderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.*;
import java.util.Comparator;
import com.arnavgpt.assistantbackend.util.BirthdayDates;

@Service
@Transactional(readOnly = true)
public class ReminderServiceImpl implements ReminderService {

    private final AppUserRepository users;
    private final FriendRepository friends;

    public ReminderServiceImpl(AppUserRepository users, FriendRepository friends) {
        this.users = users; this.friends = friends;
    }

    @Override
    public TodayBirthdaysResponse birthdaysToday(java.util.UUID userId) {
        AppUser user = users.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        ZoneId zone = ZoneId.of(user.getTimezone());
        LocalDate today = ZonedDateTime.now(zone).toLocalDate();

        var items = friends.findByUser_Id(userId).stream()
                .map(f -> {
                    LocalDate next = BirthdayDates.nextOccurrence(today, f.getDobMonth(), f.getDobDay());
                    if (!next.equals(today)) return null;
                    Integer turning = f.getDobYear() == null ? null : next.getYear() - f.getDobYear();
                    return new BirthdayItem(f.getId(), f.getFullName(), next, turning);
                })
                .filter(x -> x != null)
                .sorted(Comparator.comparing(BirthdayItem::occursOn).thenComparing(BirthdayItem::fullName))
                .toList();

        return new TodayBirthdaysResponse(today, user.getTimezone(), items);
    }

    @Override
    public UpcomingBirthdaysResponse upcomingBirthdays(java.util.UUID userId, int days) {
        if (days < 1 || days > 365) throw new IllegalArgumentException("days must be between 1 and 365");
        AppUser user = users.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        ZoneId zone = ZoneId.of(user.getTimezone());
        LocalDate start = ZonedDateTime.now(zone).toLocalDate();
        LocalDate end = start.plusDays(days);

        var items = friends.findByUser_Id(userId).stream()
                .map(f -> {
                    LocalDate next = BirthdayDates.nextOccurrence(start, f.getDobMonth(), f.getDobDay());
                    if (next.isAfter(end)) return null;
                    Integer turning = f.getDobYear() == null ? null : next.getYear() - f.getDobYear();
                    return new BirthdayItem(f.getId(), f.getFullName(), next, turning);
                })
                .filter(x -> x != null)
                .sorted(Comparator
                        .comparing(BirthdayItem::occursOn)
                        .thenComparing(BirthdayItem::fullName))
                .toList();

        return new UpcomingBirthdaysResponse(start, end, user.getTimezone(), items);
    }
}
