package com.arnavgpt.assistantbackend.dto.reminderDto;

import java.time.LocalDate;
import java.util.List;

public record UpcomingBirthdaysResponse(LocalDate from, LocalDate to, String timezone, List<BirthdayItem> birthdays) {}
