package com.chenxt.bootdemo.es.handler;

import com.chenxt.bootdemo.es.common.CdcColumn;
import com.chenxt.bootdemo.es.common.CdcMessage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.util.Map;
import java.util.Set;

/**
 * cdc模板
 *
 * @author chenxt
 * @date 2020/07/23
 */
@Slf4j
public class CdcTemplate {

    RestHighLevelClient client;

    public CdcTemplate(RestHighLevelClient client) {
        this.client = client;
    }

    public void delete(String indexName, CdcMessage message) throws Exception {
        DeleteRequest deleteRequest = new DeleteRequest(indexName, message.getId());
        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        log.debug("delete {} status {}", message, deleteResponse.status().getStatus());
    }

    public void update(String indexName, CdcMessage message, Map<String, String> fieldMapping) throws Exception {
        Set<CdcColumn> columns = message.getColumns();
        XContentBuilder updateBuilder = XContentFactory.jsonBuilder();
        XContentBuilder indexBuilder = XContentFactory.jsonBuilder();
        updateBuilder.startObject();
        indexBuilder.startObject();
        for (CdcColumn column : columns) {
            if (!fieldMapping.containsKey(column.getColumn())) {
                continue;
            }
            if (column.isUpdated()) {
                updateBuilder.field(fieldMapping.get(column.getColumn()), column.getValue());
            }
            indexBuilder.field(fieldMapping.get(column.getColumn()), column.getValue());
        }
        updateBuilder.endObject();
        indexBuilder.endObject();
        UpdateRequest request = new UpdateRequest(indexName, message.getId()).doc(updateBuilder);
        request.retryOnConflict(3);


        IndexRequest indexRequest = new IndexRequest(indexName)
                .id(message.getId())
                .source(indexBuilder);

        request.upsert(indexRequest);
        request.docAsUpsert();
        UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
        log.debug("update {} status {}", message, updateResponse.status().getStatus());
    }

    public void insert(String indexName, CdcMessage message, Map<String, String> fieldMapping) throws Exception {
        Set<CdcColumn> columns = message.getColumns();
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        for (CdcColumn column : columns) {
            if (!fieldMapping.containsKey(column.getColumn())) {
                continue;
            }
            builder.field(fieldMapping.get(column.getColumn()), column.getValue());
        }
        builder.endObject();
        IndexRequest indexRequest = new IndexRequest(indexName)
                .id(message.getId())
                .source(builder);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        log.debug("update {} status {}", message, indexResponse.status().getStatus());
    }

    /**
     * 创建索引
     */
    public void createIndex(String indexName) {
        try {
            GetIndexRequest existRequest = new GetIndexRequest(indexName);
            boolean exists = client.indices().exists(existRequest, RequestOptions.DEFAULT);
            if (!exists) {
                CreateIndexRequest indexRequest = new CreateIndexRequest(indexName);
                ClassPathResource cpr = new ClassPathResource("mapping/" + indexName + ".json");
                JsonNode rootNode = new ObjectMapper().readTree(FileCopyUtils.copyToByteArray(cpr.getInputStream()));
                indexRequest.mapping(rootNode.toString(), XContentType.JSON);
                client.indices().create(indexRequest, RequestOptions.DEFAULT);
                log.error("初始化{}索引成功！", indexName);
            }
            log.error("索引{}已存在，无需初始化！", indexName);
        } catch (Exception e) {
            log.error("初始化索引异常！", e);
        }
    }
}
