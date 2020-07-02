package com.ning.website.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期格式化
 */
public class DateFormatUtil {
    public static final String NORM_DATE_PATTERN = "yyyy-MM-dd";
    public static final String NORM_TIME_PATTERN = "HH:mm:ss";
    public static final String NORM_DATETIME_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String NORM_DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String PURE_DATE_PATTERN = "yyyyMMdd";
    public static final String PURE_TIME_PATTERN = "HHmmss";
    public static final String PURE_DATETIME_PATTERN = "yyyyMMddHHmmss";
    public static final String PURE_DATETIME_MS_PATTERN = "yyyyMMddHHmmssSSS";
    public static final String CHINA_DATE_PATTERN = "yyyy年MM月dd日";
    public static final String PATTERN_ISO = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";
    public static final String PATTERN_ISO_ON_SECOND = "yyyy-MM-dd'T'HH:mm:ssZZ";
    public static final String PATTERN_ISO_ON_DATE = "yyyy-MM-dd";
    public static final String PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String PATTERN_DEFAULT_ON_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final FastDateFormat ISO_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
    public static final FastDateFormat ISO_ON_SECOND_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ssZZ");
    public static final FastDateFormat ISO_ON_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
    public static final FastDateFormat DEFAULT_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");
    public static final FastDateFormat DEFAULT_ON_SECOND_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    public DateFormatUtil() {
    }

    public static Date pareDate(String pattern, String dateString) throws ParseException {
        return FastDateFormat.getInstance(pattern).parse(dateString);
    }

    public static String formatDate(String pattern, Date date) {
        return FastDateFormat.getInstance(pattern).format(date);
    }

    public static String formatDate(String pattern, long date) {
        return FastDateFormat.getInstance(pattern).format(date);
    }

    public static String formatDuration(Date startDate, Date endDate) {
        return DurationFormatUtils.formatDurationHMS(endDate.getTime() - startDate.getTime());
    }

    public static String formatDuration(long durationMillis) {
        return DurationFormatUtils.formatDurationHMS(durationMillis);
    }

    public static String formatDurationOnSecond(Date startDate, Date endDate) {
        return DurationFormatUtils.formatDuration(endDate.getTime() - startDate.getTime(), "HH:mm:ss");
    }

    public static String formatDurationOnSecond(long durationMillis) {
        return DurationFormatUtils.formatDuration(durationMillis, "HH:mm:ss");
    }

    public static String formatFriendlyTimeSpanByNow(Date date) {
        return formatFriendlyTimeSpanByNow(date.getTime());
    }

    public static String formatFriendlyTimeSpanByNow(long timeStampMillis) {
        long now = System.currentTimeMillis();
        long span = now - timeStampMillis;
        if (span < 0L) {
            return String.format("%tc", timeStampMillis);
        } else if (span < 1000L) {
            return "刚刚";
        } else if (span < 60000L) {
            return String.format("%d秒前", span / 1000L);
        } else if (span < 3600000L) {
            return String.format("%d分钟前", span / 60000L);
        } else {
            long wee = DateUtil.beginOfDate(new Date(now)).getTime();
            if (timeStampMillis >= wee) {
                return String.format("今天%tR", timeStampMillis);
            } else {
                return timeStampMillis >= wee - 86400000L ? String.format("昨天%tR", timeStampMillis) : String.format("%tF", timeStampMillis);
            }
        }
    }

    public static Date parseDate(String source, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {
            return sdf.parse(source);
        } catch (ParseException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static String formatDate(Date date, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {
            return sdf.format(date);
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static String DateToCN(Date date) {
        if (null == date) {
            return null;
        } else {
            String[] CN = new String[]{"〇", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            StringBuffer cn = new StringBuffer();
            String year = String.valueOf(calendar.get(1));

            int mon;
            for (mon = 0; mon < year.length(); ++mon) {
                cn.append(CN[year.charAt(mon) - 48]);
            }

            cn.append("年");
            mon = calendar.get(2) + 1;
            if (mon < 10) {
                cn.append(CN[mon]);
            } else if (mon < 20) {
                if (mon == 10) {
                    cn.append("十");
                } else {
                    cn.append("十").append(CN[mon % 10]);
                }
            }

            cn.append("月");
            int day = calendar.get(5);
            if (day < 10) {
                cn.append(CN[day]);
            } else if (day < 20) {
                if (day == 10) {
                    cn.append("十");
                } else {
                    cn.append("十").append(CN[day % 10]);
                }
            } else if (day < 30) {
                if (day == 20) {
                    cn.append("二十");
                } else {
                    cn.append("二十").append(CN[day % 10]);
                }
            } else {
                cn.append("三十").append(CN[day % 10]);
            }

            cn.append("日");
            return cn.toString();
        }
    }
}
