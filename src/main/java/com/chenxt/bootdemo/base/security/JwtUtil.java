package com.chenxt.bootdemo.base.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chenxt.bootdemo.base.constrants.TimeConstants;
import com.chenxt.bootdemo.base.expection.enumeration.BusinessExceptionCodeEnum;
import com.chenxt.bootdemo.base.expection.BusinessException;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * jwt工具类
 *
 * @author chenxt
 * @date 2020/07/14
 */
public class JwtUtil {
    private static final String SECRET = "chenxt2020chenxt2020chenxt2020chenxt2020chenxt2020";

    /**
     * 创建jwt
     * 默认ttl为一年
     *
     * @param token jwt主体
     * @return jwt
     */
    public static String createJwt(Token token) {
        return createJwt(token, TimeConstants.ONE_YEAR_MILLISECONDS);
    }

    /**
     * 创建jwt
     *
     * @param token     jwt主体
     * @param ttlMillis 超时时间
     * @return jwt
     */
    public static String createJwt(Token token, long ttlMillis) {
        String secret = SECRET;
        Algorithm algorithm = Algorithm.HMAC256(secret);

        Date now = new Date();
        JWTCreator.Builder builder = JWT.create();
        builder.withSubject(token.getCurrentUserId().toString())
                .withIssuedAt(now);
        if (ttlMillis >= 0) {
            int ttlDays = (int) (ttlMillis / 86400000L);
            int ttlDayMillis = (int) (ttlMillis % 86400000L);
            now = DateUtils.addDays(now, ttlDays);
            now = DateUtils.addMilliseconds(now, ttlDayMillis);
            builder.withExpiresAt(now);
        }

        return builder.sign(algorithm);
    }

    /**
     * 获取jwt中的信息
     *
     * @param jwt jwt
     * @return 用户信息
     */
    public static Token getSubjectByJwt(String jwt) {
        String secret = SECRET;
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();

        try {
            DecodedJWT decodedJwt = jwtVerifier.verify(jwt);
            String subject = decodedJwt.getSubject();

            return Token.builder()
                    .currentUserId(Long.parseLong(subject))
                    .build();
        } catch (Exception e) {
            //ExpiredJwtException
            throw new BusinessException(BusinessExceptionCodeEnum.JWT_INVALID);
        }
    }
}
