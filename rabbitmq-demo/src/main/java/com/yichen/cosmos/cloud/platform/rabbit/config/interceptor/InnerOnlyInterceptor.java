package com.yichen.cosmos.cloud.platform.rabbit.config.interceptor;

import com.yichen.cosmos.cloud.platform.annotation.InnerOnly;
import com.yichen.cosmos.cloud.platform.util.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 内部接口访问
 * 结合 注解{@link InnerOnly} 使用
 *
 * @author thomas.su
 */
@Component
public class InnerOnlyInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        try {
            logger.debug("请求路径：path={}", request.getRequestURL());
            logger.debug("inneronly=======================");
            if (handler instanceof HandlerMethod) {
                HandlerMethod method = (HandlerMethod) handler;
                if (method.getMethod().getAnnotation(InnerOnly.class) != null) {
                    String ip = IpUtils.getUserIP(request);
                    logger.debug("------------------ ip={}", ip);
                    //待优化为后台可配置项
                    if (ip.startsWith("127.") || ip.startsWith("192.168")) {
                        logger.info("拦截器拦截：path={}", request.getRequestURL());
                        return true;
                    } else {
                        return false;
                    }
                }
            }

        } catch (Exception e) {
            logger.error("拦截器处理异常.e={}", e);
            e.printStackTrace();
        }
        return true;
    }

}