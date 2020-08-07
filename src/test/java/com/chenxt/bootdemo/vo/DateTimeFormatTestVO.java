package com.chenxt.bootdemo.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 测试日期格式化VO
 *
 * @author chenxt
 * @date 2020/08/07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateTimeFormatTestVO implements Serializable {
    private static final long serialVersionUID = 5010767010368815901L;

    @ApiModelProperty(value = "date")
    private Date date = new Date();

    @ApiModelProperty(value = "nullDate")
    private Date nullDate = null;

    @ApiModelProperty(value = "localDateTime")
    private LocalDateTime localDateTime = LocalDateTime.now();

    @ApiModelProperty(value = "nullLocalDateTime")
    private LocalDateTime nullLocalDateTime = null;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "jsonFieldDate")
    private Date jsonFieldDate = new Date();

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "jsonFieldLocalDateTime")
    private LocalDateTime jsonFieldLocalDateTime = LocalDateTime.now();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "jsonFormatDate")
    private Date jsonFormatDate = new Date();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "jsonFormatLocalDateTime")
    private LocalDateTime jsonFormatLocalDateTime = LocalDateTime.now();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "jsonFormatJsonFieldLocalDateTime")
    private LocalDateTime jsonFormatJsonFieldLocalDateTime = LocalDateTime.now();

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "dateTimeFormatLocalDateTime")
    private LocalDateTime dateTimeFormatLocalDateTime = LocalDateTime.now();

}
