package com.arnavgpt.assistantbackend.dto.reminderDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record BirthdayItem(UUID friendId, String fullName, LocalDate occursOn, Integer turningAge) {}

