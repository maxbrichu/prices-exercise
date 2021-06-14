package com.example.pricesexercise.core.infrastructure.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateUtils {
    public static long dateStringToEpoch(String dateString) {
        LocalDateTime dateTime = LocalDateTime.parse(formatDateString(dateString));
        return dateTime.toEpochSecond(ZoneOffset.UTC);
    }

    private static CharSequence formatDateString(String dateString) {
        return dateString.substring(0,10) + "T" + dateString.substring(11).replace(".", ":");
    }
}
