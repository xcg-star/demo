package com.chenxt.bootdemo.mq.rabbit.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * RabbitMq发送
 *
 * @author chenxt
 * @date 2020/07/17
 */
@Slf4j
@Component
public class RabbitMqSender {
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     *
     * @param exchange   交换机名
     * @param routingKey 路由名
     * @param message    消息
     */
    public void send(String exchange, String routingKey, String message) {
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
        } catch (Exception e) {
            log.error("发送消息失败: {}", e);
        }
    }
}
