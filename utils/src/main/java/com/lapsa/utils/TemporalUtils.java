package com.lapsa.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public final class TemporalUtils {

    private static final ZoneId DEFAULT_TZ = ZoneId.systemDefault();

    public static final LocalDateTime toLocalDateTime(Date date) {
	return date == null ? null : LocalDateTime.ofInstant(date.toInstant(), DEFAULT_TZ);
    }

    public static final LocalDateTime toLocalDateTime(Calendar calendar) {
	return calendar == null ? null : LocalDateTime.ofInstant(calendar.toInstant(), DEFAULT_TZ);
    }

    public static final LocalDateTime toLocalDateTime(LocalDate localDate) {
	return localDate == null ? null : localDate.atStartOfDay();
    }

    public static final LocalDate toLocalDate(Date date) {
	return date == null ? null : toLocalDate(toLocalDateTime(date));
    }

    public static final LocalDate toLocalDate(Calendar calendar) {
	return calendar == null ? null : toLocalDate(toLocalDateTime(calendar));
    }

    public static final LocalDate toLocalDate(LocalDateTime localDateTime) {
	return localDateTime == null ? null : localDateTime.toLocalDate();
    }

    public static final Calendar toCalendar(Date date) {
	if (date == null)
	    return null;
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(date);
	return calendar;
    }

    public static final Calendar toCalendar(LocalDate localDate) {
	return toCalendar(toDate(localDate));
    }

    public static final Calendar toCalendar(LocalDateTime localDateTime) {
	return toCalendar(toDate(localDateTime));
    }

    public static final Date toDate(Calendar calendar) {
	return calendar == null ? null : calendar.getTime();
    }

    public static final Date toDate(LocalDate localDate) {
	return localDate == null ? null
		: Date.from(localDate.atStartOfDay().atZone(DEFAULT_TZ).toInstant());
    }

    public static final Date toDate(LocalDateTime localDateTime) {
	return localDateTime == null ? null
		: Date.from(localDateTime.atZone(DEFAULT_TZ).toInstant());
    }

}
