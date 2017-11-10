package com.yichen.cosmos.cloud.platform.util;

import org.apache.http.conn.ssl.TrustStrategy;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/***
 * @author lwf
 * @ClassName: AnyTrustStrategy
 * @Description: 信任任何密钥的策略
 * @date 2017年3月1日 下午12:39:06
 */
public class AnyTrustStrategy implements TrustStrategy {
    @Override
    public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        return true;
    }
}