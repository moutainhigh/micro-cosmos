package com.yichen.cosmos.cloud.platform.rabbit.config.interceptor;

import com.yichen.cosmos.cloud.platform.util.ChangePlainText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;


/**
 * 过滤前端文本框嵌入的html及脚本
 *
 * @author thomas.su
 */
@Component
public class HtmlScriptIntercept extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.debug("请求路径：path={}", request.getRequestURL());
        Enumeration<String> params = request.getParameterNames();
        if (params != null) {
            while (params.hasMoreElements()) {
                String param = (String) params.nextElement();
                String[] values = request.getParameterValues(param);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {

                        String value = values[i];
                        values[i] = ChangePlainText.Html2Text(value);
                    }

                    request.setAttribute(param, values);
                }
            }
        }

        return true;
    }


}
