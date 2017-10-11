package com.lapsa.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public final class TemporalUtils {

    private static final ZoneId DEFAULT_TZ = ZoneId.systemDefault();

    public static enum DateBase {
	START_OF_EPOCH, TODAY, FAR_PAST;
    }

    // toLocalDateTime family

    public static final LocalDateTime toLocalDateTime(Date date) {
	return date == null ? null : LocalDateTime.ofInstant(date.toInstant(), DEFAULT_TZ);
    }

    public static final LocalDateTime toLocalDateTime(Calendar calendar) {
	return calendar == null ? null
		: LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
    }

    public static final LocalDateTime toLocalDateTime(LocalDate localDate) {
	return localDate == null ? null : localDate.atStartOfDay();
    }

    public static final LocalDateTime toLocalDateTime(LocalTime localTime) {
	return toLocalDateTime(localTime, DateBase.START_OF_EPOCH);
    }

    public static final LocalDateTime toLocalDateTime(LocalTime localTime, DateBase dateBase) {
	if (localTime == null)
	    return null;
	if (dateBase == null)
	    return null;
	LocalDate base;
	switch (dateBase) {
	case TODAY:
	    base = LocalDate.now();
	    break;
	case FAR_PAST:
	    base = LocalDate.MIN;
	    break;
	case START_OF_EPOCH:
	default:
	    base = LocalDate.ofEpochDay(0);
	    break;
	}
	return localTime.atDate(base);
    }

    // toLocalDate family

    public static final LocalDate toLocalDate(Date date) {
	return date == null ? null : toLocalDate(toLocalDateTime(date));
    }

    public static final LocalDate toLocalDate(Calendar calendar) {
	return calendar == null ? null : toLocalDate(toLocalDateTime(calendar));
    }

    public static final LocalDate toLocalDate(LocalDateTime localDateTime) {
	return localDateTime == null ? null : localDateTime.toLocalDate();
    }

    // toCalendar family

    public static final Calendar toCalendar(Date date) {
	if (date == null)
	    return null;
	Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TZ));
	calendar.setTime(date);
	return calendar;
    }

    public static final Calendar toCalendar(LocalDate localDate) {
	return toCalendar(toDate(localDate));
    }

    public static final Calendar toCalendar(LocalDateTime localDateTime) {
	return toCalendar(toDate(localDateTime));
    }

    public static final Calendar toCalendar(LocalTime localTime) {
	return toCalendar(toDate(toLocalDateTime(localTime)));
    }

    public static final Calendar toCalendar(LocalTime localTime, DateBase dateBase) {
	return toCalendar(toDate(toLocalDateTime(localTime, dateBase)));
    }

    // toDate family

    public static Date toDate(Instant instant) {
	return instant == null ? null : Date.from(instant);
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

    public static final Date toDate(LocalTime localTime) {
	return toDate(toLocalDateTime(localTime));
    }

    public static final Date toDate(LocalTime localTime, DateBase dateBase) {
	return toDate(toLocalDateTime(localTime, dateBase));
    }

    // toLocalTime family

    public static final LocalTime toLocalTime(Calendar calendar) {
	return toLocalTime(toLocalDateTime(calendar));
    }

    public static final LocalTime toLocalTime(LocalDateTime localDateTime) {
	return localDateTime == null ? null : localDateTime.toLocalTime();
    }

    public static final LocalTime toLocalTime(Date date) {
	return toLocalTime(toLocalDateTime(date));
    }
}
