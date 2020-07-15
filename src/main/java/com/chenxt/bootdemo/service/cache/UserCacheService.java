package com.chenxt.bootdemo.service.cache;

import com.chenxt.bootdemo.base.api.ICachedPrefix;
import com.chenxt.bootdemo.base.api.RedisApi;
import com.chenxt.bootdemo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 用户缓存Service
 *
 * @author chenxt
 * @date 2020/07/15
 */
@Slf4j
@Service
public class UserCacheService implements ICachedPrefix {
    @Resource
    private UserMapper userMapper;
    @Resource
    private HttpServletRequest httpServletRequest;

    private final static Integer NICK_NAME_INDEX_INCREASE_COUNT = 10000;

    /**
     * 获取验证码票根
     *
     * @param phone 手机号码
     * @param email 电子邮箱
     * @return 验证码
     */
    public Object getVerifyCodeTicket(String phone, String email) {
        String type = StringUtils.isEmpty(email) ? "phone" : "email";
        String value = StringUtils.isEmpty(email) ? phone : email;
        String key = String.format(bootdemo_user_verifycode_ticket, type, value);
        return RedisApi.get(key);
    }

    /**
     * 设置验证码票根
     *
     * @param phone      手机号码
     * @param email      电子邮箱
     * @param verifyCode 短信验证码
     */
    public void setVerifyCodeTicket(String phone, String email, String verifyCode) {
        String type = StringUtils.isEmpty(email) ? "phone" : "email";
        String value = StringUtils.isEmpty(email) ? phone : email;
        String key = String.format(bootdemo_user_verifycode_ticket, type, value);
        RedisApi.set(key, verifyCode, 5L, TimeUnit.MINUTES);
    }

    /**
     * 获取验证码
     *
     * @param phone 手机号码
     * @param email 电子邮箱
     * @return 验证码
     */
    public Object getVerifyCode(String phone, String email) {
        String type = StringUtils.isEmpty(email) ? "phone" : "email";
        String value = StringUtils.isEmpty(email) ? phone : email;
        String key = String.format(bootdemo_user_verifycode, type, value);
        return RedisApi.get(key);
    }

    /**
     * 删除验证码
     *
     * @param phone 手机号码
     * @param email 电子邮箱
     * @return 验证码
     */
    public void deleteVerifyCode(String phone, String email) {
        String type = StringUtils.isEmpty(email) ? "phone" : "email";
        String value = StringUtils.isEmpty(email) ? phone : email;
        String key = String.format(bootdemo_user_verifycode, type, value);
        RedisApi.remove(key);
    }

    /**
     * 清空验证码票根
     *
     * @param phone 手机号码
     * @param email 电子邮箱
     */
    public void deleteVerifyCodeTicket(String phone, String email) {
        String type = StringUtils.isEmpty(email) ? "phone" : "email";
        String value = StringUtils.isEmpty(email) ? phone : email;
        String key = String.format(bootdemo_user_verifycode_ticket, type, value);
        RedisApi.remove(key);
    }

    /**
     * 获取昵称索引
     *
     * @return 昵称索引
     */
    public Integer getNickNameIndex() {
        String key = bootdemo_user_nickname_index;
        Object nickNameIndex = RedisApi.lLeftPop(key);
        return nickNameIndex == null ? null : Integer.parseInt(nickNameIndex.toString());
    }

    /**
     * 更新昵称索引
     *
     * @param nickNameIndex 昵称索引
     */
    public void updateNickNameIndex(Integer nickNameIndex) {
        String key = bootdemo_user_nickname_index;
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < NICK_NAME_INDEX_INCREASE_COUNT; i++) {
            indexList.add(nickNameIndex++);
        }
        RedisApi.setList(key, indexList);
    }

}
