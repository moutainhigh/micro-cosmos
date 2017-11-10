package com.yichen.cosmos.cloud.platform.util;

import com.github.pagehelper.Page;
import com.yichen.cosmos.cloud.platform.bean.Response;
import com.yichen.cosmos.cloud.platform.bean.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页查询返回查询结果
 * http://www.cnblogs.com/xiaoxinwt/p/5329840.html
 * Created by thomas on 2017/4/8 10:58.
 */
public class JsonPageTools {

    //只能应用到单表查询
    public static String jsonPage(Page page) {
        Map<String, Object> dataMap = new HashMap<>(2);

        dataMap.put("rows", page.getResult());
        dataMap.put("total", page.getTotal());

        return new Response.Builder().code(ResponseStatus.RESPONSE_CODE_200)
                .msg(ResponseStatus.RESPONSE_MSG_200)
                .data(dataMap)
                .build().toStringIgnoreNull();
    }

    public static String jsonPage(long total, Object result) {
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("rows", result);
        dataMap.put("total", total);

        return new Response.Builder().code(ResponseStatus.RESPONSE_CODE_200)
                .msg(ResponseStatus.RESPONSE_MSG_200)
                .data(dataMap)
                .build().toString();
    }
}
