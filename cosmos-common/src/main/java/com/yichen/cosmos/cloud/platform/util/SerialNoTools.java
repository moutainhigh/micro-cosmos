package com.yichen.cosmos.cloud.platform.util;

import com.alibaba.fastjson.JSON;
import com.yichen.cosmos.cloud.platform.bean.SerialNo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.Date;

/**
 * copy TokenTools
 * 10分钟有效期
 *
 * @author thomas.su
 * @version 1.0
 * @date 2016年8月11日 下午3:02:46
 */
public class SerialNoTools {
    private static Logger logger = LoggerFactory.getLogger(SerialNoTools.class);
    private static String MD5_KEY = "_mock_client_session";

    /**
     * 判断SerialNo是否过期/正确
     *
     * @param encrypSerialNo
     * @return
     */
    public static SerialNo parseSerialNo(String encrypSerialNo) {

        SerialNo serialVO = new SerialNo();
        if (StringUtils.isEmpty(encrypSerialNo)) {
            serialVO.setIsdeline(true);
            serialVO.setSerialNo(null);
            serialVO.setAttached(null);
            serialVO.setEncrypSerialNo(null);
            return serialVO;
        }

        String params = "";
        try {
            String tempToken = new String(new BASE64Decoder().decodeBuffer(encrypSerialNo));
            params = tempToken.split(";")[0];
        } catch (Exception e) {
            logger.error("获取[serialNo]对象异常 .e={}", e);
            e.printStackTrace();
        }

        try {
            String[] resultToken = params.split("\\|");
            String ids = resultToken[1];
            String[] idArr = ids.split(",");
            String serialNoParam = idArr[0];
            String attachedParam = idArr[1];
            String lastTime = resultToken[2];
            int dateLen = Integer.parseInt(resultToken[3]);
            Date lastLoginDate = CalendarTools.parseDateTime(lastTime, "yyyy-MM-dd HH:mm:ss");
            long start = lastLoginDate.getTime();
            long currentTimeMillis = System.currentTimeMillis();
            if (dateLen <= (currentTimeMillis - start) / 60000) {
                //超时,serialNo失效
                serialVO.setIsdeline(true);
                serialVO.setEncrypSerialNo(null);
                serialVO.setSerialNo(serialNoParam);
                serialVO.setAttached(attachedParam);
                return serialVO;
            }

            serialVO.setIsdeline(false);
            serialVO.setEncrypSerialNo(encrypSerialNo);
            serialVO.setSerialNo(serialNoParam);
            serialVO.setAttached(attachedParam);

            return serialVO;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("解析token异常.e={}", e);

            serialVO.setIsdeline(true);
            serialVO.setEncrypSerialNo(null);
            return serialVO;
        }
    }

    /**
     * 通过用户的ID+Token产生时间+Token过期时间长度(分钟)加密后产生一个token字符串
     * 参数为   params 由 serialNo+","+attached (账户id和用户id通过英文逗号拼接而成)
     *
     * @param params
     * @param timeLength 有效时间长度  分钟
     * @return
     */
    public static String createSerialNo(String params, String timeLength) {
        if (StringUtils.isEmpty(params)) {
            return "";
        }

        if (!StringTools.isNumeric(timeLength)) {
            timeLength = "10";//默认10分钟
        }

        try {
            /* 要加密的字符串：14位随机数|params|登陆时间|有效期分钟|14位随机数 */
            String source = StringTools.getRandom(15) + "|" + params + "|" + CalendarTools.getNowDateTime() + "|" + timeLength + "|" + StringTools.getRandom(15);

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
            logger.error("生成SerialNo异常 .e={}", e);
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String serialNo = "thomas.su!@";
        String attached = "11111111111111";
        String encrypSerialNo = createSerialNo(serialNo + "," + attached, "10");
        System.out.println("encrypSerialNo:" + encrypSerialNo);

        SerialNo serialNoVO = parseSerialNo(encrypSerialNo);
        System.out.println("json:" + JSON.toJSONString(serialNoVO));
    }

}
