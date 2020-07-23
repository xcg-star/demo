package com.chenxt.bootdemo.es.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * cdc 消息
 *
 * @author chenxt
 * @date 2020/07/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CdcMessage implements Serializable {
    String db;
    String table;
    String id;
    /**
     * I:insert,U:update,D:delete
     */
    String eventType;
    Set<CdcColumn> columns = new HashSet<>();
}
