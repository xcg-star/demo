package com.chenxt.bootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenxt.bootdemo.entity.Blacklist;
import org.apache.ibatis.annotations.Mapper;

/**
 * 拉黑 Mapper 接口
 *
 * @author chenxt
 * @date 2020-07-18
 */
@Mapper
public interface BlacklistMapper extends BaseMapper<Blacklist> {

}
