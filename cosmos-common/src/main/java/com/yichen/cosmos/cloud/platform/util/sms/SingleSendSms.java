package com.yichen.cosmos.cloud.platform.util.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yichen.cosmos.cloud.platform.util.sms.constant.Constants;
import com.yichen.cosmos.cloud.platform.util.sms.constant.HttpSchema;
import com.yichen.cosmos.cloud.platform.util.sms.enums.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SingleSendSms {

    private final static Logger logger = LoggerFactory.getLogger(SingleSendSms.class);

//    private final static String APP_KEY = "AppKey"; //AppKey从控制台获取
//    private final static String APP_SECRET = "AppSecret"; //AppSecret从控制台获取
//    private final static String SIGN_NAME = "签名名称"; // 签名名称从控制台获取，必须是审核通过的
//    private final static String TEMPLATE_CODE = "模板CODE"; //模板CODE从控制台获取，必须是审核通过的

    private final static String HOST = "sms.market.alicloudapi.com"; //API域名从控制台获取

    private final static String ERRORKEY = "errorMessage";  //返回错误的key

    // @phoneNum: 目标手机号，多个手机号可以逗号分隔;
    // @params: 短信模板中的变量，数字必须转换为字符串，如短信模板中变量为${no}",则参数params的值为{"no":"123456"}
    public static String sendMsg(String appKey, String appSecret, String signName, String templateCode,
                                 String phoneNum, String params) {
        String path = "/singleSendSms";
        logger.info("------------------------ params={}", params);
        Request request = new Request(Method.GET, HttpSchema.HTTP + HOST, path, appKey, appSecret, Constants.DEFAULT_TIMEOUT);

        //请求的query
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("SignName", signName);
        querys.put("TemplateCode", templateCode);
        querys.put("RecNum", phoneNum);
        querys.put("ParamString", params);
        request.setQuerys(querys);

        Map<String, Object> msgMap = new HashMap<String, Object>();

        try {
            Map<String, String> bodymap = new HashMap<String, String>();
            Response response = Client.execute(request);
            //根据实际业务需要，调整对response的处理
            if (null == response) {
                logger.info("no response");
                return null;
            } else if (200 != response.getStatusCode()) {
                logger.info("StatusCode:" + response.getStatusCode());
                logger.info("ErrorMessage:" + response.getErrorMessage());
                logger.info("RequestId:" + response.getRequestId());


                msgMap.put("statusCode", response.getStatusCode());
                msgMap.put("errorMessage", response.getErrorMessage());

                return JSON.toJSONString(msgMap);

            } else {
                bodymap = ReadResponseBodyContent(response.getBody());
                if (null != bodymap.get(ERRORKEY)) {
                    //当传入的参数不合法时，返回有错误说明
                    logger.info(bodymap.get(ERRORKEY));

                    msgMap.put("statusCode", "406");
                    msgMap.put("errorMessage", bodymap.get(ERRORKEY));

                } else {
                    //成功返回map，对应的key分别为：message、success等
                    logger.info(JSON.toJSONString(bodymap));
                    bodymap.put("statusCode", "200");
                    return JSON.toJSONString(bodymap);//{"success":"true"}
                }
            }
        } catch (Exception e) {
            logger.error("短信发送异常：e={}", e);
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        msgMap.put("statusCode", "500");
        return JSON.toJSONString(msgMap);
    }

    private static Map<String, String> ReadResponseBodyContent(String body) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            JSONObject jsonObject = JSON.parseObject(body);
            if (null != jsonObject) {
                for (Entry<String, Object> entry : jsonObject.entrySet()) {
                    map.put(entry.getKey(), entry.getValue().toString());
                }
            }
            if ("false".equals(map.get("success"))) {
                map.put(ERRORKEY, map.get("message"));
            }
        } catch (Exception e) {
            map.put(ERRORKEY, body);
        }
        return map;
    }


    public static void main(String agrs[]) {
//        SingleSendSms app = new SingleSendSms();
//        app.sendMsg("18600000000,13800000000","{'name':'David'}");

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("code", "111111");
        paramMap.put("time", "5");
        String params = JSON.toJSONString(paramMap);

        String appKey = "23605649";
        String appSecret = "13d4b9cb8a24f5e641af3efb83854b0b";
        String signName = "泰然鲸数云计算";
        String templateCode = "SMS_41900098";
        String phoneNum = "18551022796";
//    	String params = "{\"code\":\"123456\"}";//code,time


        SingleSendSms.sendMsg(appKey, appSecret, signName, templateCode, phoneNum, params);

    }
}
