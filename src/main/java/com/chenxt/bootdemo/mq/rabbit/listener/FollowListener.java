package com.chenxt.bootdemo.mq.rabbit.listener;

import com.chenxt.bootdemo.base.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * RabbitMq 关注监听器
 *
 * @author chenxt
 * @date 2020/07/19
 */
@Slf4j
@Component
public class FollowListener {
    @RabbitListener(queues = RabbitMqConfig.FOLLOW_FOLLOW_QUEUE)
    public void processFollowFollow(String message) {
        log.info("开始消费关注消息 : {}", message);
    }
}
