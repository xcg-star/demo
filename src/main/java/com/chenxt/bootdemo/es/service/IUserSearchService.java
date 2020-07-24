package com.chenxt.bootdemo.es.service;

import com.chenxt.bootdemo.es.dto.UserSearchDTO;
import com.chenxt.bootdemo.es.vo.UserSearchVO;

import java.util.List;

/**
 * 用户搜索service接口
 *
 * @author chenxt
 * @date 2020/07/23
 */
public interface IUserSearchService {
    /**
     * 搜索用户
     *
     * @param userSearchDTO 搜索条件
     * @return 用户列表
     */
    List<UserSearchVO> search(UserSearchDTO userSearchDTO);
}
