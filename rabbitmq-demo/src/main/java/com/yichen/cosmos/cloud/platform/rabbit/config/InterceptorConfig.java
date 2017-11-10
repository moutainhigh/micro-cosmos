package com.yichen.cosmos.cloud.platform.rabbit.config;

import com.yichen.cosmos.cloud.platform.rabbit.config.interceptor.ContentTypeInterceptor;
import com.yichen.cosmos.cloud.platform.rabbit.config.interceptor.HtmlScriptIntercept;
import com.yichen.cosmos.cloud.platform.rabbit.config.interceptor.InnerOnlyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private ContentTypeInterceptor contentTypeInterceptor;

    @Autowired
    private InnerOnlyInterceptor innerOnlyInterceptor;

    @Autowired
    private HtmlScriptIntercept htmlScriptIntercept;

    /**
     * 添加拦截处理
     * 保持拦截器的顺序
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//		只需要插入、更新数据的操作做过滤即可
        registry.addInterceptor(htmlScriptIntercept)
                .addPathPatterns("/*/**")
        ;

        //配合@InnerOnly 只允许内部进行访问的接口
        registry.addInterceptor(innerOnlyInterceptor)
                .addPathPatterns("/*/**")

        ;
    }


}