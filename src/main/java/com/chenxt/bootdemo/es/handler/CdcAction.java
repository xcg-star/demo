package com.chenxt.bootdemo.es.handler;

import com.chenxt.bootdemo.es.common.CdcMessage;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * cdc action
 *
 * @author chenxt
 * @date 2020/07/24
 */
@Slf4j
@Component
public class CdcAction {

    private final ApplicationContext applicationContext;
    /**
     * 扫描该包下的 handler 类
     */
    @Value("${canal.handler.basePackages:com.chenxt.bootdemo.es.handler}")
    String basePackages;

    public CdcAction(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    Map<String, CdcHandler> handlers = new HashMap<>();

    /**
     * 扫描包下的所有类，初始化 handlers 类集合
     */
    @PostConstruct
    public void init() {
        Reflections reflections = new Reflections(basePackages);
        Set<Class<? extends CdcHandler>> types = reflections.getSubTypesOf(CdcHandler.class);
        for (Class<? extends CdcHandler> type : types) {
            Map<String, ? extends CdcHandler> beansOfType = applicationContext.getBeansOfType(type);
            beansOfType.forEach((key, value) -> {
                handlers.put(value.tableName(), value);
            });

        }
    }

    /**
     * 执行指定类下的 handle action
     *
     * @param message
     */
    public void action(CdcMessage message) {
        CdcHandler handler = handlers.get(message.getTable());
        if (handler == null) {
            return;
        }
        handler.action(message);
    }
}
