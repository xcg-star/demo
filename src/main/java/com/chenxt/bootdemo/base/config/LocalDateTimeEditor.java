package com.chenxt.bootdemo.base.config;

import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * 日期类型
 *
 * @author chenxt
 * @date 2020/09/23
 */
@Slf4j
public class LocalDateTimeEditor extends PropertyEditorSupport {
    private static List<DateTimeFormatter> dateTimeFormatters = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ISO_DATE_TIME
    );

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        LocalDateTime localDateTime = null;
        for (DateTimeFormatter dateTimeFormatter : dateTimeFormatters) {
            try {
                localDateTime = LocalDateTime.parse(text, dateTimeFormatter);
                break;
            } catch (DateTimeParseException ex) {
                log.warn(text + " 无法使用 " + dateTimeFormatter + " 转换");
            }
        }
        setValue(localDateTime);
    }
}
