package com.arnavgpt.assistantbackend.service.template;

import com.arnavgpt.assistantbackend.dto.reminderDto.TodayBirthdaysResponse;
import com.arnavgpt.assistantbackend.dto.reminderDto.UpcomingBirthdaysResponse;
import java.util.UUID;

public interface ReminderService {
    TodayBirthdaysResponse birthdaysToday(UUID userId);
    UpcomingBirthdaysResponse upcomingBirthdays(UUID userId, int days);
}
