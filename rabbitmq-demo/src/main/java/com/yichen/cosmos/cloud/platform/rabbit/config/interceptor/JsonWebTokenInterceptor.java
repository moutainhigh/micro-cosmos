package com.yichen.cosmos.cloud.platform.rabbit.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yichen.cosmos.cloud.platform.bean.Response;
import com.yichen.cosmos.cloud.platform.bean.ResponseStatus;
import com.yichen.cosmos.cloud.platform.constants.RedisKeys;
import com.yichen.cosmos.cloud.platform.rabbit.config.RedisHelper;
import com.yichen.cosmos.cloud.platform.rabbit.service.IJsonWebTokenService;
import com.yichen.cosmos.cloud.platform.util.StringTools;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * JWT权限验证
 * Created by Lizhengxian on 2017/6/23.
 */
@Component
public class JsonWebTokenInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(JsonWebTokenInterceptor.class);

    @Autowired
    private IJsonWebTokenService jsonWebTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String[]> params = request.getParameterMap();
        logger.info("============= params={}", JSON.toJSONString(params));
        String token = request.getHeader("Authorization");
        if (StringTools.isEmpty(token)) {
            token = request.getParameter("token");
        }
        logger.info("============= token={}", token);
        if (StringTools.isEmpty(token)) {
            logger.warn("没有获取到token参数，请带上token参数");
            logger.warn("拦截器拦截：path={}", request.getRequestURL());
            String jsonString = new Response.Builder()
                    .code(ResponseStatus.RESPONSE_CODE_409)
                    .msg(ResponseStatus.RESPONSE_MSG_409)
                    .build().toString();
            print(jsonString, response);
            return false;
        }
        //解析accessToken
        JSONObject jsonObject = jsonWebTokenService.parseToken(request);
        //是否解析成功
        if (jsonObject == null) {
            logger.warn("无法解析access_token参数，请检查传参是否正确，也有可能是篡改了token，导致签名无法验证");
            logger.warn("拦截器拦截：path={}", request.getRequestURL());
            String jsonString = new Response.Builder()
                    .code(ResponseStatus.RESPONSE_CODE_408)
                    .msg(ResponseStatus.RESPONSE_MSG_408)
                    .build().toString();
            print(jsonString, response);
            return false;
        }

        if (jsonObject.containsKey("isExpired") && jsonObject.getBoolean("isExpired")) {
            //解析token的时候抛出过期异常
            logger.info("解析token的时候抛出过期异常 : ExpiredJwtException");
            logger.info("拦截器拦截：path={}", request.getRequestURL());
            String jsonString = new Response.Builder()
                    .code(ResponseStatus.RESPONSE_CODE_405)
                    .msg(ResponseStatus.RESPONSE_MSG_405)
                    .build().toString();
            print(jsonString, response);
            return false;
        }

        //根据userId获取redis的key
        String key = RedisKeys.GET_WEB_ACCESS_TOKEN_KEY(jsonObject.getString("userId"));

        //查询Redis中是否存在token，如果存在校验token的时效和正确性，否则返回未授权
        if (!RedisHelper.redisHelper.exists(key)) {
            //redis中无当前登录信息
            logger.warn("access_token已经过期");
            logger.warn("拦截器拦截：path={}", request.getRequestURL());
            String jsonString = new Response.Builder()
                    .code(ResponseStatus.RESPONSE_CODE_405)
                    .msg(ResponseStatus.RESPONSE_MSG_405)
                    .build().toString();
            print(jsonString, response);
            return false;
        }

        //todo 获取当前redis中的token是否与入参token相同 (登录顶掉原来的token)
        //AccessTokenDto
//        AccessTokenDto tokenDto = (AccessTokenDto)RedisHelper.redisHelper.get(key);
//        if(!tokenDto.getAccessToken().equals(jsonWebTokenService.getRequestToken(request))){
//            //入参的token不等于redis中的token，那么就拒绝
//            logger.info("redis中记录的token与入参access_token不匹配");
//            logger.info("拦截器拦截：path={}",request.getRequestURL());
//            String jsonString = new Response.Builder()
//                    .code(ResponseStatus.RESPONSE_CODE_405)
//                    .msg(ResponseStatus.RESPONSE_MSG_405)
//                    .build().toString();
//            print(jsonString,response);
//            return false;
//        }


        //判断token是否过期
        Claims claims = jsonWebTokenService.getClaims(request);
        if (claims != null) {
            long now = System.currentTimeMillis();
            long expireTime = claims.getExpiration().getTime();
            //logger.info("expireTime={},now={}",expireTime,now);
            if (expireTime < now) {
                logger.info("redis中记录的时间表示access_token已经过期");
                logger.info("拦截器拦截：path={}", request.getRequestURL());
                String jsonString = new Response.Builder()
                        .code(ResponseStatus.RESPONSE_CODE_405)
                        .msg(ResponseStatus.RESPONSE_MSG_405)
                        .build().toString();
                print(jsonString, response);
                return false;
            }
        }

        //没过期就放行
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        //刷新token
        //logger.info("刷新token");
        //AccessTokenDto accessTokenDto = jsonWebTokenService.refreshToken(request);
        //String access_token = accessTokenDto.getAccessToken();
        //String token_type = accessTokenDto.getTokenType();
        //String authorization = token_type+" "+access_token;
        //response.setHeader("Authorization",authorization);
    }

    private void print(String jsonString, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(jsonString);
    }
}
