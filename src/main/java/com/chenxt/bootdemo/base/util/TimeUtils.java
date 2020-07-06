package com.chenxt.bootdemo.base.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 *
 * @author chenxt
 * @date 2020/02/25
 */
public class TimeUtils {
    /**
     * The constant DATE_FORMAT_M.
     */
    public static SimpleDateFormat DATE_FORMAT_M = new SimpleDateFormat("MM");
    /**
     * The constant DATE_FORMAT_Y.
     */
    public static SimpleDateFormat DATE_FORMAT_Y = new SimpleDateFormat("yyyy");
    /**
     * The constant DATE_FORMAT_MD.
     */
    public static SimpleDateFormat DATE_FORMAT_MD = new SimpleDateFormat("MM-dd");
    /**
     * The constant DATE_FORMAT_YMD.
     */
    public static SimpleDateFormat DATE_FORMAT_YMD = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * The constant DATE_FORMAT_YMD_WITHOUT_SEPARATOR.
     */
    public static SimpleDateFormat DATE_FORMAT_YMD_WITHOUT_SEPARATOR = new SimpleDateFormat("yyyyMMdd");
    /**
     * The constant DATE_FORMAT_YMD_.
     */
    public static SimpleDateFormat DATE_FORMAT_YMD_ = new SimpleDateFormat("yyyy_MM_dd");
    /**
     * The constant DATE_FORMAT_YMDHM.
     */
    public static SimpleDateFormat DATE_FORMAT_YMDHM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    /**
     * The constant DATE_FORMAT_YMDHMS.
     */
    public static SimpleDateFormat DATE_FORMAT_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 将LocalDateTime转化成Date
     *
     * @param localDateTime localDateTime
     * @return date date
     */
    public static Date convertLocalDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 将Date转换成LocalDateTime
     *
     * @param date date
     * @return localDateTime local date time
     */
    public static LocalDateTime covertDate2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 获取当前时间的0点时分秒
     *
     * @return long default day time in millis
     */
    public static long getDefaultDayTimeInMillis() {
        return getCalendar(null, null).getTimeInMillis();
    }

    /**
     * 获取某一天的0点时分秒
     *
     * @param datetime the datetime
     * @param i        天数 [负数为过去的天数，正数为未来的天数]
     * @return long day time in millis
     */
    public static long getDayTimeInMillis(Object datetime, Integer i) {
        return getCalendar(datetime, i).getTimeInMillis();
    }

    /**
     * 获取当前时间的0点时分
     *
     * @return Date default day date
     */
    public static Date getDefaultDayDate() {
        return getCalendar(null, null).getTime();
    }

    /**
     * 获取某一天的0点时分
     *
     * @param datetime the datetime
     * @param i        天数 [负数为过去的天数，正数为未来的天数]
     * @return Date day date
     */
    public static Date getDayDate(Object datetime, Integer i) {
        return getCalendar(datetime, i).getTime();
    }

    /**
     * 获取当前时间的0点时分秒
     *
     * @return Calendar default calendar
     */
    public static Calendar getDefaultCalendar() {
        return getCalendar(null, null);
    }

    /**
     * 获取某一天的0点时分秒
     *
     * @param datetime the datetime
     * @param i        天数 [负数为过去的天数，正数为未来的天数]
     * @return Calendar calendar
     */
    public static Calendar getCalendar(Object datetime, Integer i) {
        Calendar calendar = Calendar.getInstance();
        if (datetime != null) {
            if (datetime instanceof Long) {
                calendar.setTimeInMillis((Long) datetime);
            } else if (datetime instanceof Date) {
                calendar.setTime((Date) datetime);
            }
        }
        if (i != null) {
            calendar.add(Calendar.DATE, i);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }


    /**
     * 获取一段时间范围内的日期集合
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return between date
     */
    public static List<String> getBetweenDate(Date startDate, Date endDate) {
        List<String> list = new ArrayList<String>();
        try {
            Calendar calendar = Calendar.getInstance();
            while (startDate.getTime() <= endDate.getTime()) {
                list.add(DATE_FORMAT_YMD.format(startDate));
                calendar.setTime(startDate);
                calendar.add(Calendar.DATE, 1);
                startDate = calendar.getTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取给定日期所在月的起始时间
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTime();
    }
}
