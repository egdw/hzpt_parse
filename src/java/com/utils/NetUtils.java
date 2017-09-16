package com.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hdy on 2017/9/16.
 */
public class NetUtils {
    //设置超时时间
    public static int timeOut = 10000;

    public static java.lang.String get(java.lang.String url) {
        String request = request(url);
        return request;
    }

    private static String request(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        get.setHeader("Referer", "http://www.hzpt.edu.cn/");
        get.setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();//设置请求和传输超时时间
        get.setConfig(requestConfig);
        int times = 0;
        try {
            CloseableHttpResponse response = httpclient.execute(get);
            HttpEntity entity = response.getEntity();
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
            char[] c = new char[1024];
            int len = -1;
            StringBuilder sb = new StringBuilder();
            while ((len = reader.read(c)) != -1) {
                sb.append(new java.lang.String(c, 0, len));
            }
            return sb.toString();
        } catch (IOException e) {
            return null;
        } finally {
            try {
                get.releaseConnection();
                httpclient.close();
            } catch (IOException e) {
                return null;
            }
        }
    }


    public static String post(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost get = new HttpPost(url);
        get.setHeader("Referer", "http://www.hzpt.edu.cn/");
        get.setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();//设置请求和传输超时时间
        get.setConfig(requestConfig);
        int times = 0;
        try {
            CloseableHttpResponse response = httpclient.execute(get);
            HttpEntity entity = response.getEntity();
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
            char[] c = new char[1024];
            int len = -1;
            StringBuilder sb = new StringBuilder();
            while ((len = reader.read(c)) != -1) {
                sb.append(new java.lang.String(c, 0, len));
            }
            return sb.toString();
        } catch (IOException e) {
            return null;
        } finally {
            try {
                get.releaseConnection();
                httpclient.close();
            } catch (IOException e) {
                return null;
            }
        }
    }
}
