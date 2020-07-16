package com.chenxt.bootdemo.service;

import com.chenxt.bootdemo.BaseTest;
import com.chenxt.bootdemo.dto.UserLoginDTO;
import com.chenxt.bootdemo.entity.User;
import com.chenxt.bootdemo.mapper.UserMapper;
import com.chenxt.bootdemo.service.cache.UserCacheService;
import com.chenxt.bootdemo.vo.UserVO;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * 用户service 测试
 *
 * @author chenxt
 * @date 2020/07/16
 */
public class UserServiceTest extends BaseTest {

    @Resource
    private IUserService userService;

    @MockBean
    private UserCacheService userCacheService;
    @MockBean
    private UserMapper userMapper;

    @Test
    public void loginTest() {
        LocalDate now = LocalDate.now();
        User user = new User();
        user.setNickName("用户99999999");
        user.setPhone("18251816111");
        user.setBirthday(now);
        when(userCacheService.getNickNameIndex()).thenReturn(99999999);
        when(userCacheService.getVerifyCode(any(), any())).thenReturn("111111");
        when(userCacheService.getVerifyCodeTicket(any(), any())).thenReturn("111111");
        doNothing().when(userCacheService).setVerifyCodeTicket(any(), any(), any());
        doNothing().when(userCacheService).deleteVerifyCode(any(), any());
        doNothing().when(userCacheService).deleteVerifyCodeTicket(any(), any());
        doNothing().when(userCacheService).updateNickNameIndex(any());
        when(userMapper.selectByEmail(any())).thenReturn(null);
        when(userMapper.selectByPhone(any())).thenReturn(user);
        when(userMapper.countByNickName(any())).thenReturn(0);
        when(userMapper.insert(any())).thenReturn(0);
        UserVO userVO = userService.login(UserLoginDTO.builder().phone("18251826111").verifyCode("111111").build());
        assertNotNull(userVO);
        verify(userMapper, times(0)).selectByEmail(any());
        verify(userMapper, times(1)).selectByPhone(any());
        verify(userMapper, times(0)).countByNickName(any());
        verify(userMapper, times(0)).insert(any());
    }
}
