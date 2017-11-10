package com.yichen.cosmos.cloud.platform.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTools {

    public static boolean isNotNull(String s) {
        return (s != null) ? true : false;
    }

    public static boolean isNotEmpty(String s) {
        if (isNotNull(s)) {
            return "".equals(s.trim()) ? false : true;
        }
        return false;
    }


    public static boolean isEmpty(String s) {
        if (isNotNull(s)) {
            return "".equals(s.trim()) ? true : false;
        }
        return true;
    }

    public static boolean isAllEmpty(String... strings) {
        boolean isAllEmpty = true;
        for (String s : strings) {
            if (isNotEmpty(s)) {
                isAllEmpty = false;
            }
        }
        return isAllEmpty;
    }

    public static boolean hasEmpty(String... strings) {
        for (String s : strings) {
            if (s == null || s.equals("")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 去除网页文档中的标签,不包括&nbsp;
     *
     * @param s
     * @return
     */
    public static String delHtmlTag(String s) {
        /** 定义script的正则表达式 **/
        String regScript = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        /** 定义style的正则表达式 **/
        String regStyle = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        /** 定义HTML标签的正则表达式 **/
        String regHtml = "<[^>]+>";
        /** 定义空格回车换行符 **/
        String regSpace = "\\s*|\t|\r|\n";

        Pattern pScript = Pattern.compile(regScript, Pattern.CASE_INSENSITIVE);
        Matcher mScript = pScript.matcher(s);
        s = mScript.replaceAll("");

        Pattern pStyle = Pattern.compile(regStyle, Pattern.CASE_INSENSITIVE);
        Matcher mStyle = pStyle.matcher(s);
        s = mStyle.replaceAll("");

        Pattern pHtml = Pattern.compile(regHtml, Pattern.CASE_INSENSITIVE);
        Matcher mHtml = pHtml.matcher(s);
        s = mHtml.replaceAll("");

        Pattern pSpace = Pattern.compile(regSpace, Pattern.CASE_INSENSITIVE);
        Matcher mSpace = pSpace.matcher(s);
        s = mSpace.replaceAll("");
        return s.trim();
    }

    /**
     * 去除网页文档中的标签,包括&nbsp;
     *
     * @param s
     * @return
     */
    public static String delHtmlTagBlank(String s) {
        s = delHtmlTag(s);
        s = s.replaceAll("&nbsp;", "");
        return s;
    }

    /**
     * 判断是否是邮箱
     */
    public static boolean isEmail(String email) {
        if (!isNotEmpty(email)) {
            return false;
        }
        Pattern pattern = Pattern
                .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher isNum = pattern.matcher(email);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否是手机号格式
     *
     * @param tele
     * @return
     */
    public static boolean isTele(String tele) {
        if (!isNotEmpty(tele)) {
            return false;
        }
        Pattern pattern = Pattern.compile("1[3|5|7|8][0-9]{9}");
        Matcher isNum = pattern.matcher(tele);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 用正则表达式 判断是否数字
     *
     * @return
     */
    public static boolean isNumeric(String str) {
        if (!isNotEmpty(str)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("[0-9]*");
            return pattern.matcher(str).matches();
        }
    }

    /**
     * 判断是否是数字
     *
     * @param str
     * @param negative 是否允许负数
     * @param positive 是否允许正数
     * @param isfloat  是否允许小数
     * @return
     */
    public static boolean isNumberic(String str, boolean negative,
                                     boolean positive, boolean isfloat) {
        // 判断是否为正整数 ^\\d+$
        // 判断是否为正小数 ^\\d+\\.\\d+$
        // 判断是否为负整数 -\\d+$
        // 判断是否为负小数 ^-\\d+\\.\\d+$

        if (!isNotEmpty(str)) {
            return false;
        }

        Pattern pattern = null;
        boolean flag = false;

        if (negative) {
            pattern = Pattern.compile("-\\d+$");
            flag = pattern.matcher(str).matches();
            if (flag) {
                return true;
            }
            if (isfloat) {
                pattern = Pattern.compile("^-\\d+\\.\\d+$");
                flag = pattern.matcher(str).matches();
                if (flag) {
                    return true;
                }
            }
        }
        if (positive) {
            pattern = Pattern.compile("^\\d+$");
            flag = pattern.matcher(str).matches();
            if (flag) {
                return true;
            }
            if (isfloat) {
                pattern = Pattern.compile("^\\d+\\.\\d+$");
                flag = pattern.matcher(str).matches();
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 数字金额大写转换 要用到正则表达式
     */
    public static String digitUppercase(double n) {

        String fraction[] = {"角", "分"};

        String digit[] = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};

        String unit[][] = {{"元", "万", "亿"},

                {"", "拾", "佰", "仟"}};

        String head = n < 0 ? "负" : "";

        n = Math.abs(n);

        String s = "";

        for (int i = 0; i < fraction.length; i++) {

            s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i])
                    .replaceAll("(零.)+", "");

        }
        if (s.length() < 1) {
            s = "整";
        }

        int integerPart = (int) Math.floor(n);
        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p = "";
            for (int j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[integerPart % 10] + unit[1][j] + p;
                integerPart = integerPart / 10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i]
                    + s;
        }
        return head
                + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "")
                .replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }

    /**
     * 校验是否含有非法字符
     * 包含非法字符时会返回非法的字符
     */
    public static String illegalChar(String str) {
        if (!isNotEmpty(str)) {
            return "";
        }
        String[] IIIEGAL_CHAR = {"+", "%", "^", "<", ">", "[", "]", "{", "}",
                "/", "\\", "?", "&", "(", ")"};
        for (int i = 0; i < IIIEGAL_CHAR.length; i++) {
            int index = str.indexOf(IIIEGAL_CHAR[i]);
            if (index > -1) {
                return IIIEGAL_CHAR[i];
            }
        }
        return "";
    }

    /**
     * 去除空格
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 去除换行和制表，不去除空格
     *
     * @param str
     * @return
     */
    public static String replaceTabEnter(String str) {
//		    \n 回车
//		    \t 水平制表符
//		    \s 空格
//		    \r 换行
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /***
     * 产生一个纯数字随机整数
     *
     * @param point 整数位数
     * @return
     */
    public static String getRandom(int point) {
        String result = String.valueOf(Math.random());
        String f = "#####0";
        if (point > 0) {
            f = "";
            for (int i = 0; i < point - 1; i++) {
                f += "#";
            }
            f += "0";
        }
        BigDecimal rand = new BigDecimal(result);
        BigDecimal one = new BigDecimal(1);
        double d = rand.divide(one, point, BigDecimal.ROUND_HALF_UP).doubleValue();
        DecimalFormat df = new DecimalFormat(f);
        String t = df.format(d * Math.pow(10, point));
        return t;
    }

    /**
     * 身份证号合法性验证
     *
     * @param idCard
     * @return
     */
    public static boolean isIdCard(String idCard) {
        if (!isNotEmpty(idCard)) {
            return false;
        }
        String regx = "^(\\d{15})$|^(\\d{17}[a-z0-9A-Z])$";
        Pattern p = Pattern.compile(regx);
        Matcher m = p.matcher(idCard);
        boolean flag = m.matches();
        if (!flag) {
            return flag;
        }
        Integer prefix = Integer.parseInt(idCard.substring(0, 2));
        Map<Integer, String> addr = new HashMap<Integer, String>();
        addr.put(11, "北京");
        addr.put(12, "天津");
        addr.put(13, "河北");
        addr.put(14, "山西");
        addr.put(15, "内蒙古");
        addr.put(21, "辽宁");
        addr.put(22, "吉林");
        addr.put(23, "黑龙江");
        addr.put(31, "上海");
        addr.put(32, "江苏");
        addr.put(33, "浙江");
        addr.put(34, "安徽");
        addr.put(35, "福建");
        addr.put(36, "江西");
        addr.put(37, "山东");
        addr.put(41, "河南");
        addr.put(42, "湖北");
        addr.put(43, "湖南");
        addr.put(44, "广东");
        addr.put(45, "广西");
        addr.put(46, "海南");
        addr.put(50, "重庆");
        addr.put(51, "四川");
        addr.put(52, "贵州");
        addr.put(53, "云南");
        addr.put(54, "西藏");
        addr.put(61, "陕西");
        addr.put(62, "甘肃");
        addr.put(63, "青海");
        addr.put(64, "宁夏");
        addr.put(65, "新疆");
        addr.put(71, "台湾");
        addr.put(81, "香港");
        addr.put(82, "澳门");
        addr.put(91, "国外");

        if (StringTools.isNotEmpty(addr.get(prefix))) {
            return true;
        }
        return false;
    }

    /**
     * 银行卡号简单的验证，只做全数字验证
     *
     * @param s
     * @return
     */
    public static boolean validBankNum(String s) {
        boolean flag = isNumeric(s);
        if (!flag) {
            return false;
        }
        if (s.length() < 16 || s.length() > 19) {
            return false;
        }
        return true;
    }

    /**
     * @param d
     * @return
     */
    public static double parseDouble(double d) {
        DecimalFormat df = new DecimalFormat("#.00");
        d = Double.parseDouble(df.format(d));
        return d;
    }

    public static String firstToLowerCase(String str) {
        StringBuilder sb = new StringBuilder(str);
        Character c = sb.charAt(0);
        String firstChar = c.toString().toLowerCase();
        sb.deleteCharAt(0);
        sb.insert(0, firstChar);
        return sb.toString();
    }


    public static String firstToUpperCase(String str) {
        StringBuilder sb = new StringBuilder(str);
        Character c = sb.charAt(0);
        String firstChar = c.toString().toUpperCase();
        sb.deleteCharAt(0);
        sb.insert(0, firstChar);
        return sb.toString();
    }

    public static List<String> findByChars(String content, char start, char end) {
        List list = new ArrayList();
        char[] chars = content.toCharArray();
        int f = 0;
        int e = 0;
        boolean flag = false;
        for (int i = 0; i < chars.length; i++) {
            if (start == chars[i]) {
                f = i;
                flag = true;
            }
            if ((end == chars[i]) && (flag)) {
                char[] temp = new char[chars.length];
                e = i;
                System.arraycopy(chars, f + 1, temp, 0, e - f - 1);
                list.add(new String(temp).trim());
                flag = false;
            }
        }
        return list;
    }

}
