package com.chenxt.bootdemo.base.util;


import com.chenxt.bootdemo.base.dto.TimelineDTO;
import com.chenxt.bootdemo.base.vo.TimelineVO;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * 时间工具类
 *
 * @author chenxt
 * @date 2019/12/27
 */
public class TimelineUtils {

    /**
     * Render timeline timeline dto.
     *
     * @param <T>      the type parameter
     * @param timeline the timeline
     * @param list     the list
     * @param consumer the consumer
     * @return the timeline dto
     */
    public static <T> TimelineDTO<List<T>> renderTimeline(Long timeline, List<T> list, Function<T, Long> consumer) {
        if (CollectionUtils.isEmpty(list)) {
            return new TimelineDTO<>(timeline, 0, Collections.emptyList());
        }
        T t = list.get(list.size() - 1);
        timeline = consumer.apply(t);
        return new TimelineDTO<>(timeline, 1, list);
    }

    /**
     * Render timeline vo timeline vo.
     *
     * @param <T>      the type parameter
     * @param timeline the timeline
     * @param list     the list
     * @param consumer the consumer
     * @return the timeline vo
     */
    public static <T> TimelineVO<List<T>> renderTimelineVO(Long timeline, List<T> list, Function<T, Long> consumer) {
        if (CollectionUtils.isEmpty(list)) {
            return new TimelineVO<>(timeline, 0, Collections.emptyList());
        }
        T t = list.get(list.size() - 1);
        timeline = consumer.apply(t);
        return new TimelineVO<>(timeline, 1, list);
    }

    public static <T> TimelineVO<List<T>> renderFullTimelineVO(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new TimelineVO<>(-1L, 0, Collections.emptyList());
        }
        return new TimelineVO<>(-1L, 0, list);
    }
}
