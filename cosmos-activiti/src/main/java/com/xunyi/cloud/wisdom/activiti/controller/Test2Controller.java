package com.xunyi.cloud.wisdom.activiti.controller;

import com.xunyi.cloud.wisdom.activiti.service.ITestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class Test2Controller {
    private static final Logger logger = LoggerFactory.getLogger(Test2Controller.class);

    @Autowired
    private ITestService testService;

    @RequestMapping(value = "/demo")
    public String demo(){
        testService.testName();
        return "{\"code\":200}";
    }



}
