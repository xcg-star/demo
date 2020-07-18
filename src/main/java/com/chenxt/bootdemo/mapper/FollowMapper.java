package com.chenxt.bootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenxt.bootdemo.entity.Follow;
import org.apache.ibatis.annotations.Mapper;

/**
 * 关注 Mapper 接口
 *
 * @author chenxt
 * @date 2020-07-12
 */
@Mapper
public interface FollowMapper extends BaseMapper<Follow> {

    /**
     * 新增关注，当有冲突时更新
     *
     * @param follow 关注
     */
    void insertOnDuplicateUpdate(Follow follow);
}
