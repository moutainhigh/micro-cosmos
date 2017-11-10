package com.yichen.cosmos.cloud.platform.util.auth;

import com.alibaba.fastjson.JSON;
import com.yichen.cosmos.cloud.platform.constants.Constant;
import com.yichen.cosmos.cloud.platform.dto.AccessTokenDto;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;


/**
 * 创建TOKEN工具类
 * <p>
 * Created by Linq on 2017/3/27 9:32.
 */
public class TokenHelper {

    private static Logger logger = LoggerFactory.getLogger(TokenHelper.class);

    /**
     * 生成AccessToken信息
     *
     * @param user ""
     * @return ""
     */
    public static <T> AccessTokenDto getTokenInfo(T user) {
        //创建存储对象
        String subject = JwtHelper.generalSubject(user);
        //创建交互token
        String token = JwtHelper.createJWT(Constant.JWT_ID, subject, Constant.JWT_TTL);
        //创建刷新token凭据
        String refreshToken = JwtHelper.createJWT(Constant.JWT_ID, subject, Constant.JWT_REFRESH_TTL);
        //构建授权返回
        try {
            Claims claims = JwtHelper.parseJWT(token);
            long expireTime = claims.getExpiration().getTime();
            return new AccessTokenDto(token, Constant.TOKEN_TYPE, refreshToken, expireTime);
        } catch (Exception e) {
            logger.error("Method [TokenHelper.getTokenInfo] Exception = {}", e);
        }
        return null;
    }

    /**
     * 解析token
     *
     * @param token ""
     * @return ""
     */
    public static <T> T parseToken(String token, Class<T> clazz) {
        T t = null;
        try {
            Claims claims = JwtHelper.parseJWT(token);
            if (claims == null) {
                return null;
            }
            String subject = claims.getSubject();
            logger.info("subject = " + subject);
            t = JSON.parseObject(subject, clazz);
        } catch (Exception e) {
            logger.error("Method [TokenHelper.parseToken] Exception = {}", e);
        }
        return t;
    }


    /**
     * 获取用户请求中的TOKEN
     *
     * @param request ""
     * @return ""
     */
    public static String getRequestToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(token) && token.length() > 7) {
            String type = token.substring(0, 6).toLowerCase();
            //判断认证类型必须为bearer
            if (type.compareTo("bearer") == 0) {
                //获取授权token
                token = token.substring(7, token.length());
                return token;
            }
        }
        return null;
    }


    /**
     * 解析token<br/>
     * <p>
     * 请使用 UserTokenHelper 类型操作 <br/>
     * UserTokenHelper.parseToken(request, clazz) <br/>
     * 如果是CustomerInfoDao类型可以使用<br/>
     * UserTokenHelper.parseToken(request) <br/>
     *
     * @param request ""
     * @return ""
     */
    @Deprecated
    public static <T> T parseToken(HttpServletRequest request, Class<T> clazz) {
        String token = getRequestToken(request);
        return parseToken(token, clazz);
    }


    /**
     * 刷新token
     *
     * @param refreshToken refreshToken
     * @return ""
     */
    public static <T> AccessTokenDto refreshToken(String refreshToken, Class<T> clazz) {
        try {
            T t = parseToken(refreshToken, clazz);
            if (t != null) {
                return getTokenInfo(t);
            }
        } catch (Exception e) {
            logger.error("refresh token error = {}", e);
        }
        return null;
    }

}
