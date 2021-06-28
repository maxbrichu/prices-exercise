package com.example.pricesexercise.core.infrastructure.utils;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String defaultFormat = "yyyy-MM-dd-HH.mm.ss";
    public static LocalDateTime dateStringToDate(String dateString) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(defaultFormat);
        return LocalDateTime.parse(dateString, formatter);
    }

    public static LocalDateTime dateStringToDate(String dateString, String format) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(dateString, formatter);
    }

    public static String dateToString(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(defaultFormat);
        return  date.format(formatter);
    }
}
