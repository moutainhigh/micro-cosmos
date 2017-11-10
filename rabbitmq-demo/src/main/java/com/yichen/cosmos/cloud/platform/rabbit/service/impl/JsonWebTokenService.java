package com.yichen.cosmos.cloud.platform.rabbit.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yichen.cosmos.cloud.platform.bean.Response;
import com.yichen.cosmos.cloud.platform.bean.ResponseStatus;
import com.yichen.cosmos.cloud.platform.bean.User;
import com.yichen.cosmos.cloud.platform.constants.Constant;
import com.yichen.cosmos.cloud.platform.constants.RedisKeys;
import com.yichen.cosmos.cloud.platform.dto.AccessTokenDto;
import com.yichen.cosmos.cloud.platform.enums.CreditWisdomEnum;
import com.yichen.cosmos.cloud.platform.exception.RefreshTokenExpiredException;
import com.yichen.cosmos.cloud.platform.exception.UserRemovedException;
import com.yichen.cosmos.cloud.platform.rabbit.config.RedisHelper;
import com.yichen.cosmos.cloud.platform.rabbit.dao.UserRedisDao;
import com.yichen.cosmos.cloud.platform.rabbit.service.IJsonWebTokenService;
import com.yichen.cosmos.cloud.platform.util.StringTools;
import com.yichen.cosmos.cloud.platform.util.auth.JwtHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * token操作
 * Created by Lizhengxian on 2017/6/23.
 */
@Service
public class JsonWebTokenService implements IJsonWebTokenService {
    private final static Logger logger = LoggerFactory.getLogger(JsonWebTokenService.class);

    @Resource
    private UserRedisDao userRedisDao;

    @Override
    public AccessTokenDto createToken(Map<String, Object> params) {
        //创建存储对象
        String subject = JwtHelper.generalSubject(params);
        //创建交互token
        String token = JwtHelper.createJWT(Constant.JWT_ID, subject, Constant.JWT_TTL);
        //创建刷新token凭据
        String refreshToken = JwtHelper.createJWT(Constant.JWT_ID, subject, Constant.JWT_REFRESH_TTL);
        //构建授权返回
        return createTokenDto(token, refreshToken);
    }

    @Override
    public AccessTokenDto createResetPasswordToken(Map<String, Object> params) {
        //创建存储对象
        String subject = JwtHelper.generalSubject(params);
        //创建交互token
        String token = JwtHelper.createJWT(Constant.JWT_ID, subject, 1000 * 60 * 2);
        //创建刷新token凭据
        String refreshToken = JwtHelper.createJWT(Constant.JWT_ID, subject, 1000 * 60 * 2);
        //构建授权返回
        return createTokenDto(token, refreshToken);
    }

    @Override
    public String refreshToken(HttpServletRequest request) throws RefreshTokenExpiredException, UserRemovedException {
        String requestToken = this.getRequestToken(request);
        //获得token里的信息
        Claims claims = JwtHelper.parseJWT(requestToken);
        if (claims == null) {
            throw new RefreshTokenExpiredException("刷新token解析失败");
        }
        //先判断是否过期
        claims.getExpiration().getTime();
        long now = System.currentTimeMillis();
        long expireTime = claims.getExpiration().getTime();
        if (expireTime < now) {
            logger.info("refreshToken已经过期，请重新登录");
            throw new RefreshTokenExpiredException("refreshToken已经过期，请重新登录");
        }

        //没有过期，提取信息，重新生成accessToken，写入redis
        String subject = claims.getSubject();
        JSONObject jsonObject = JSONObject.parseObject(subject);
        String userId = jsonObject.getString("userId");


        String key = RedisKeys.GET_WEB_ACCESS_TOKEN_KEY(userId);

        //todo 获取当前redis中的token是否与入参token相同
        //AccessTokenDto
        AccessTokenDto redisAccessTokenDto = (AccessTokenDto) RedisHelper.redisHelper.get(key);
        if (!redisAccessTokenDto.getRefreshToken().equals(this.getRequestToken(request))) {
            //入参的token不等于redis中的token，那么就拒绝
            logger.info("redis中记录的refresh_token与入参refresh_token不匹配");
            throw new RefreshTokenExpiredException("refreshToken已经过期，请重新登录");
        }
        User user = userRedisDao.getUserById(userId);
        if (user == null) {
            logger.info("用户已经被删除了，无法创建新的accessToken");
            throw new UserRemovedException("用户已经被删除了，无法创建新的accessToken");
        }

        HashMap<String, Object> tokenParams = new HashMap<>(6);
        tokenParams.put("userId", userId);
        tokenParams.put("account", user.getAccount());
        tokenParams.put("createdTime", System.currentTimeMillis());
        tokenParams.put("creatorId", userId);
        tokenParams.put("updaterId", userId);
        tokenParams.put("orgId", user.getOrgId());
        //fatherId:0表示主账号
        tokenParams.put("fatherId", user.getFatherId());
        //一小时过期
        tokenParams.put("expireTime", Constant.JWT_TTL);
        AccessTokenDto tokenDto = this.createToken(tokenParams);
        if (tokenDto == null) {
            //token生成失败
            logger.error("token生成失败.user={}", JSON.toJSONString(user));
            return new Response.Builder().code(ResponseStatus.RESPONSE_CODE_500)
                    .msg(ResponseStatus.RESPONSE_CODE_500)
                    .build().toString();
        }

        Map<String, Object> dataMap = new HashMap<>(1);
        dataMap.put("token", tokenDto);
        if (CreditWisdomEnum.WISDOM_MAIN_ACCOUNT.getCode().equals(user.getFatherId())) {
            dataMap.put("isParent", true);
        } else {
            dataMap.put("isParent", false);
        }

        //将User数据存放到redis中
        RedisHelper.redisHelper.set(key, tokenDto, Constant.JWT_REFRESH_TTL);//7天过期

        return new Response.Builder().code(ResponseStatus.RESPONSE_CODE_200)
                .msg(ResponseStatus.RESPONSE_MSG_200)
                .data(dataMap)
                .build().toString();
    }

    @Override
    public int deleteToken(Map<String, Object> params) {
        return 0;
    }

    @Override
    public JSONObject parseToken(HttpServletRequest request) {
        String token = getRequestToken(request);
        return this.parseToken(token);
    }

    @Override
    public JSONObject parseToken(String token) {
        if (token.startsWith("bearer") || token.startsWith("Bearer")) {
            //如果是未处理的bearer开头的token，则处理一下，成为纯token
            token = token.substring(7, token.length());
        }
        JSONObject t = null;
        try {
            String stringKey = Constant.JWT_SECRET;
            byte[] encodedKey = Base64.decodeBase64(stringKey);
            SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token).getBody();
            String subject = claims.getSubject();
            t = JSONObject.parseObject(subject);
        } catch (ExpiredJwtException e) {
            //过期了，过期了不打印
            //logger.error("Method [JsonWebTokenService.parseToken] ExpiredJwtException = {}", e);
            t = new JSONObject(1);
            t.put("isExpired", true);
            return t;
        } catch (Exception e) {
            logger.error("Method [JsonWebTokenService.parseToken] Exception = {}", e);
        }
        return t;
    }

    @Override
    public String getRequestToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        //为了适应下载报表的url
        if (StringTools.isEmpty(token)) {
            token = request.getParameter("token");
        }

        if (!StringUtils.isEmpty(token) && token.length() > 7) {
            String type = token.substring(0, 6);
            //判断认证类型必须为bearer
            if (type.equalsIgnoreCase("bearer")) {
                token = token.substring(7, token.length());
                return token;
            }
        }
        return null;
    }

    @Override
    public Claims getClaims(HttpServletRequest request) {
        String requestToken = this.getRequestToken(request);
        return JwtHelper.parseJWT(requestToken);
    }

    /**
     * 生成 tokenDTO对象
     *
     * @param token        token
     * @param refreshToken refreshToken
     * @return accessTokenDto 对象
     */
    private AccessTokenDto createTokenDto(String token, String refreshToken) {
        try {
            Claims claims = JwtHelper.parseJWT(token);
            long expireTime = claims.getExpiration().getTime();
            return new AccessTokenDto(token, Constant.TOKEN_TYPE, refreshToken, expireTime);
        } catch (Exception e) {
            logger.error("Method [JsonWebTokenService.createToken] Exception = {}", e);
        }
        return null;
    }
}
