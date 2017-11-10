package com.yichen.cosmos.cloud.platform.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by thomas.su on 2016/11/22.
 */
public class SearchUtils {

    public static String searchValue(JSONArray array, String param) {
        Iterator<Object> iter = array.iterator();
        while (iter.hasNext()) {
            JSONObject obj = (JSONObject) iter.next();
            String code = obj.getString("code");
            if (param.equals(code)) {
                String value = obj.getString("value");
                if (StringUtils.isEmpty(value)) {
                    continue;
                }
                return value;
            }
        }
        return null;
    }

    /**
     * @param array
     * @param param
     * @return List<String>
     * @Title searchValueArray
     * @Description 查找一个参数值数组  add by xab 2017-04-11
     */
    public static List<String> searchValueArray(JSONArray array, String param) {
        Iterator<Object> iter = array.iterator();
        List<String> values = new ArrayList<String>();
        while (iter.hasNext()) {
            Object arrayObj = iter.next();
            if (arrayObj == null || StringUtils.isEmpty(arrayObj.toString())) {
                continue;
            }
            JSONArray jsonArray = JSON.parseArray(arrayObj.toString());
            String value = searchValue(jsonArray, param);
            if (StringUtils.isEmpty(value)) {
                continue;
            }
            values.add(value);
        }
        return values;
    }
}
