package com.arnavgpt.assistantbackend.util;

import java.time.LocalDate;

public final class BirthdayDates {
    private BirthdayDates() {}

    // Policy: Feb 29 -> remind on Feb 28 in non-leap years
    public static LocalDate nextOccurrence(LocalDate anchor, int month, int day) {
        int year = anchor.getYear();
        int d = day;
        int m = month;

        if (m == 2 && d == 29 && !anchor.isLeapYear()) {
            // anchor year may be non-leap; still compute "today" first, then future
            if (anchor.getMonthValue() > 2 || (anchor.getMonthValue() == 2 && anchor.getDayOfMonth() > 28)) {
                // move to next year
                year += 1;
                if (!isLeap(year)) return LocalDate.of(year, 2, 28);
                else return LocalDate.of(year, 2, 29);
            } else {
                return LocalDate.of(year, 2, 28);
            }
        }

        LocalDate thisYear = LocalDate.of(year, m, Math.min(d, LocalDate.of(year, m, 1).lengthOfMonth()));
        if (!thisYear.isBefore(anchor)) return thisYear;

        int nextYear = year + 1;
        if (m == 2 && d == 29 && !isLeap(nextYear)) return LocalDate.of(nextYear, 2, 28);
        return LocalDate.of(nextYear, m, Math.min(d, LocalDate.of(nextYear, m, 1).lengthOfMonth()));
    }

    private static boolean isLeap(int y) {
        return (y % 4 == 0) && ((y % 100 != 0) || (y % 400 == 0));
    }
}
