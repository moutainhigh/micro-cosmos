package com.yichen.cosmos.cloud.platform.rabbit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by thomas.su on 2017/11/10 11:47.
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping(value = "/testException", produces = "application/json; charset=UTF-8")
    public String testException(HttpServletRequest request) {
        try {
            testa();
            testb();
            return "200";
        } catch (Exception e) {
            System.out.println("error....");
        } finally {
            System.out.println("zong......");
        }
        return "500";
    }

    private void testa() {
        System.out.println("testa");

    }

    private void testb() {
        try {
            System.out.println("testb");
            int b = 1;
            int a = 0;
            int result = b / a;
        } catch (Exception e) {
            System.out.println("testb error.");
            throw new RuntimeException("testb");
        } finally {
            System.out.println("testb error.......");
        }

    }


}
