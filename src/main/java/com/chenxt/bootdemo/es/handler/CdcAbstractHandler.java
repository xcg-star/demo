package com.chenxt.bootdemo.es.handler;

import com.chenxt.bootdemo.es.common.CdcMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * cdc活动处理基类
 *
 * @author chenxt
 * @date 2020/07/23
 */
@Slf4j
public abstract class CdcAbstractHandler implements CdcHandler {

    protected final Map<String, String> fieldMapping = new HashMap<>();

    private static final char UNDERLINE = '_';


    protected void addFieldMappings(List<String> mysqlFields) {
        for (String mysqlField : mysqlFields) {
            addFieldMapping(mysqlField);
        }
    }

    protected void addFieldMapping(String mysqlField) {
        // 去除Field转驼峰的逻辑
        // addFieldMapping(mysqlField, underlineToCamel(mysqlField));
        addFieldMapping(mysqlField, mysqlField);
    }


    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    protected void addFieldMapping(String mysqlField, String esField) {
        fieldMapping.put(mysqlField, esField);
    }

    @Override
    public void action(CdcMessage message) {
        try {
            if (Objects.equals("I", message.getEventType())) {
                log.info("canal 同步新增,DB:{},Table:{},ID:{}", message.getDb(), message.getTable(), message.getId());
                insert(message);
            }
            if (Objects.equals("U", message.getEventType())) {
                log.info("canal 同步更新,DB:{},Table:{},ID:{}", message.getDb(), message.getTable(), message.getId());
                update(message);
            }
            if (Objects.equals("D", message.getEventType())) {
                log.info("canal 同步删除,DB:{},Table:{},ID:{}", message.getDb(), message.getTable(), message.getId());
                delete(message);
            }
        } catch (Exception e) {
            handlerError(message, e);
        }
    }

    protected void handlerError(CdcMessage message, Exception e) {
        log.error("handle cdc error {}", message, e);
    }

    protected void delete(CdcMessage message) throws Exception {
    }

    protected void update(CdcMessage message) throws Exception {
    }

    protected void insert(CdcMessage message) throws Exception {
    }

}
