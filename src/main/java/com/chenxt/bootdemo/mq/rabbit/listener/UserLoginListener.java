package com.chenxt.bootdemo.mq.rabbit.listener;

import com.chenxt.bootdemo.base.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * RabbitMq 用户监听器
 *
 * @author chenxt
 * @date 2020/07/17
 */
@Slf4j
@Component
public class UserLoginListener {
    @RabbitListener(queues = RabbitMqConfig.USER_LOGIN_QUEUE)
    public void processUserLogin(String message) {
        log.info("开始消费用户登陆消息 : {}", message);
    }
}
