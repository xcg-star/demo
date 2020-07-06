package com.chenxt.bootdemo.base.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * The type Timeline dto.
 *
 * @param <T> the type parameter
 * @author chenxt
 * @date 2019/12/25
 */
@Data
public class TimelineDTO<T> implements Serializable {

    private static final long serialVersionUID = -9165406946206202991L;

    private Long timeline;
    private Integer hasNext;
    private T list;

    /**
     * Instantiates a new Timeline dto.
     */
    public TimelineDTO() {
    }

    /**
     * Instantiates a new Timeline dto.
     *
     * @param timeline the timeline
     * @param list     the list
     */
    public TimelineDTO(Long timeline, T list) {
        this.timeline = timeline;
        this.list = list;
    }

    /**
     * Instantiates a new Timeline dto.
     *
     * @param timeline the timeline
     * @param hasNext  the has next
     * @param list     the list
     */
    public TimelineDTO(Long timeline, Integer hasNext, T list) {
        this.timeline = timeline;
        this.hasNext = hasNext;
        this.list = list;
    }
}
