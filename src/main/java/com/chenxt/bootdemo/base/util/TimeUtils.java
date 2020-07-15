package com.chenxt.bootdemo.base.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author chenxt
 * @date 2020/02/25
 */
public class TimeUtils {

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

    public static Date getDate(String text) {
        return getDate(text, DEFAULT_PATTERN);
    }

    public static Date getDate(String text, String pattern) {
        LocalDateTime localDateTime = LocalDateTime.parse(text, DateTimeFormatter.ofPattern(pattern));
        return Date.from(localDateTime.atZone(DEFAULT_ZONE_ID).toInstant());
    }

    public static LocalDateTime instant2LocalDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, DEFAULT_ZONE_ID);
    }

    public static Instant localDateTime2Instant(LocalDateTime localDateTime) {
        return localDateTime.atZone(DEFAULT_ZONE_ID).toInstant();
    }

    public static Instant getInstant(String text) {
        return getInstant(text, DEFAULT_PATTERN);
    }

    public static Instant getInstant(String text, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.parse(text, dateTimeFormatter);
        return localDateTime2Instant(localDateTime);
    }

    public static void main(String[] args) {
        Instant instant = Instant.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        System.out.println(getInstant("2020-03-15 10:12:33"));
    }
}
