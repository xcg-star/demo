package com.chenxt.bootdemo.base.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * The type Timeline vo.
 *
 * @param <T> the type parameter
 * @author chenxt
 * @date 2019/12/25
 */
@Data
public class TimelineVO<T> implements Serializable {

    private static final long serialVersionUID = 7625616586638276096L;
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long timeline;
    private Integer hasNext;
    private T list;

    /**
     * Instantiates a new Timeline vo.
     */
    public TimelineVO() {
    }

    /**
     * Instantiates a new Timeline vo.
     *
     * @param timeline the timeline
     * @param list     the list
     */
    public TimelineVO(Long timeline, T list) {
        this.timeline = timeline;
        this.list = list;
    }

    /**
     * Instantiates a new Timeline vo.
     *
     * @param timeline the timeline
     * @param hasNext  the has next
     * @param list     the list
     */
    public TimelineVO(Long timeline, Integer hasNext, T list) {
        this.timeline = timeline;
        this.hasNext = hasNext;
        this.list = list;
    }
}
