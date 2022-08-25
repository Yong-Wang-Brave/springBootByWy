package com.wy.demo.spring.Restemplate;


import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
public class RestemplatePoolConfig {

    /**
     * 让spring管理Restemplate,参数相关配置
     * @param builder
     * @return
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        //生成一个Restemplate实例
        RestTemplate restTemplate=builder.build();
        restTemplate.setRequestFactory(clientHttpRequestFactory());
        //设置编码
        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    /**
     * 客户端请求连接策略
     * @return  工厂
     */
    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(){
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClientBuilder().build());
        //连接超时时间、毫秒
        clientHttpRequestFactory.setConnectTimeout(60000);
        //读写超时时间/毫秒
        clientHttpRequestFactory.setReadTimeout(60000);
        //请求超时时间/毫秒
        clientHttpRequestFactory.setConnectionRequestTimeout(60000);
        return clientHttpRequestFactory;
    }


    /**设置http连接管理器，连接池相关配置管理
     *   客户端链接管理器
      */
    @Bean
    public HttpClientBuilder httpClientBuilder(){
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(poolingConnectionManager());
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(3,true));//失败重试次数
        return  httpClientBuilder;
    }

    /**
     * 链接线程池管理 可以keep-alive不断开链接请求，这样速度会更快
     * @return
     */
    @Bean
    public HttpClientConnectionManager poolingConnectionManager(){
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        //连接池最大连接数1000
        poolingHttpClientConnectionManager.setMaxTotal(1000);
        //同路由并发数；每个主机的并发
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(800);
        //空闲永久链接检查间隔 30000
        //可用空闲链接过期时间，重用空闲连接时会先检查是否空闲时间超过这个时间，如果超过，释放socket重新连接。
        poolingHttpClientConnectionManager.setValidateAfterInactivity(30000);
        return poolingHttpClientConnectionManager;
    }


}
