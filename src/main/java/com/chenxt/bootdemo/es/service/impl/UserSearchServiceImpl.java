package com.chenxt.bootdemo.es.service.impl;

import com.chenxt.bootdemo.es.dto.UserSearchDTO;
import com.chenxt.bootdemo.es.service.IUserSearchService;
import com.chenxt.bootdemo.es.vo.UserSearchVO;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户搜索service实现
 *
 * @author chenxt
 * @date 2020/07/23
 */
@Service
public class UserSearchServiceImpl implements IUserSearchService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Value("${canal.index.user}")
    private String indexName;

    @Override
    public List<UserSearchVO> search(UserSearchDTO userSearchDTO) {
        SearchRequest searchRequest = new SearchRequest(indexName);
        // 设置查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        // 英文必须有相等
        // 中文进行分词
        boolQueryBuilder.should(QueryBuilders.matchPhraseQuery("nick_name", userSearchDTO.getNickName()));

        // 搜索词高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("nick_name");
        highlightBuilder.preTags("<h>");
        highlightBuilder.postTags("</h>");
        searchSourceBuilder.highlighter(highlightBuilder);

        // 暂时使用浅分页
        searchSourceBuilder.query(boolQueryBuilder).from((userSearchDTO.getPage() - 1) * userSearchDTO.getSize()).size(userSearchDTO.getSize());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hitsArr = hits.getHits();

        if (hitsArr == null || hitsArr.length == 0) {
            return new ArrayList<>();
        }
        // 处理返回的数据
        return Arrays.stream(hitsArr).filter(searchHit -> !CollectionUtils.isEmpty(searchHit.getHighlightFields())).map(searchHit -> {
            UserSearchVO userSearchVO = new UserSearchVO();
            //获取对应的高亮域
            Map<String, HighlightField> result = searchHit.getHighlightFields();
            //从设定的高亮域中取得指定域
            HighlightField titleField = result.get("nick_name");
            //取得定义的高亮标签
            Text[] titleTexts = titleField.fragments();
            //为title串值增加自定义的高亮标签
            StringBuilder highlightContent = new StringBuilder();
            for (Text text : titleTexts) {
                highlightContent.append(text);
            }
            userSearchVO.setId(Long.valueOf(searchHit.getId()));
            userSearchVO.setNickName(highlightContent.toString());
            // 新增头像，个性签名，粉丝数
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            userSearchVO.setAvatar(sourceAsMap.getOrDefault("avatar", "").toString());
            userSearchVO.setBio(sourceAsMap.getOrDefault("bio", "").toString());
            return userSearchVO;
        }).collect(Collectors.toList());
    }
}
