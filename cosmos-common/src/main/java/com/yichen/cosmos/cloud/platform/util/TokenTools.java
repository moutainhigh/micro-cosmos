package com.yichen.cosmos.cloud.platform.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yichen.cosmos.cloud.platform.bean.Token;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 财鲸手机客户端专用token解析工具
 *
 * @author thomas.su
 * @version 1.0
 * @date 2016年8月11日 下午3:02:46
 */
public class TokenTools {
    private static Logger logger = LoggerFactory.getLogger(TokenTools.class);
    private static String MD5_KEY = "whalewisdom";

    /**
     * 判断Token是否过期
     *
     * @param token
     * @return
     */
    public static Token parseToken(String token) {

        Token tokenVo = new Token();
        if (StringUtils.isEmpty(token)) {
            tokenVo.setIsdeline(true);
            tokenVo.setToken(null);
            tokenVo.setUserId(null);
            tokenVo.setAccount(null);
            return tokenVo;
        }

        String idParam = "";
        try {
            String tempToken = new String(new BASE64Decoder().decodeBuffer(token));
            idParam = tempToken.split(";")[0];
        } catch (Exception e) {
            logger.error("获取token对象异常 .e={}，token={}", e, token);
            e.printStackTrace();
        }

        try {
            String[] resultToken = idParam.split("\\|");
            String ids = resultToken[1];
            String[] idArr = ids.split(",");
            String account = idArr[0];
            String userId = idArr[1];
            String lastTime = resultToken[2];
            String attachedJson = resultToken[5];
            int dateLen = Integer.parseInt(resultToken[3]);
            Date lastLoginDate = CalendarTools.parseDateTime(lastTime, "yyyy-MM-dd HH:mm:ss");
            long start = lastLoginDate.getTime();
            long end = System.currentTimeMillis();
            //请参考  TokenRedisService.java
            /*if( dateLen <= (end - start)/60000 ){
    			//超时离线
    			tokenVo.setIsdeline(true);
    			tokenVo.setToken(null);
    			tokenVo.setUserId(userId);
    			tokenVo.setAccount(account);
    			return tokenVo;
    		}
    		*/
            //如果是在线状态下，用户请求后，刷新token
            tokenVo.setIsdeline(false);
            tokenVo.setToken(token);
            tokenVo.setUserId(userId);
            tokenVo.setAccount(account);

            JSONObject jsonObject = JSON.parseObject(attachedJson);
            String serialNo = jsonObject.getString("serialNo");
            Map<String, String> attachedMap = new HashMap<>();
            attachedMap.put("serialNo", serialNo);
            tokenVo.setAttachedMap(attachedMap);

            return tokenVo;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("解析token异常.e={},token={}", e, token);

            tokenVo.setIsdeline(true);
            tokenVo.setToken(null);
            return tokenVo;
        }
    }

    /**
     * 通过用户的ID+Token产生时间+Token过期时间长度(分钟)加密后产生一个token字符串
     * 参数为   idParam 由 account+","+userId (账户id和用户id通过英文逗号拼接而成)
     *
     * @param idParam
     * @return
     */
    public static String createToken(String idParam, Map<String, String> attachedMap) {
        if (StringUtils.isEmpty(idParam)) {
            return "";
        }

        try {
            String attachedJson = JSON.toJSONString(attachedMap);
			/* 要加密的字符串：14位随机数|用户ID|登陆时间|有效期分钟|14位随机数 */
            String source = StringTools.getRandom(15) + "|" + idParam + "|" + CalendarTools.getNowDateTime() + "|60|" + StringTools.getRandom(15) + "|" + attachedJson;

            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(source);
            stringBuffer.append(";");
            StringBuffer sb = new StringBuffer(stringBuffer);
            sb.append(MD5_KEY);
            String sign = new String(DigestUtils.md5DigestAsHex(sb.toString().getBytes("utf-8")));
            stringBuffer.append(new Date());
            stringBuffer.append(sign);
            return StringUtils.replaceEachRepeatedly(new BASE64Encoder().encode(stringBuffer.toString().getBytes("utf-8")), new String[]{"\b", "\r", "\n", "\t"}, new String[]{"", "", "", ""});
        } catch (Exception e) {
            logger.error("生成TOKEN异常 .e={}", e);
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) throws Exception {
        String token = "NDYxNTM4NjY5OTI3MTA5fHN0b3JtdGVhLDEzNDk4MzQ1N2M1NzQ1NjdhMTkzYTJiMjMwMzhlZDk5fDIwMTctMDQtMTQgMTI6MDU6Mjh8NjB8MTY2MjU0OTk0MTcxNjc1fHsic2VyaWFsTm8iOiJkMmQzMDgzMWEyNzI0NzQ5YmNjMmUyYWNiYzIyYjM2ZiJ9O0ZyaSBBcHIgMTQgMTI6MDU6MjggQ1NUIDIwMTdjMDhlNjQ2Y2YyOTlkZTQ2MjJlYzJmNGU4ZDhmODczNw==";
        Token tokenvo = parseToken(token);
        System.out.println(JSON.toJSONString(tokenvo));
    }

}
