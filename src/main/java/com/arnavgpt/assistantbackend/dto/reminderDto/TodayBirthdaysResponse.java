package com.arnavgpt.assistantbackend.dto.reminderDto;

import java.time.LocalDate;
import java.util.List;

public record TodayBirthdaysResponse(LocalDate date, String timezone, List<BirthdayItem> birthdays) {}
