package com.sun.task.util;


import com.alibaba.fastjson.TypeReference;
import lombok.extern.log4j.Log4j;
import okhttp3.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Log4j
public class OkHttpUtil implements InitializingBean {

    private static MediaType JSON = MediaType.parse("application/json");
    private static MediaType FORM = MediaType.parse("application/x-www-form-urlencoded");
    private OkHttpClient client;

    public String postByJson(String url, String param) {

        RequestBody body = RequestBody.create(JSON, param);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return execute(request);

    }

    public void setClient(OkHttpClient client) {
        this.client = client;
    }

    protected String execute(Request request) {
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException("请求异常");
        }
    }

    public String postFomm(String url, Map<String, String> param) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        return execute(request);
    }

    @Override
    public void afterPropertiesSet() {
        if (client == null) {
            client = new OkHttpClient();
        }
    }

    public String get(String url) {
        Request request = new Request.Builder().url(url).get().build();
        return execute(request);

    }

    /**
     * 调用微信接口
     *
     * @param authAccessToken
     * @param url
     * @param reqMap
     * @param method
     * @return
     */
    public Map<String, Object> requestAuthWechatAPI(String authAccessToken, String url, Map<String, Object> reqMap, String method) {
        String reqUrl = url + authAccessToken;
        String req = null;
        if (!CollectionUtils.isEmpty(reqMap)) {
            if ("get".equals(method)) {
                for (String key : reqMap.keySet()) {
                    reqUrl += "&" + key + "=" + reqMap.get(key);
                }
            } else {
                req = com.alibaba.fastjson.JSON.toJSONString(reqMap);
            }
        }
        Map<String, Object> wechatRetMap = requestByClient(reqUrl, method, req);

        // 返回结果处理
        if (!CollectionUtils.isEmpty(wechatRetMap) && isSuccessErrcode(objectToString(wechatRetMap.get("errcode")))) {
            return wechatRetMap;
        } else {
            throw new RuntimeException("返回结果异常" + com.alibaba.fastjson.JSON.toJSONString(wechatRetMap));
        }
    }

    public Map<String, Object> requestByClient(String url, String method, String data) {

        String result = null;
        if ("POST".equalsIgnoreCase(method)) {
            result = this.postByJson(url, data);
        } else {
            result = this.get(url);
        }
        if (!StringUtils.isEmpty(result)) {
            return com.alibaba.fastjson.JSON.parseObject(result, new TypeReference<Map<String, Object>>() {
            });
        } else {
            return null;
        }
    }

    private static boolean isSuccessErrcode(String errcode) {
        if ("".equals(errcode) || "0".equals(errcode)) return true;

        return false;

    }

    /**
     * object转换String 为空返回 ""
     *
     * @param object
     * @return
     */
    private static String objectToString(Object object) {
        if (null == object) {
            return "";
        }

        return String.valueOf(object);
    }
}
