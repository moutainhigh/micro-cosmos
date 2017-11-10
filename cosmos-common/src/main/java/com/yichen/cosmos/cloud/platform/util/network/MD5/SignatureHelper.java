package com.yichen.cosmos.cloud.platform.util.network.MD5;

import com.yichen.cosmos.cloud.platform.util.MD5;
import com.yichen.cosmos.cloud.platform.util.SUID;
import com.yichen.cosmos.cloud.platform.util.SerialNumberTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 网关签名工具类
 */
public class SignatureHelper {

    private static Logger logger = LoggerFactory.getLogger(SignatureHelper.class);

    /**
     * 生成签名
     *
     * @param params     参数
     * @param privateKey MD5私钥
     * @return
     */
    public static String sign(Map params, String privateKey) {
        Properties properties = new Properties();

        for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            Object value = params.get(name);

            if (name == null || name.equalsIgnoreCase("sign")
                    || name.equalsIgnoreCase("sign_type")) {
                continue;
            }

            properties.setProperty(name, value.toString());

        }

        String content = getSignatureContent(properties);
        return sign(content, privateKey);
    }


    /**
     * 校验签名
     *
     * @param params 服务器端拼接的参数
     * @param key    服务器端从库中获取的key
     * @param sign   客户端传入的签名
     * @return
     */
    public static boolean verify(Map params, String key, String sign) {
        String mysign = sign(params, key);
        logger.info("mysign = {}", mysign);
        logger.info("otsign = {}", sign);
        if (mysign.equals(sign)) {
            return true;
        } else {
            return false;
        }
    }


    private static String getSignatureContent(Properties properties) {
        StringBuffer content = new StringBuffer();
        List keys = new ArrayList(properties.keySet());
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            String value = properties.getProperty(key);
            content.append((i == 0 ? "" : "&") + key + "=" + value);
        }

        return content.toString();
    }

    private static String sign(String content, String privateKey) {
        if (privateKey == null) {
            return null;
        }
        String signBefore = content + privateKey;

        logger.info("signBefore = {}", signBefore);

        //*****************************************************************
        //当收到信息，会把接受的信息写程日志
        //该文件存在于和应用服务器   启动文件同一目录下，文件名是sign_log加服务器时间
//        try {
//            FileWriter writer = new FileWriter("./sign_log" + System.currentTimeMillis() + ".txt");
//            writer.write(signBefore);
//            writer.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//*********************************************************************
        return Md5Encrypt.md5(signBefore);

    }

    public static void main(String[] args) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("partner_id", "1706081748200001");
        params.put("version", "1.0");
        params.put("sign_type", "MD5");
        params.put("service", "/gateway/serviceProvider/reviceReportToExecuteRule");
        params.put("product_id", "fd62d12bdd7c4580bfd264e1e7d80155");

//		params.put("service", "/gateway/operator/jingshu/queryReport");

//		params.put("reportId", "201702141007380001");
//		params.put("captcha", "408078");
//		params.put("service", "/gateway/operator/jingshu/collectNeedCaptcha");

//		params.put("reportId", "201702141007380001");
//		params.put("password", "940626");
//		params.put("service", "/gateway/operator/jingshu/collectNeedPassword");


        String sig = sign(params, "ca970955e848c458b2abaf6454369c94");
        System.out.println(sig);


        //生成秘钥、商户号
        System.out.println("parternPrivatekey:" + MD5.encrypt(SUID.getUUID()));
        System.out.println("parternId:" + SerialNumberTools.getMoveOrderNo().substring(2));
    }
}
