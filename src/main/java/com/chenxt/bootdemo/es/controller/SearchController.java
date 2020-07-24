package com.chenxt.bootdemo.es.controller;

import com.chenxt.bootdemo.es.dto.UserSearchDTO;
import com.chenxt.bootdemo.es.service.IUserSearchService;
import com.chenxt.bootdemo.es.vo.UserSearchVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 搜索controller
 *
 * @author chenxt
 * @date 2020/07/23
 */
@Api(tags = "用户controller")
@RestController
@RequestMapping(value = "/search")
public class SearchController {
    @Resource
    private IUserSearchService userSearchService;

    @ApiOperation(value = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "", value = ""),
    })
    @GetMapping("/user")
    public List<UserSearchVO> searchUser(@RequestBody UserSearchDTO userSearchDTO) {
        return userSearchService.search(userSearchDTO);
    }
}
