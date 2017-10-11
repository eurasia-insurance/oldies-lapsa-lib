package com.lapsa.lapsa.velocitytools;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.velocity.tools.generic.DateTool;

import com.lapsa.utils.TemporalUtils;

public class Java8TemporalTypesDateTool extends DateTool {

    @Override
    public String format(Object obj) {
	if (obj != null) {
	    if (obj instanceof LocalDateTime)
		return super.format(TemporalUtils.toDate((LocalDateTime) obj));
	    if (obj instanceof LocalDate)
		return super.format(TemporalUtils.toDate((LocalDate) obj));
	    if (obj instanceof LocalTime)
		return super.format(TemporalUtils.toDate((LocalTime) obj));
	    if (obj instanceof Instant)
		return super.format(TemporalUtils.toDate((Instant) obj));
	}
	return super.format(obj);
    }

    @Override
    public String format(String format, Object obj, Locale locale, TimeZone timezone) {
	if (obj != null) {
	    if (obj instanceof LocalDateTime)
		return super.format(format, TemporalUtils.toDate((LocalDateTime) obj), locale, timezone);
	    if (obj instanceof LocalDate)
		return super.format(format, TemporalUtils.toDate((LocalDate) obj), locale, timezone);
	    if (obj instanceof LocalTime)
		return super.format(format, TemporalUtils.toDate((LocalTime) obj), locale, timezone);
	    if (obj instanceof Instant)
		return super.format(format, TemporalUtils.toDate((Instant) obj), locale, timezone);
	}
	return super.format(format, obj, locale, timezone);
    }

    @Override
    public String format(String format, Object obj, Locale locale) {
	if (obj != null) {
	    if (obj instanceof LocalDateTime)
		return super.format(format, TemporalUtils.toDate((LocalDateTime) obj), locale);
	    if (obj instanceof LocalDate)
		return super.format(format, TemporalUtils.toDate((LocalDate) obj), locale);
	    if (obj instanceof LocalTime)
		return super.format(format, TemporalUtils.toDate((LocalTime) obj), locale);
	    if (obj instanceof Instant)
		return super.format(format, TemporalUtils.toDate((Instant) obj), locale);
	}
	return super.format(format, obj, locale);
    }

    @Override
    public String format(String format, Object obj) {
	if (obj != null) {
	    if (obj instanceof LocalDateTime)
		return super.format(format, TemporalUtils.toDate((LocalDateTime) obj));
	    if (obj instanceof LocalDate)
		return super.format(format, TemporalUtils.toDate((LocalDate) obj));
	    if (obj instanceof LocalTime)
		return super.format(format, TemporalUtils.toDate((LocalTime) obj));
	    if (obj instanceof Instant)
		return super.format(format, TemporalUtils.toDate((Instant) obj));
	}
	return super.format(format, obj);
    }

    @Override
    public String format(String dateStyle, String timeStyle, Object obj, Locale locale, TimeZone timezone) {
	if (obj != null) {
	    if (obj instanceof LocalDateTime)
		return super.format(dateStyle, timeStyle, TemporalUtils.toDate((LocalDateTime) obj), locale, timezone);
	    if (obj instanceof LocalDate)
		return super.format(dateStyle, timeStyle, TemporalUtils.toDate((LocalDate) obj), locale, timezone);
	    if (obj instanceof LocalTime)
		return super.format(dateStyle, timeStyle, TemporalUtils.toDate((LocalTime) obj), locale, timezone);
	    if (obj instanceof Instant)
		return super.format(dateStyle, timeStyle, TemporalUtils.toDate((Instant) obj), locale, timezone);
	}
	return super.format(dateStyle, timeStyle, obj, locale, timezone);
    }

    @Override
    public String format(String dateStyle, String timeStyle, Object obj, Locale locale) {
	if (obj != null) {
	    if (obj instanceof LocalDateTime)
		return super.format(dateStyle, timeStyle, TemporalUtils.toDate((LocalDateTime) obj), locale);
	    if (obj instanceof LocalDate)
		return super.format(dateStyle, timeStyle, TemporalUtils.toDate((LocalDate) obj), locale);
	    if (obj instanceof LocalTime)
		return super.format(dateStyle, timeStyle, TemporalUtils.toDate((LocalTime) obj), locale);
	    if (obj instanceof Instant)
		return super.format(dateStyle, timeStyle, TemporalUtils.toDate((Instant) obj), locale);
	}
	return super.format(dateStyle, timeStyle, obj, locale);
    }

    @Override
    public String format(String dateStyle, String timeStyle, Object obj) {
	if (obj != null) {
	    if (obj instanceof LocalDateTime)
		return super.format(dateStyle, timeStyle, TemporalUtils.toDate((LocalDateTime) obj));
	    if (obj instanceof LocalDate)
		return super.format(dateStyle, timeStyle, TemporalUtils.toDate((LocalDate) obj));
	    if (obj instanceof LocalTime)
		return super.format(dateStyle, timeStyle, TemporalUtils.toDate((LocalTime) obj));
	    if (obj instanceof Instant)
		return super.format(dateStyle, timeStyle, TemporalUtils.toDate((Instant) obj));
	}
	return super.format(dateStyle, timeStyle, obj);
    }
}
