package com.chenxt.bootdemo.base.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * RabbitMq配置
 *
 * @author chenxt
 * @date 2020/07/17
 */
@Configuration
public class RabbitMqConfig {

    public static final String USER_LOGIN_EXCHANGE = "exchange.topic.bootdemo.user.login";
    public static final String USER_LOGIN_QUEUE = "queue.bootdemo.user.login";
    public static final String USER_LOGIN_BINDING_KEY = "bootdemo.user.login.*";

    public static final String FOLLOW_FOLLOW_EXCHANGE = "exchange.topic.bootdemo.follow.follow";
    public static final String FOLLOW_FOLLOW_QUEUE = "queue.bootdemo.follow.follow";
    public static final String FOLLOW_FOLLOW_BINDING_KEY = "bootdemo.follow.follow.*";

    @Resource
    AmqpAdmin amqpAdmin;

    @PostConstruct
    public void createQueue() {
        TopicExchange userLoginExchange = new TopicExchange(USER_LOGIN_EXCHANGE);
        TopicExchange followFollowExchange = new TopicExchange(FOLLOW_FOLLOW_EXCHANGE);
        amqpAdmin.declareExchange(userLoginExchange);
        amqpAdmin.declareExchange(followFollowExchange);

        Queue userLoginQueue = new Queue(USER_LOGIN_QUEUE);
        Queue followFollowQueue = new Queue(FOLLOW_FOLLOW_QUEUE);
        amqpAdmin.declareQueue(userLoginQueue);
        amqpAdmin.declareQueue(followFollowQueue);

        Binding userLoginBind = BindingBuilder.bind(userLoginQueue).to(userLoginExchange)
                .with(USER_LOGIN_BINDING_KEY);
        Binding followFollowBind = BindingBuilder.bind(followFollowQueue).to(followFollowExchange)
                .with(FOLLOW_FOLLOW_BINDING_KEY);
        amqpAdmin.declareBinding(userLoginBind);
        amqpAdmin.declareBinding(followFollowBind);
    }
}
