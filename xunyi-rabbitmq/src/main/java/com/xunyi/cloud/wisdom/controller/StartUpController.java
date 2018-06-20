package com.xunyi.cloud.wisdom.controller;

import com.alibaba.fastjson.JSON;
import com.xunyi.cloud.wisdom.MybatisGenerator.StartUp;
import com.yichen.cosmos.cloud.platform.bean.Response;
import com.yichen.cosmos.cloud.platform.bean.ResponseStatus;
import com.yichen.cosmos.cloud.platform.util.HttpsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author:thomas
 * @Date: 2018/3/22 18:53
 * @Email:1165030287@qq.com
 * @Description:
 */
@RestController
@RequestMapping(value = "/start", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StartUpController {
    private static final Logger logger = LoggerFactory.getLogger(StartUpController.class);
    @Autowired
    private StartUp  startUp;

    @RequestMapping(value = "/startUp", method = RequestMethod.GET)
    public String startUp(HttpServletRequest request) {
        startUp.startUp();
        return new Response.Builder().code(ResponseStatus.RESPONSE_CODE_200).msg(ResponseStatus.RESPONSE_MSG_200)
                .build().toString();
    }

    @RequestMapping(value = "/testTimeOut1Node", method = RequestMethod.GET)
    public void testTimeOut1Node(HttpServletRequest request){
        String url = "http://127.0.0.1:33002/xunyi-activiti/test/testTimeOut2Node";
        try{


            long start = System.currentTimeMillis();
            while(true){
                TimeUnit.MILLISECONDS.sleep(10);
                //并发调用
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Map<String,String> params = new HashMap<>();
                            params.put("thread-name",Thread.currentThread().getName());
                            String result = HttpsUtils.doPost(url, JSON.toJSONString(params));
                            logger.info("第一个节点,线程名：{}， result={}，统计耗时：{}",Thread.currentThread().getName(),result,System.currentTimeMillis()-start);

                        }catch (Exception e){
                            logger.error("线程并发调用异常",e);
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

        }catch (Exception e){
            logger.error("http 超时调用异常",e);
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/testTimeOut3Node", method = RequestMethod.POST)
    public String testTimeOut3Node(HttpServletRequest request,@RequestBody String param){

        logger.info("调用的参数：{}",param);
        Map<String,String> params = new HashMap<>();
        params.put("number","333333");
        try{
            long start = System.currentTimeMillis();
            TimeUnit.MINUTES.sleep(2);
            logger.info("第三个节点 ，休眠2分钟;时间为{}",System.currentTimeMillis()-start);
        }catch (Exception e){
            logger.error("http 超时调用异常",e);
            e.printStackTrace();
            return new Response.Builder().code(ResponseStatus.RESPONSE_CODE_500).msg(ResponseStatus.RESPONSE_MSG_500)
                    .build().toString();
        }
        return new Response.Builder().code(ResponseStatus.RESPONSE_CODE_200).msg(ResponseStatus.RESPONSE_MSG_200)
                .data(params)
                .build().toString();
    }
}
