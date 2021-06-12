package com.example.pricesexercise.core.infrastructure.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final String STRING_DATE_FORMAT = "yyyy-MM-dd-HH.mm.ss";
    private static final SimpleDateFormat stringFormat = new SimpleDateFormat(STRING_DATE_FORMAT);
    public static Date stringToDate(String dateString) throws ParseException {
        return stringFormat.parse(dateString);
    }
}
