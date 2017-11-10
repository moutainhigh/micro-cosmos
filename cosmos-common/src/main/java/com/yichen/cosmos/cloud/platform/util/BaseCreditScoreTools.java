package com.yichen.cosmos.cloud.platform.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yichen.cosmos.cloud.platform.bean.credit.score.BaseCreditScore;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Lizhengxian on 2017/4/12.
 */
public class BaseCreditScoreTools {

    public final static HashMap<String, Object> parseToHashMap(BaseCreditScore baseCreditScore) {
        JSONObject jsonObject = baseCreditScore.toJSONObject();
        jsonObject.remove("phone");
        jsonObject.remove("idCard");
        jsonObject.remove("name");
        jsonObject.remove("id");
        jsonObject.remove("createTime");
        HashMap<String, Object> hashMap = new HashMap<>();
        for (String key : jsonObject.keySet()) {
            JSONObject jsonObject1 = jsonObject.getJSONObject(key);
            for (Map.Entry<String, Object> entry : jsonObject1.entrySet()) {
                StringBuilder stringBuilder = new StringBuilder(key);
                char c = stringBuilder.charAt(0);
                String cs = c + "";
                String s = cs.toUpperCase();
                stringBuilder.deleteCharAt(0);
                stringBuilder.insert(0, s);
                hashMap.put(stringBuilder.toString() + "." + entry.getKey(), entry.getValue());
            }
        }
        return hashMap;
    }

    public final static TreeMap<String, Object> parseToTreeMap(BaseCreditScore baseCreditScore) {
        JSONObject jsonObject = baseCreditScore.toJSONObject();
        jsonObject.remove("phone");
        jsonObject.remove("idCard");
        jsonObject.remove("name");
        jsonObject.remove("id");
        jsonObject.remove("createTime");
        TreeMap<String, Object> treeMap = new TreeMap<>();
        //返回列表属性的字段名称
        List<String> listFieldNames = baseCreditScore.getListFieldName();
        for (String key : jsonObject.keySet()) {


            if (CollectionUtils.isNotEmpty(listFieldNames)) {
                if (listFieldNames.contains(key)) {
                    JSONArray cs = jsonObject.getJSONArray(key);
                    if (null == cs || cs.size() == 0) continue;
                    for (int i = 0; i < cs.size(); i++) {
                        JSONObject obj = cs.getJSONObject(i);
                        for (Map.Entry<String, Object> entry : obj.entrySet()) {
                            StringBuilder stringBuilder = new StringBuilder(key);
                            char c1 = stringBuilder.charAt(0);
                            String cs1 = c1 + "";
                            String s1 = cs1.toUpperCase();
                            stringBuilder.deleteCharAt(0);
                            stringBuilder.insert(0, s1);
                            System.out.println(stringBuilder.toString() + "." + entry.getKey() + i + "  " + entry.getValue());
                            treeMap.put(stringBuilder.toString() + "." + entry.getKey() + i, entry.getValue());
                        }
                    }
                    continue;
                }
            }

            JSONObject jsonObject1 = jsonObject.getJSONObject(key);
            for (Map.Entry<String, Object> entry : jsonObject1.entrySet()) {
                StringBuilder stringBuilder = new StringBuilder(key);
                char c = stringBuilder.charAt(0);
                String cs = c + "";
                String s = cs.toUpperCase();
                stringBuilder.deleteCharAt(0);
                stringBuilder.insert(0, s);
                treeMap.put(stringBuilder.toString() + "." + entry.getKey(), entry.getValue());
            }
        }
        return treeMap;
    }
}
