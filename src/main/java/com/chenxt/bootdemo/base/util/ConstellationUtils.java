package com.chenxt.bootdemo.base.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 获取星座工具类
 *
 * @author chenxt
 * @date 2020/03/12
 */
public class ConstellationUtils {

    /**
     * Gets constellation.
     *
     * @param date the date
     * @return the constellation
     */
    public static String getConstellation(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int m = calendar.get(Calendar.MONTH) + 1;
        int d = calendar.get(Calendar.DATE);
        int beginIndex = m * 2 - (d < (Integer.parseInt("102223444433".substring(m - 1, m)) + 19) ? 1 : 0) * 2;
        return "魔羯水瓶双鱼白羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯".substring(beginIndex, beginIndex + 2) + "座";
    }
}
