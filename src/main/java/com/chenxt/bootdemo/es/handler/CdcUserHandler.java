package com.chenxt.bootdemo.es.handler;

import com.chenxt.bootdemo.es.common.CdcMessage;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;

/**
 * cdc用户处理类
 *
 * @author chenxt
 * @date 2020/07/23
 */
@Slf4j
public class CdcUserHandler extends CdcAbstractHandler {

    @Value("${canal.index.user}")
    String indexName;

    CdcTemplate template;

    @PostConstruct
    public void init() {
        template = new CdcTemplate(client);
        // 此处对应数据库字段名
        addFieldMappings(Arrays.asList("id", "nick_name", "nick_name_pinyin", "avatar", "bio"));
        template.createIndex(indexName);
    }

    @Resource
    private RestHighLevelClient client;

    @Override
    public String tableName() {
        return "user";
    }

    @Override
    protected void delete(CdcMessage message) throws Exception {
        template.delete(indexName, message);
    }

    @Override
    protected void update(CdcMessage message) throws Exception {
        template.update(indexName, message, fieldMapping);
    }

    @Override
    protected void insert(CdcMessage message) throws Exception {
        template.insert(indexName, message, fieldMapping);
    }
}
