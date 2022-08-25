package com.wy.demo.spring.Restemplate;


import cn.hutool.core.collection.CollUtil;
import com.alibaba.druid.support.json.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class RestClient {
    @Resource
    private RestTemplate restTemplate;


    public String get(String url, Map<String, ?> param) {
        return get(url, param, String.class);
    }

    public String get(String url) {
        return get(url, String.class);
    }

    public <T> T get(String url, Map<String, ?> param, Class<T> clazz) {
        if (param == null) {
            return restTemplate.getForEntity(url, clazz).getBody();
        }
        return restTemplate.getForEntity(expandUrl(url, param.keySet()), clazz, param).getBody();
    }

    public <T> T get(String url, Map<String, ?> param, Class<T> clazz, Map<String, Object> headerMap) {
        HttpHeaders headers = new HttpHeaders();
        if (!headerMap.isEmpty()) {
            for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
                headers.add(entry.getKey(), (String) entry.getValue());
            }
        }
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> requestEntity = new HttpEntity<>(null, headers);
        if (param == null) {
            return restTemplate.exchange(url, HttpMethod.GET, requestEntity, clazz).getBody();
        }
        return restTemplate.exchange(expandUrl(url, param.keySet()), HttpMethod.GET, requestEntity, clazz, param).getBody();
    }

    private String expandUrl(String url, Set<?> keys) {

        //问号特殊字符
        final String questionSign = "?";
        StringBuilder sb = new StringBuilder(url);
        if (url.contains(questionSign)) {
            sb.append('&');
        } else {
            sb.append('?');
        }
        for (Object key : keys) {
            sb.append(key).append('=').append('{').append(key).append('}').append('&');
        }
        String requestUrl = sb.deleteCharAt(sb.length() - 1).toString();
        log.info("\n restTemplate request expandUrl:{}", requestUrl);
        return requestUrl;
    }

    public <T> T get(String url, Class<T> clazz, Map<String, Object> headerMap) {
        HttpHeaders headers = new HttpHeaders();
        if (!headerMap.isEmpty()) {
            for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
                headers.add(entry.getKey(), (String) entry.getValue());
            }
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, clazz).getBody();
    }

    public <T> T get(String url, Class<T> clazz) {
        return restTemplate.getForEntity(url, clazz).getBody();
    }

    public <T> T postJson(String url, Class<T> clazz) {
        return postJson(null, url, null, clazz, null);
    }

    public <T> T postJson(String url, Object param, Class<T> clazz) {
        return postJson(null, url, param, clazz, null);
    }

    public <T> T postJson(Map<String, Object> hearMap, String url, Object param, Class<T> clazz) {
        return postJson(hearMap, url, param, clazz, null);
    }

    public <T> T postJson(String url, Object param, Class<T> clazz, Map<String, ?> urlParamMap) {
        return postJson(null, url, param, clazz, urlParamMap);
    }

    public <T> T postJson(Map<String, Object> headerMap, String url, Object param, Class<T> clazz, Map<String, ?> urlParamMap) {
        return post(headerMap, url, JSONUtils.toJSONString(param), clazz, urlParamMap, MediaType.APPLICATION_JSON);
    }


    public <T> T postFromUrlencoded(String url,Class<T> clazz){
        return postFromUrlencoded(null,url,null,clazz,null);
    }
    public <T> T postFromUrlencoded(String url,Object param,Class<T> clazz){
        return postFromUrlencoded(null,url,param,clazz,null);
    }
    public <T> T postFromUrlencoded(Map<String, Object> headerMap,String url,Object param,Class<T> clazz){
        return postFromUrlencoded(headerMap,url,param,clazz,null);
    }
    public <T> T postFromUrlencoded(String url,Object param,Class<T> clazz,Map<String, ?> urlParamMap){
        return postFromUrlencoded(null,url,param,clazz,urlParamMap);
    }
    public <T> T postFromUrlencoded(Map<String, Object> headerMap,String url,Object param,Class<T> clazz,Map<String, ?> urlParamMap){
        return post(headerMap,url,param,clazz,urlParamMap,MediaType.APPLICATION_FORM_URLENCODED);
    }

    public <T> T postMulFileFormdata(Map<String, Object> headerMap, String url, MultiValueMap<String,Object> multiValueMap, Class<T> clazz, Map<String, ?> urlParamMap){
        return post(headerMap,url,multiValueMap,clazz,urlParamMap,MediaType.APPLICATION_JSON);
    }

    public <T> T post(Map<String, Object> headerMap, String url, Object param, Class<T> clazz, Map<String, ?> urlParamMap, MediaType mediaType) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return post(url, param, clazz, headers, headerMap, urlParamMap);

    }

    public <T> T post(String url, Object param, Class<T> clazz, HttpHeaders headers, Map<String, Object> headerMap, Map<String, ?> urlParamMap) {
        if (CollUtil.isNotEmpty(headerMap)) {
            for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
                headers.add(entry.getKey(), (String) entry.getValue());
            }
        }
        HttpEntity<Object> requestEntity = new HttpEntity<>(param, headers);
        if (CollUtil.isNotEmpty(urlParamMap)) {
            return restTemplate.postForEntity(url, requestEntity, clazz).getBody();
        }
        return restTemplate.postForEntity(expandUrl(url, urlParamMap.keySet()), requestEntity, clazz, urlParamMap).getBody();
    }

}
