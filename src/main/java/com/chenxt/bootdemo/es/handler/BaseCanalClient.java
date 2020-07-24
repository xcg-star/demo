package com.chenxt.bootdemo.es.handler;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.chenxt.bootdemo.es.common.CdcColumn;
import com.chenxt.bootdemo.es.common.CdcMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * base canal client
 *
 * @author chenxt
 * @date 2020/07/23
 */
@Slf4j
public abstract class BaseCanalClient {

    protected volatile boolean running = false;
    protected Thread.UncaughtExceptionHandler handler = (t, e) -> log.error("parse events has an error", e);
    protected Thread thread = null;
    protected CanalConnector connector;
    protected String destination;

    public void setDestination(String destination) {
        this.destination = destination;
    }


    protected void consumeEntry(List<Entry> entrys) {
        for (Entry entry : entrys) {
            if (entry.getEntryType() == EntryType.ROWDATA) {
                RowChange rowChage;
                try {
                    rowChage = RowChange.parseFrom(entry.getStoreValue());
                } catch (Exception e) {
                    throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                }

                EventType eventType = rowChage.getEventType();
                String schemaName = entry.getHeader().getSchemaName();
                String tableName = entry.getHeader().getTableName();
                for (RowData rowData : rowChage.getRowDatasList()) {
                    if (eventType == EventType.DELETE) {
                        consumeColumn(schemaName, tableName, eventType, rowData.getBeforeColumnsList());
                    } else if (eventType == EventType.INSERT) {
                        consumeColumn(schemaName, tableName, eventType, rowData.getAfterColumnsList());
                    } else {
                        consumeColumn(schemaName, tableName, eventType, rowData.getAfterColumnsList());
                    }
                }
            }
        }
    }

    protected void consumeColumn(String schemaName, String tableName, EventType eventType, List<Column> columns) {
        // 解析 canal 通知
        CdcMessage message = new CdcMessage();
        message.setTable(tableName);
        message.setDb(schemaName);
        message.setEventType(toShortEventType(eventType));
        for (Column column : columns) {
            String value;
            if (StringUtils.containsIgnoreCase(column.getMysqlType(), "BLOB")
                    || StringUtils.containsIgnoreCase(column.getMysqlType(), "BINARY")) {
                // get value bytes
                value = new String(column.getValue().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            } else {
                value = column.getValue();
            }
            if (value == null) {
                continue;
            }
            CdcColumn cdcColumn = CdcColumn.builder()
                    .column(column.getName())
                    .dataType(column.getMysqlType())
                    .updated(column.getUpdated())
                    .value(translateValue(value, column.getMysqlType()))
                    .build();
            message.getColumns().add(cdcColumn);
            if (column.hasIsKey() && Objects.equals(column.getName(), "id")) {
                message.setId(column.getValue());
            }
            if (Objects.equals("is_deleted", column.getName()) && Objects.equals("1", column.getValue())) {
                message.setEventType("D");
            }
        }
        post(message);
    }

    public abstract void post(CdcMessage message);

    public Object translateValue(String value, String mysqlType) {
        if (value == null) {
            return null;
        }
        try {
            if (mysqlType.contains("int") || mysqlType.contains("bit")) {
                if (StringUtils.isEmpty(value) && !NumberUtils.isDigits(value)) {
                    return null;
                }
                return Long.parseLong(value);
            }
            if (mysqlType.startsWith("timestamp") || mysqlType.startsWith("date")) {
                if (StringUtils.isEmpty(value)) {
                    return null;
                }
                if (value.length() == "yyyy-MM-dd".length()) {
                    return DateUtils.parseDate(value, "yyyy-MM-dd").getTime();
                } else {
                    return DateUtils.parseDate(value, "yyyy-MM-dd HH:mm:ss").getTime();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return value;
        }
        return value;
    }

    private String toShortEventType(EventType eventType) {
        if (Objects.equals(eventType, EventType.INSERT)) {
            return "I";
        } else if (Objects.equals(eventType, EventType.UPDATE)) {
            return "U";
        } else if (Objects.equals(eventType, EventType.DELETE)) {
            return "D";
        }
        return "";
    }

    public void setConnector(CanalConnector connector) {
        this.connector = connector;
    }

}
