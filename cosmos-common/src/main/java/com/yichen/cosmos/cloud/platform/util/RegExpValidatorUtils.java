package com.yichen.cosmos.cloud.platform.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by thomas on 2017/3/2.
 */
public class RegExpValidatorUtils {
    /**
     * 验证邮箱
     *
     * @return 如果是符合的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     * @str 待验证的字符串
     */
    public static boolean isEmail(String str) {
        String regex = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        return match(regex, str);
    }

    /**
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
