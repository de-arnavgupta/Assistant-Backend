package com.arnavgpt.assistantbackend.controller;

import com.arnavgpt.assistantbackend.dto.reminderDto.TodayBirthdaysResponse;
import com.arnavgpt.assistantbackend.dto.reminderDto.UpcomingBirthdaysResponse;
import com.arnavgpt.assistantbackend.service.template.ReminderService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reminders")
public class ReminderController {

    private final ReminderService reminders;

    public ReminderController(ReminderService reminders) { this.reminders = reminders; }

    @GetMapping("/today")
    public TodayBirthdaysResponse today(@RequestParam UUID userId) {
        return reminders.birthdaysToday(userId);
    }

    @GetMapping("/upcoming")
    public UpcomingBirthdaysResponse upcoming(@RequestParam UUID userId,
                                              @RequestParam(defaultValue = "14") int days) {
        return reminders.upcomingBirthdays(userId, days);
    }
}
