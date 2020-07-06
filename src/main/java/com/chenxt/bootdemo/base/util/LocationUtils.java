package com.chenxt.bootdemo.base.util;

import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;

/**
 * 地理位置工具类
 *
 * @author chenxt
 * @date 2020/06/11
 */
@Slf4j
public class LocationUtils {

    /**
     * ip转地理位置
     *
     * @param ip ip
     * @return 地理位置(代码1 | 国家 | 大区 | 省份 | 城市 | 运营商 | 代码2)
     */
    public static String ip2Address(String ip) {
        try {
            //根据ip进行位置信息搜索
            DbConfig config = new DbConfig();
            //获取ip库的位置（放在resources下）
            InputStream fis = LocationUtils.class.getResourceAsStream("/ip2region/ip2region.db");
            DbSearcher searcher = new DbSearcher(config, FileCopyUtils.copyToByteArray(fis));
            //采用Btree搜索
            DataBlock block = searcher.memorySearch(ip);
            //位置信息（格式：国家|大区|省份|城市|运营商）
            return format(block.getRegion());
        } catch (Exception e) {
            log.error("ip转换成地理位置错误:" + ip);
            return null;
        }
    }

    private static String format(String region) {
        try {
            String[] regionArray = region.split("\\|");
            return regionArray[0] + "|" + regionArray[2] + "|" + regionArray[3];
        } catch (Exception e) {
            log.error("地理位置信息错误:" + region);
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(ip2Address("116.24.33.43"));
    }
}
