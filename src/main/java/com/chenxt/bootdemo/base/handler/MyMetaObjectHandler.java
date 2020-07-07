package com.chenxt.bootdemo.base.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MyBatis-Plus 自动插入
 *
 * @author chenxt
 * @date 2020/07/07
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.getValue("createdAt") == null) {
            this.strictInsertFill(metaObject, "createdAt", Date.class, new Date());
        }
        if (metaObject.getValue("updatedAt") == null) {
            this.strictInsertFill(metaObject, "updatedAt", Date.class, new Date());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.getValue("updatedAt") == null) {
            this.strictInsertFill(metaObject, "updatedAt", Date.class, new Date());
        }
    }

}