package com.chenxt.bootdemo.mapper;

import com.chenxt.bootdemo.entity.Follow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 关注 Mapper 接口
 *
 * @author chenxt
 * @date 2020-07-12
 */
@Mapper
public interface FollowMapper extends BaseMapper<Follow> {

}
