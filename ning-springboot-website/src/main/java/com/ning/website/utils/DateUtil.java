package com.ning.website.utils;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    public static final long MILLIS_PER_SECOND = 1000L;
    public static final long MILLIS_PER_MINUTE = 60000L;
    public static final long MILLIS_PER_HOUR = 3600000L;
    public static final long MILLIS_PER_DAY = 86400000L;
    private static final int[] MONTH_LENGTH = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public DateUtil() {
    }

    public static boolean isSameDay(Date date1, Date date2) {
        return DateUtils.isSameDay(date1, date2);
    }

    public static boolean isSameTime(Date date1, Date date2) {
        return date1.compareTo(date2) == 0;
    }

    public static boolean isBetween(Date date, Date start, Date end) {
        if (date != null && start != null && end != null && !start.after(end)) {
            return !date.before(start) && !date.after(end);
        } else {
            throw new IllegalArgumentException("some date parameters is null or dateBein after dateEnd");
        }
    }

    public static Date addMonths(Date date, int amount) {
        return DateUtils.addMonths(date, amount);
    }

    public static Date subMonths(Date date, int amount) {
        return DateUtils.addMonths(date, -amount);
    }

    public static Date addWeeks(Date date, int amount) {
        return DateUtils.addWeeks(date, amount);
    }

    public static Date subWeeks(Date date, int amount) {
        return DateUtils.addWeeks(date, -amount);
    }

    public static Date addDays(Date date, int amount) {
        return DateUtils.addDays(date, amount);
    }

    public static Date subDays(Date date, int amount) {
        return DateUtils.addDays(date, -amount);
    }

    public static Date addHours(Date date, int amount) {
        return DateUtils.addHours(date, amount);
    }

    public static Date subHours(Date date, int amount) {
        return DateUtils.addHours(date, -amount);
    }

    public static Date addMinutes(Date date, int amount) {
        return DateUtils.addMinutes(date, amount);
    }

    public static Date subMinutes(Date date, int amount) {
        return DateUtils.addMinutes(date, -amount);
    }

    public static Date addSeconds(Date date, int amount) {
        return DateUtils.addSeconds(date, amount);
    }

    public static Date subSeconds(Date date, int amount) {
        return DateUtils.addSeconds(date, -amount);
    }

    public static Date setYears(Date date, int amount) {
        return DateUtils.setYears(date, amount);
    }

    public static Date setMonths(Date date, int amount) {
        return DateUtils.setMonths(date, amount);
    }

    public static Date setDays(Date date, int amount) {
        return DateUtils.setDays(date, amount);
    }

    public static Date setHours(Date date, int amount) {
        return DateUtils.setHours(date, amount);
    }

    public static Date setMinutes(Date date, int amount) {
        return DateUtils.setMinutes(date, amount);
    }

    public static Date setSeconds(Date date, int amount) {
        return DateUtils.setSeconds(date, amount);
    }

    public static Date setMilliseconds(Date date, int amount) {
        return DateUtils.setMilliseconds(date, amount);
    }

    public static int getDayOfWeek(Date date) {
        int result = get(date, 7);
        return result == 1 ? 7 : result - 1;
    }

    public static int getYear(Date date) {
        return get(date, 1);
    }

    public static int getMoth(Date date) {
        return get(date, 2) + 1;
    }

    public static int getDayOfYear(Date date) {
        return get(date, 6);
    }

    public static int getDayOfMonth(Date date) {
        return get(date, 5);
    }

    public static int getWeekOfMonth(Date date) {
        return getWithMondayFirst(date, 4);
    }

    public static int getWeekOfYear(Date date) {
        return getWithMondayFirst(date, 3);
    }

    private static int get(Date date, int field) {
        Validate.notNull(date, "The date must not be null", new Object[0]);
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(2);
        cal.setTime(date);
        return cal.get(field);
    }

    private static int getWithMondayFirst(Date date, int field) {
        Validate.notNull(date, "The date must not be null", new Object[0]);
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(2);
        cal.setTime(date);
        return cal.get(field);
    }

    public static Date beginOfYear(Date date) {
        return DateUtils.truncate(date, 1);
    }

    public static Date endOfYear(Date date) {
        return new Date(nextYear(date).getTime() - 1L);
    }

    public static Date nextYear(Date date) {
        return DateUtils.ceiling(date, 1);
    }

    public static Date beginOfMonth(Date date) {
        return DateUtils.truncate(date, 2);
    }

    public static Date endOfMonth(Date date) {
        return new Date(nextMonth(date).getTime() - 1L);
    }

    public static Date nextMonth(Date date) {
        return DateUtils.ceiling(date, 2);
    }

    public static Date beginOfWeek(Date date) {
        return DateUtils.truncate(subDays(date, getDayOfWeek(date) - 1), 5);
    }

    public static Date endOfWeek(Date date) {
        return new Date(nextWeek(date).getTime() - 1L);
    }

    public static Date nextWeek(Date date) {
        return DateUtils.truncate(addDays(date, 8 - getDayOfWeek(date)), 5);
    }

    public static Date beginOfDate(Date date) {
        return DateUtils.truncate(date, 5);
    }

    public static Date endOfDate(Date date) {
        return new Date(nextDate(date).getTime() - 1L);
    }

    public static Date nextDate(Date date) {
        return DateUtils.ceiling(date, 5);
    }

    public static Date beginOfHour(Date date) {
        return DateUtils.truncate(date, 11);
    }

    public static Date endOfHour(Date date) {
        return new Date(nextHour(date).getTime() - 1L);
    }

    public static Date nextHour(Date date) {
        return DateUtils.ceiling(date, 11);
    }

    public static Date beginOfMinute(Date date) {
        return DateUtils.truncate(date, 12);
    }

    public static Date endOfMinute(Date date) {
        return new Date(nextMinute(date).getTime() - 1L);
    }

    public static Date nextMinute(Date date) {
        return DateUtils.ceiling(date, 12);
    }

    public static boolean isLeapYear(Date date) {
        return isLeapYear(get(date, 1));
    }

    public static boolean isLeapYear(int y) {
        boolean result = false;
        if (y % 4 == 0 && (y < 1582 || y % 100 != 0 || y % 400 == 0)) {
            result = true;
        }

        return result;
    }

    public static int getMonthLength(Date date) {
        int year = get(date, 1);
        int month = get(date, 2);
        return getMonthLength(year, month);
    }

    public static int getMonthLength(int year, int month) {
        if (month >= 1 && month <= 12) {
            if (month == 2) {
                return isLeapYear(year) ? 29 : 28;
            } else {
                return MONTH_LENGTH[month];
            }
        } else {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
    }

    public static int daysBetween(Date smdate, Date bdate) {
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTime(smdate);
        caled.setTime(bdate);
        calst.set(11, 0);
        calst.set(12, 0);
        calst.set(13, 0);
        caled.set(11, 0);
        caled.set(12, 0);
        caled.set(13, 0);
        int days = ((int) (caled.getTime().getTime() / 1000L) - (int) (calst.getTime().getTime() / 1000L)) / 3600 / 24;
        return days;
    }

    public static Date getTodayStartTime() {
        Calendar c = new GregorianCalendar();
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        c.set(14, 0);
        return c.getTime();
    }

    public static Date getTodayEndTime() {
        Calendar c = new GregorianCalendar();
        c.set(11, 23);
        c.set(12, 59);
        c.set(13, 59);
        c.set(14, 999);
        return c.getTime();
    }

    public static Date getDateTimeOfStart(Date date) {
        Calendar c = DateUtils.toCalendar(date);
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        c.set(14, 0);
        return c.getTime();
    }

    public static Date getDateTimeOfEnd(Date date) {
        Calendar c = DateUtils.toCalendar(date);
        c.set(11, 23);
        c.set(12, 59);
        c.set(13, 59);
        c.set(14, 999);
        return c.getTime();
    }
}
