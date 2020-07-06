package com.chenxt.bootdemo.base.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * ip工具类
 *
 * @author chenxt
 * @date 2020/06/11
 */
@Slf4j
public class IpUtils {
    public static String getClientIp(String xForwardedFor) {
        if (StringUtils.isEmpty(xForwardedFor)) {
            log.error("xForwardedFor信息错误:" + xForwardedFor);
            return null;
        }
        String[] ipArray = xForwardedFor.split(",");
        if (ipArray.length != 0) {
            return ipArray[0].trim();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getClientIp("116.24.33.43, 116.24.33.43, 192.168.32.1"));
    }
}
