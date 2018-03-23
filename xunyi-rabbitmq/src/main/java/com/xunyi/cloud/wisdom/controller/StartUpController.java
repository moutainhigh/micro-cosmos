package com.xunyi.cloud.wisdom.controller;

import com.xunyi.cloud.wisdom.MybatisGenerator.StartUp;
import com.yichen.cosmos.cloud.platform.bean.Response;
import com.yichen.cosmos.cloud.platform.bean.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:thomas
 * @Date: 2018/3/22 18:53
 * @Email:1165030287@qq.com
 * @Description:
 */
@RestController
@RequestMapping(value = "/start", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StartUpController {
    @Autowired
    private StartUp  startUp;

    @RequestMapping(value = "/startUp", method = RequestMethod.GET)
    public String startUp(HttpServletRequest request) {
        startUp.startUp();
        return new Response.Builder().code(ResponseStatus.RESPONSE_CODE_200).msg(ResponseStatus.RESPONSE_MSG_200)
                .build().toString();
    }
}
