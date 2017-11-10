package com.yichen.cosmos.cloud.platform.rabbit.service;

import com.alibaba.fastjson.JSONObject;
import com.yichen.cosmos.cloud.platform.dto.AccessTokenDto;
import com.yichen.cosmos.cloud.platform.exception.RefreshTokenExpiredException;
import com.yichen.cosmos.cloud.platform.exception.UserRemovedException;
import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * JsonWebToken操作接口
 * Created by Lizhengxian on 2017/6/23.
 */
public interface IJsonWebTokenService {

    /**
     * 创建token
     *
     * @param params 需要放到payload里面的数据
     * @return
     */
    AccessTokenDto createToken(Map<String, Object> params);

    /**
     * 创建重置密码令牌token
     *
     * @param params 需要放到payload里面的数据
     * @return
     */
    AccessTokenDto createResetPasswordToken(Map<String, Object> params);

    /**
     * 刷新token
     *
     * @param request Controller层传入的request
     * @return 新的token，直接返回给前端就行了
     * @throws RefreshTokenExpiredException refresh_token也过期了
     * @throws UserRemovedException         用户已经被删除了
     */
    String refreshToken(HttpServletRequest request) throws RefreshTokenExpiredException, UserRemovedException;

    /**
     * 删除在redis中保存的token
     *
     * @param params
     * @return
     */
    int deleteToken(Map<String, Object> params);

    /**
     * 渲染token
     *
     * @param request
     * @return
     */
    JSONObject parseToken(HttpServletRequest request);

    /**
     * 渲染token
     *
     * @param token
     * @return
     */
    JSONObject parseToken(String token);

    /**
     * 从request中获取token
     *
     * @param request
     * @return
     */
    String getRequestToken(HttpServletRequest request);

    /**
     * 渲染token为Claims对象，Claims保存着完整Payload信息
     *
     * @param request
     * @return
     */
    Claims getClaims(HttpServletRequest request);

}
