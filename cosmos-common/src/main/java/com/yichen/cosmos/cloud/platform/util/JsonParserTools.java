package com.yichen.cosmos.cloud.platform.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hzsj on 2017/5/24.
 * 解析 json字段的 公共工具
 */
public class JsonParserTools {


    private static Logger logger = LoggerFactory.getLogger(JsonParserTools.class);

    /**
     * 解析 json中value为int的值 ， 如果为空 null 则返回"无数据"
     * 否则返回对应的int
     *
     * @return
     */
    public static Object parserDouble(JSONObject jsonData, String field, int scale) {
        try {
            String value = jsonData.getString(field);
            if (StringTools.isNotEmpty(value)) {
                value = value.replace("元", "");
                return StringTools.isNotEmpty(value) ? DecimalCalculate.round(Double.valueOf(value.replace("%", "").trim()), scale) : "无数据";
            }
        } catch (Exception e) {
            logger.error("fild: {} -- 解析异常：{}, e={}", field, jsonData.get(field), e);
            e.printStackTrace();
        }
        return "无数据";
    }

    /**
     * 解析 json中value为int的值 ， 如果为空 null 则返回"无数据"
     * 否则返回对应的int
     *
     * @return
     */
    public static Object parserInteger(JSONObject jsonData, String field) {
        try {
            String value = jsonData.getString(field);
            if (StringTools.isNotEmpty(value)) {
                return StringTools.isNotEmpty(value) ? Integer.valueOf(value.trim()) : "无数据";
            }

        } catch (Exception e) {
            logger.error("fild: {} -- 解析异常：{}, e={}", field, jsonData.get(field), e);
            e.printStackTrace();
        }
        return "无数据";
    }

    /**
     * string转json 如果string非json格式返回null
     *
     * @param str
     * @return
     */
    public static JSONObject parserStringToJson(String str) {
        JSONObject object = null;
        try {
            object = JSONObject.parseObject(str);
        } catch (Exception e) {
        }
        return object;

    }


}
