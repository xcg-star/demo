package com.chenxt.bootdemo.es.handler;

import com.chenxt.bootdemo.es.common.CdcMessage;

/**
 * cdc处理类
 *
 * @author chenxt
 * @date 2020/07/23
 */
public interface CdcHandler {

    /**
     * 表名
     *
     * @return
     */
    String tableName();

    /**
     * 变更活动
     *
     * @param message
     */
    void action(CdcMessage message);

}
