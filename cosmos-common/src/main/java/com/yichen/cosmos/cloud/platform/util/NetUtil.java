package com.yichen.cosmos.cloud.platform.util;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hzlq on 2016/7/22.
 */
public class NetUtil {

    public static String getContextPath(HttpServletRequest request) {
        String url = request.getRequestURL().substring(0, getCharacterPosition(request.getRequestURL().toString()));
        String uri = request.getContextPath();
        return url + uri + "/";
    }

    private static int getCharacterPosition(String string) {
        //这里是获取"/"符号的位置
        Matcher slashMatcher = Pattern.compile("/").matcher(string);
        int mIdx = 0;
        while (slashMatcher.find()) {
            mIdx++;
            //当"/"符号第三次出现的位置
            if (mIdx == 3) {
                break;
            }
        }
        return slashMatcher.start();
    }


}
