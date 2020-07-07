package com.chenxt.bootdemo.base.handler;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.chenxt.bootdemo.base.util.SnowFlakeIdUtils;
import org.springframework.stereotype.Component;

/**
 * 雪花ID生成器
 *
 * @author chenxt
 * @date 2020/07/07
 */
@Component
public class SnowFlakeIdGenerator implements IdentifierGenerator {
    @Override
    public Number nextId(Object entity) {
        return SnowFlakeIdUtils.nextId();
    }
}
