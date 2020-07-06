package com.chenxt.bootdemo.base.util;

/**
 * 时间常量类
 *
 * @author chenxt
 * @date 2020/01/01
 */
public interface ITimeConstant {

    /**
     * The constant ONE_MILLISECONDS.
     */
    long ONE_MILLISECONDS = 1;
    /**
     * The constant ONE_SECONDS_MILLISECONDS.
     */
    long ONE_SECONDS_MILLISECONDS = 1000 * ONE_MILLISECONDS;
    /**
     * The constant TEN_SECONDS.
     */
    long TEN_SECONDS = 10;
    /**
     * The constant TEN_SECONDS_MILLISECONDS.
     */
    long TEN_SECONDS_MILLISECONDS = TEN_SECONDS * ONE_SECONDS_MILLISECONDS;
    /**
     * The constant ONE_MINUTE_SECONDS.
     */
    long ONE_MINUTE_SECONDS = 60;
    /**
     * The constant ONE_MINUTE_MILLISECONDS.
     */
    long ONE_MINUTE_MILLISECONDS = 1000 * ONE_MINUTE_SECONDS;
    ;

    /**
     * The constant FIVE_MINUTE_SECONDS.
     */
    long FIVE_MINUTE_SECONDS = 5 * ONE_MINUTE_SECONDS;
    /**
     * The constant FIVE_MINUTE_MILLISECONDS.
     */
    long FIVE_MINUTE_MILLISECONDS = 5 * ONE_MINUTE_MILLISECONDS;
    /**
     * The constant FIFTEEN_SECONDS_MILLISECONDS.
     */
    long FIFTEEN_SECONDS_MILLISECONDS = 15 * ONE_SECONDS_MILLISECONDS;
    /**
     * The constant FIFTEEN_MINUTE_MILLISECONDS.
     */
    long FIFTEEN_MINUTE_MILLISECONDS = 15 * ONE_MINUTE_MILLISECONDS;
    /**
     * The constant FIFTEEN_MINUTE_SECONDS.
     */
    long FIFTEEN_MINUTE_SECONDS = 15 * ONE_MINUTE_SECONDS;
    /**
     * The constant TEN_MINUTE_SECONDS.
     */
    long TEN_MINUTE_SECONDS = 10 * ONE_MINUTE_SECONDS;
    /**
     * The constant TEN_MINUTE_MILLISECONDS.
     */
    long TEN_MINUTE_MILLISECONDS = 1000 * TEN_MINUTE_SECONDS;

    /**
     * The constant THIRTY_MINUTE_SECONDS.
     */
    long THIRTY_MINUTE_SECONDS = 30 * ONE_MINUTE_SECONDS;
    /**
     * The constant THIRTY_MINUTE_MILLISECONDS.
     */
    long THIRTY_MINUTE_MILLISECONDS = 3000 * TEN_MINUTE_SECONDS;

    /**
     * The constant ONE_HOUR_SECONDS.
     */
    long ONE_HOUR_SECONDS = 60 * ONE_MINUTE_SECONDS;
    /**
     * The constant ONE_HOUR_MILLISECONDS.
     */
    long ONE_HOUR_MILLISECONDS = 1000 * ONE_HOUR_SECONDS;

    /**
     * The constant THREE_HOUR_SECONDS.
     */
    long THREE_HOUR_SECONDS = ONE_HOUR_SECONDS * 3;
    /**
     * The constant THREE_SECONDS_MILLISECONDS.
     */
    long THREE_SECONDS_MILLISECONDS = 3 * ONE_SECONDS_MILLISECONDS;
    /**
     * The constant THREE_HOUR_MILLISECONDS.
     */
    long THREE_HOUR_MILLISECONDS = ONE_HOUR_MILLISECONDS * 3;

    /**
     * The constant ONE_DAY_SECONDS.
     */
    long ONE_DAY_SECONDS = 24 * 3600;
    /**
     * The constant ONE_DAY_MILLISECONDS.
     */
    long ONE_DAY_MILLISECONDS = ONE_DAY_SECONDS * 1000;

    /**
     * The constant TWO_DAY_SECONDS.
     */
    long TWO_DAY_SECONDS = ONE_DAY_SECONDS * 2;
    /**
     * The constant TWO_DAY_MILLISECONDS.
     */
    long TWO_DAY_MILLISECONDS = ONE_DAY_MILLISECONDS * 2;

    /**
     * The constant THREE_DAY_SECONDS.
     */
    long THREE_DAY_SECONDS = ONE_DAY_SECONDS * 3;
    /**
     * The constant THREE_DAY_MILLISECONDS.
     */
    long THREE_DAY_MILLISECONDS = ONE_DAY_MILLISECONDS * 3;

    /**
     * The constant THIRTY_DAY_SECONDS.
     */
    long THIRTY_DAY_SECONDS = ONE_DAY_SECONDS * 30;
    /**
     * The constant THIRTY_DAY_MILLISECONDS.
     */
    long THIRTY_DAY_MILLISECONDS = ONE_DAY_MILLISECONDS * 30;

    /**
     * The constant ONE_YEAR_MILLISECONDS.
     */
    long ONE_YEAR_MILLISECONDS = ONE_DAY_MILLISECONDS * 365;

}
