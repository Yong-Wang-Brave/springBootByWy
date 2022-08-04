package com.wy.demo.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wy.demo.Exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class JacksonUtil {

private static final ObjectMapper OBJECT_MAPPER;
//系列化时忽略字段是null的
private static final ObjectMapper OBJECT_NULL_MAPPER;

static{
    OBJECT_MAPPER=new ObjectMapper();
    OBJECT_MAPPER.registerModules(new JavaTimeModule());
    OBJECT_NULL_MAPPER=new ObjectMapper();
    OBJECT_NULL_MAPPER.registerModule(new JavaTimeModule());
    OBJECT_NULL_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
}
//字符串转java对象
public static <T> T parseObject(String jsonString ,Class<T> clazz){
    T t =null;
    try {
        t = OBJECT_MAPPER.readValue(jsonString, clazz);
    } catch (JsonProcessingException e) {
       throw new ServiceException("json字符串转换成"+clazz.toString()+"发生异常！",e);
    }catch (IOException e) {
        e.printStackTrace();
    }
return t;

}
//json字符串转java集合对象

    public static <T> List<T> parseList(String jsonString , Class<T> clazz){
        List<T> t =null;
        JavaType javaType =OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class,clazz);
        try {
            t = OBJECT_MAPPER.readValue(jsonString, javaType);
        } catch (JsonProcessingException e) {
            throw new ServiceException("json字符串转换成List"+clazz.toString()+"发生异常！",e);
        }
        return t;

    }

    //java对象转成json字符串

    public static  String tojson(Object obj){
        String jsonString;
        try {
jsonString =OBJECT_MAPPER.writeValueAsString(obj);

        } catch (JsonProcessingException e) {
            throw new ServiceException(obj.getClass().toString()+"转换成json字符串发生异常",e);
        }
        return jsonString;
    }
    //JAVA对象转成json字符串

    public static  String tojsonNonNull(Object obj){
        String jsonString;
        try {
            jsonString =OBJECT_NULL_MAPPER.writeValueAsString(obj);

        } catch (JsonProcessingException e) {
            throw new ServiceException(obj.getClass().toString()+"转换成json字符串发生异常",e);
        }
        return jsonString;
    }

}
