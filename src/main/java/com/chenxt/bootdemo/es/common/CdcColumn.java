package com.chenxt.bootdemo.es.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * cdc 字段
 *
 * @author chenxt
 * @date 2020/07/23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CdcColumn {
    String column;
    Object value;
    String dataType;
    boolean updated;
}
