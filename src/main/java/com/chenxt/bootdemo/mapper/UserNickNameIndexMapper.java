package com.chenxt.bootdemo.mapper;

import com.chenxt.bootdemo.entity.UserNickNameIndex;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户昵称索引 Mapper 接口
 *
 * @author chenxt
 * @date 2020-07-15
 */
@Mapper
public interface UserNickNameIndexMapper extends BaseMapper<UserNickNameIndex> {

    /**
     * 更新索引
     * @param nickNameIndex 索引
     */
    void updateIndex(@Param("nickNameIndex") Integer nickNameIndex);
}
