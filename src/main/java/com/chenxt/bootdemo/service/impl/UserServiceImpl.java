package com.chenxt.bootdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenxt.bootdemo.base.api.ICachedPrefix;
import com.chenxt.bootdemo.base.api.RedisReentrantLock;
import com.chenxt.bootdemo.base.expection.BusinessException;
import com.chenxt.bootdemo.base.expection.enumeration.BusinessExceptionCodeEnum;
import com.chenxt.bootdemo.base.util.Md5Utils;
import com.chenxt.bootdemo.base.util.PhoneUtils;
import com.chenxt.bootdemo.dto.UserLoginDTO;
import com.chenxt.bootdemo.entity.User;
import com.chenxt.bootdemo.mapper.UserMapper;
import com.chenxt.bootdemo.mapper.UserNickNameIndexMapper;
import com.chenxt.bootdemo.service.IUserService;
import com.chenxt.bootdemo.service.cache.UserCacheService;
import com.chenxt.bootdemo.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.concurrent.locks.Lock;

/**
 * 用户 服务实现类
 *
 * @author chenxt
 * @date 2020-07-07
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserNickNameIndexMapper userNickNameIndexMapper;
    @Resource
    private UserCacheService userCacheService;
    @Resource
    private RedisReentrantLock redisReentrantLock;

    @Value("${verifycode.mode}")
    private Integer verifycodeMode;

    public static final String DEFAULT_VERIFYCODE = "111111";

    @Override
    public UserVO login(UserLoginDTO userLoginDTO) {
        String phone = userLoginDTO.getPhone();
        String email = userLoginDTO.getEmail();
        String password = userLoginDTO.getPassword();
        String verifyCode = userLoginDTO.getVerifyCode();

        if (StringUtils.isEmpty(phone) && StringUtils.isEmpty(email)) {
            //手机号码和电子邮箱至少输入一种
            throw new BusinessException(BusinessExceptionCodeEnum.INVALID_PARAMETER);
        }
        if (StringUtils.isEmpty(password) && StringUtils.isEmpty(verifyCode)) {
            //密码和验证码至少输入一种
            throw new BusinessException(BusinessExceptionCodeEnum.INVALID_PARAMETER);
        }
        if (!StringUtils.isEmpty(phone) && !PhoneUtils.isPhoneValidForInternal(phone)) {
            //手机号码不正确
            throw new BusinessException(BusinessExceptionCodeEnum.PHONE_ERROR);
        }

        User user;
        if (!StringUtils.isEmpty(password)) {
            //优先使用密码登陆
            user = loginByPassword(phone, email, password);
        } else {
            //使用验证码登陆
            user = loginByVerifyCode(phone, email, verifyCode);
            if (user == null) {
                user = register(userLoginDTO);
            }
        }

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    private User register(UserLoginDTO userLoginDTO) {
        String phone = userLoginDTO.getPhone();
        String email = userLoginDTO.getEmail();
        BusinessExceptionCodeEnum.PHONE_EXIST_ERROR.assertIsTrue(StringUtils.isEmpty(phone) || baseMapper.selectByPhone(phone) == null);
        BusinessExceptionCodeEnum.EMAIL_EXIST_ERROR.assertIsTrue(StringUtils.isEmpty(email) || baseMapper.selectByEmail(email) == null);
        //新用户注册
        return addUser(phone, email, null, null, null);
    }

    private User loginByPassword(String phone, String email, String password) {
        User user;
        if (!StringUtils.isEmpty(email)) {
            //电子邮箱+密码登陆
            user = baseMapper.selectByEmail(email);
        } else {
            //手机号码+密码登陆
            user = baseMapper.selectByPhone(phone);
        }
        BusinessExceptionCodeEnum.USER_NOT_EXISTS.assertNotNull(user);
        BusinessExceptionCodeEnum.USER_PASSWORD_ERROR.assertIsTrue(Md5Utils.verifyHex(user.getPassword(), password));
        return user;
    }

    private User loginByVerifyCode(String phone, String email, String verifyCode) {
        //验证验证码
        doVerifyCodeVerification(phone, email, verifyCode);
        //设置验证码票根
        userCacheService.setVerifyCodeTicket(phone, email, verifyCode);
        if (!StringUtils.isEmpty(email)) {
            //电子邮箱+验证码登陆
            return baseMapper.selectByEmail(email);
        } else {
            //手机号码+验证码登陆
            return baseMapper.selectByPhone(phone);
        }
    }

    private void doVerifyCodeVerification(String phone, String email, String verifyCode) {
        if (verifycodeMode == 1 && DEFAULT_VERIFYCODE.equals(verifyCode)) {
            return;
        }
        //从redis读取短信验证码
        Object redisVerifyCode = userCacheService.getVerifyCode(phone, email);
        BusinessExceptionCodeEnum.VERIFYCODE_INVALID.assertNotNull(redisVerifyCode);
        BusinessExceptionCodeEnum.VERIFYCODE_ERROR.assertEquals(redisVerifyCode.toString().toUpperCase(), verifyCode.toUpperCase());
        //验证成功之后失效
        userCacheService.deleteVerifyCode(phone, email);
    }

    private void doVerifyCodeVerificationForSecondTime(String phone, String email, String verifyCode) {
        if (verifycodeMode == 1 && DEFAULT_VERIFYCODE.equals(verifyCode)) {
            return;
        }
        //忘记密码或注册验证票根
        Object redisVerifyCode = userCacheService.getVerifyCodeTicket(phone, email);
        BusinessExceptionCodeEnum.VERIFYCODE_INVALID.assertNotNull(redisVerifyCode);
        BusinessExceptionCodeEnum.VERIFYCODE_ERROR.assertNotNull(verifyCode);
        BusinessExceptionCodeEnum.VERIFYCODE_ERROR.assertEquals(redisVerifyCode.toString().toUpperCase(), verifyCode.toUpperCase());
        //验证成功之后票根失效
        userCacheService.deleteVerifyCodeTicket(phone, email);
    }

    private User addUser(String phone, String email, String nickName, String avatar, String password) {
        User user = new User();
        user.setNickName(nickName == null ? getNickName() : nickName);
        user.setAvatar(avatar);
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(Md5Utils.encodeHex(password));
        }
        user.setPhone(phone);
        user.setEmail(email);
        user.setIsEnable(true);
        user.setBirthday(LocalDate.now());
        baseMapper.insert(user);
        return user;
    }

    private String getNickName() {
        String nickName = tryGetNickName();
        if (baseMapper.countByNickName(nickName) > 0) {
            return getNickName();
        }
        return nickName;
    }

    private String tryGetNickName() {
        Integer nickNameIndex = userCacheService.getNickNameIndex();
        if (nickNameIndex == null) {
            Lock obtain = redisReentrantLock.obtain(ICachedPrefix.bootdemo_global_user_nickname_index);
            //尝试获取锁，若已被其他线程获取则等待
            obtain.lock();
            try {
                log.info("已获取到锁，再次获取缓存昵称索引");
                nickNameIndex = userCacheService.getNickNameIndex();
                if (nickNameIndex == null) {
                    log.info("缓存昵称索引未更新，进行更新");
                    //获取昵称索引
                    nickNameIndex = userNickNameIndexMapper.selectOne(null).getNickNameIndex();
                    userNickNameIndexMapper.updateIndex(nickNameIndex + 10000);
                    //更新缓存昵称索引
                    userCacheService.updateNickNameIndex(nickNameIndex);
                    nickNameIndex = userCacheService.getNickNameIndex();
                }
            } catch (Exception e) {
                log.error("更新缓存昵称索引出现异常！", e);
                throw new BusinessException(BusinessExceptionCodeEnum.NICK_NAME_LOCK_GET_ERROR);
            } finally {
                obtain.unlock();
                log.info("更新缓存昵称索引结束！解锁完成！");
            }
        }
        return "用户" + String.format("%08d", nickNameIndex);
    }
}
