package com.yichen.cosmos.cloud.platform.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;


public class HttpClientUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    public static String doGet(String uri) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = null;
        String responseBody = "";
        try {
            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            responseBody = EntityUtils.toString(entity, "UTF-8");
            EntityUtils.consume(entity);
            return responseBody;
        } catch (UnsupportedEncodingException e) {
            logHttpGetException(httpGet, e);
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            logHttpGetException(httpGet, e);
            e.printStackTrace();
        } catch (ParseException | IOException e) {
            logHttpGetException(httpGet, e);
            e.printStackTrace();
        }
        return responseBody;
    }

    public static String doPost(String uri, List<NameValuePair> valuePairs) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri);
        String responseBody = "";
        CloseableHttpResponse response = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(valuePairs, "UTF-8"));
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            responseBody = EntityUtils.toString(entity, "UTF-8");
            EntityUtils.consume(entity);
            return responseBody;
        } catch (UnsupportedEncodingException e) {
            logHttpPostException(httpPost, e);
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            logHttpPostException(httpPost, e);
            e.printStackTrace();
        } catch (ParseException | IOException e) {
            logHttpPostException(httpPost, e);
            e.printStackTrace();
        }
        return responseBody;
    }

    private static void logHttpGetException(HttpGet method, Exception e) {
        String logMsg = null;
        try {
            logMsg = String.format("HTTP调用失败: %s %s%s: %s",
                    method.getMethod(),
                    method.getURI().toString(),
                    method.getParams() == null ? "" : "?" + method.getParams(),
                    e.getLocalizedMessage());
        } catch (Exception ex) {
            logger.error("Log exception...", ex);
            e.printStackTrace();
        }
        logger.error(logMsg, e);
    }

    private static void logHttpPostException(HttpPost method, Exception e) {
        String logMsg = null;
        try {
            logMsg = String.format("HTTP调用失败: %s %s%s: %s",
                    method.getMethod(),
                    method.getURI().toString(),
                    method.getEntity() == null ? "" : "?" + method.getEntity(),
                    e.getLocalizedMessage());
        } catch (Exception ex) {
            logger.error("Log exception...", ex);
            e.printStackTrace();
        }
        logger.error(logMsg, e);
    }
}
