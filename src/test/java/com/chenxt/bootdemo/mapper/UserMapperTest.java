package com.chenxt.bootdemo.mapper;

import com.chenxt.bootdemo.BaseTest;
import com.chenxt.bootdemo.base.util.SnowFlakeIdUtils;
import com.chenxt.bootdemo.entity.User;
import org.junit.Test;

import javax.annotation.Resource;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 用户mapper 测试
 *
 * @author chenxt
 * @date 2020/07/16
 */
public class UserMapperTest extends BaseTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void selectByPhoneTest() {
        LocalDate date = LocalDate.now();
        User user = new User();
        user.setPhone("12999999999");
        user.setBirthday(date);
        userMapper.insert(user);

        user = userMapper.selectByPhone("12999999999");
        assertNotNull(user);
        assertEquals(date, user.getBirthday());
    }

    @Test
    public void selectByEmailTest() {
        LocalDate date = LocalDate.now();
        String email = SnowFlakeIdUtils.nextId() + "@qq.com";
        User user = new User();
        user.setEmail(email);
        user.setBirthday(date);
        userMapper.insert(user);

        user = userMapper.selectByEmail(email);
        assertNotNull(user);
        assertEquals(date, user.getBirthday());
    }

    @Test
    public void countByNickNameTest() {
        String nickName = JUNIT_TEST_DATA_PREFIX + "昵称";
        User user = new User();
        user.setNickName(nickName);
        user.setBirthday(LocalDate.now());
        userMapper.insert(user);

        user = new User();
        user.setNickName(nickName);
        user.setBirthday(LocalDate.now());
        userMapper.insert(user);

        Integer count = userMapper.countByNickName(nickName);
        assertNotNull(count);
        assertEquals(count, 2);
    }
}
