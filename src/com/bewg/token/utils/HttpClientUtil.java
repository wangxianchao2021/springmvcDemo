package com.bewg.token.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.util.Map;

/*
 * 利用HttpClient进行请求的工具类
 */
public class HttpClientUtil {

    /**
     * 编码格式
     */
    private String CHARSET;

    /**
     * @param url
     * @return
     * @description GET请求
     * @author yanlei
     */
    public String doGet(String api) {

        HttpClient httpClient = null;

        HttpGet httpGet = null;

        String result = null;

        try {

            httpClient = new SSLClient();

            httpGet = new HttpGet(api);
            httpGet.addHeader("Content-Type", "application/json");

            HttpResponse response = httpClient.execute(httpGet);
            if (response != null) {

                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {

                    result = EntityUtils.toString(resEntity, CHARSET);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * @param url
     * @param map
     * @return
     * @description POST请求
     * @author yanlei
     */
    public String doPost(String api, Map<String, Object> map) {

        HttpClient httpClient = null;

        HttpPost httpPost = null;

        String result = null;

        try {

            httpClient = new SSLClient();

            httpPost = new HttpPost(api);
            httpPost.addHeader("Content-Type", "application/json");

            String json = JSON.toJSONString(map);
            System.out.println(json);

            StringEntity requestEntity = new StringEntity(json, CHARSET);

            httpPost.setEntity(requestEntity);

            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {

                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {

                    result = EntityUtils.toString(resEntity, CHARSET);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public String doPost(String api, Map<String, Object> map, File file) {

        HttpClient httpClient = null;

        HttpPost httpPost = null;

        String result = null;

        try {

            httpClient = new SSLClient();
            httpPost = new HttpPost(api);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//            httpPost.addHeader("Content-Type", "application/json");
//            String json = JSON.toJSONString(map);
//            StringEntity requestEntity = new StringEntity(json, CHARSET);
            if (map != null) {
                for (String key : map.keySet()) {
                    builder.addPart(key,new StringBody(map.get(key).toString()));
//                    new StringBody(map.get(key).toString(), ContentType.MULTIPART_FORM_DATA);
//                    StringBody stringBody1 = ;
                }
            }
//			httpPost.setEntity(requestEntity);
            if (file != null && file.exists()) {
                builder.addPart("media", new FileBody(file));
            }
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, CHARSET);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public String getCHARSET() {
        return CHARSET;
    }

    public void setCHARSET(String cHARSET) {
        CHARSET = cHARSET;
    }
} 