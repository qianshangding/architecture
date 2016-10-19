package com.architecture.utils;


import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 *
 */
public class HttpHelper {

    private static final CloseableHttpClient httpClient;

    public static final String CHARSET = "UTF-8";

    static {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000).build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }

    public static String post(String url, String data, String contentType) {
        HttpPost exec = new HttpPost(url);
        HttpEntity entity = new StringEntity(data, "utf-8");
        exec.setEntity(entity);
        exec.setHeader("contentType", contentType);
        return post(exec);
    }

    public static String restPost(String url, Object object) {
        try {
            HttpPost exec = new HttpPost(url);
            HttpEntity entity = new StringEntity(JSON.toJSONString(object), "utf-8");
            exec.setEntity(entity);
            exec.setHeader("from", "java");
            exec.setHeader("Content-Type", "application/json;charset=utf-8");
            return post(exec);
        } catch (Exception e) {
            throw new RuntimeException("HttpHelper error :", e);
        }

    }

    /**
     * @param httpPost:httpPost
     * @return: remote response
     */
    private static String post(HttpPost httpPost) {

        HttpEntity respEntity = null;
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }

            String result = null;
            respEntity = response.getEntity();
            if (respEntity != null) {
                result = EntityUtils.toString(respEntity, "utf-8");
//                logger.info("reveive message:\t" + result);
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException("HttpHelper error :", e);
        } finally {
            if (respEntity != null) {
                try {
                    EntityUtils.consume(respEntity);
                } catch (IOException e) {
                    throw new RuntimeException("HttpHelper error :", e);
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    throw new RuntimeException("HttpHelper error :", e);
                }
            }
        }
    }
}
