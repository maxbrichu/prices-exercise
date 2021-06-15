package com.example.pricesexercise.core.infrastructure.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String defaultFormat = "yyyy-MM-dd-HH.mm.ss";
    public static Date dateStringToDate(String dateString) throws ParseException {
        return new SimpleDateFormat(defaultFormat).parse(dateString);
    }

    public static Date dateStringToDate(String dateString, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(dateString);
    }

    public static String dateToString(Date date){
        DateFormat df = new SimpleDateFormat(defaultFormat);
        return df.format(date);
    }
}
