package com.yichen.cosmos.cloud.platform.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneTools {
    //移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    //联通：130、131、132、152、155、156、185、186
    //电信：133、153、180、189、（1349卫通）
    public static boolean isMobile(String mobile) {
        Pattern p = Pattern.compile("^((170)|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobile);
        return m.matches();
//		if(StringTools.isEmpty(mobile)){
//			return false;
//		}
//		if(mobile.length() != 11){
//			return false;
//		}
//		return true;
    }
}
