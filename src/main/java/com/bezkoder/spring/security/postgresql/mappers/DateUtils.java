package com.bezkoder.spring.security.postgresql.mappers;

import com.bezkoder.spring.security.postgresql.enums.DateFormatPattern;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {
    public static Date parseDate(final String dateString, final DateFormatPattern pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern.getValue());
        sdf.setLenient(false);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String formatDate(final Date date, final DateFormatPattern pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern.getValue());
        return sdf.format(date);
    }
}
