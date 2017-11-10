package com.yichen.cosmos.cloud.platform.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thomas.su on 2017/6/7.
 */
public class RequestUtil {
    private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);

    public static Map<String, String> getParamtersFromRequest(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        Map<String, String> result = new HashMap<String, String>();
        String[] keys = map.keySet().toArray(new String[0]);
        StringBuffer sb = new StringBuffer();
        for (String key : keys) {
            String val = "";
            if (map.get(key) != null && map.get(key).length > 0) {
                val = map.get(key)[0];
            }
            result.put(key, val);
            sb.append(key + "=" + val + "&");
        }
        String paramsLog = sb.substring(0, sb.length());
        if (paramsLog.length() != 0) {
            paramsLog = paramsLog.substring(0, paramsLog.length() - 1);
        }
        String addr = request.getRemoteAddr();
//        logger.info("[网关查询] [日志] [请求地址:" + addr + "] [" + paramsLog + "]");
        return result;
    }
}
