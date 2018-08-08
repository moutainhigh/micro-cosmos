package com.xunyi.cloud.wisdom.activiti.util;

import java.util.Map;

/**
 * @Author:thomas su
 * @Date: 2018/8/7 9:37
 * @Description:
 */
public class VariablesUtil {
    public static final ThreadLocal<Map>  variables = new ThreadLocal<>();
}
