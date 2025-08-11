package com.arnavgpt.assistantbackend.util;

public final class BirthdayValidator {
    private BirthdayValidator() {}

    public static void validateMonthDay(int month, int day, Integer year) {
        if (month < 1 || month > 12) throw new IllegalArgumentException("Invalid month");
        if (day < 1) throw new IllegalArgumentException("Invalid day");

        int maxDay = switch (month) {
            case 1,3,5,7,8,10,12 -> 31;
            case 4,6,9,11 -> 30;
            case 2 -> 29; // allow 29 if year unknown; handle non-leap later
            default -> 31;
        };
        if (day > maxDay) throw new IllegalArgumentException("Invalid day for given month");

        if (year != null && month == 2 && day == 29 && !isLeap(year)) {
            throw new IllegalArgumentException("Feb 29 provided for a non-leap year");
        }
    }

    private static boolean isLeap(int y) {
        return (y % 4 == 0) && ((y % 100 != 0) || (y % 400 == 0));
    }
}
