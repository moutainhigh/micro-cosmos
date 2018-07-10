package com.tairan.cloud.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:thomas
 * @Date: 2018/6/26 18:19
 * @Description:
 */
@RestController
@RequestMapping(value = "/demo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DemoController {

    @RequestMapping(value = "/nativeInfo", method = RequestMethod.GET)
    public String nativeInfo(HttpServletRequest request){
        System.out.println("1111111111111111111111111");
        return "1111";
    }

    @RequestMapping(value = "/nativeInfo4", method = RequestMethod.GET)
    public String nativeInfo4(HttpServletRequest request){
        System.out.println("aaaaaaaaaaaa");
        return "aaaaaaaaa";
    }


    @RequestMapping(value = "/nativeInfo3", method = RequestMethod.GET)
    public String nativeInfo3(HttpServletRequest request){
        System.out.println("33");
        return "333";
    }

}
