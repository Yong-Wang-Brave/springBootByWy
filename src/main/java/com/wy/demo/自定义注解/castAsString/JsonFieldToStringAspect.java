package com.wy.demo.自定义注解.castAsString;


import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;
import com.wy.demo.自定义注解.castAsString.CastAsString;
import net.minidev.json.JSONArray;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.math.BigDecimal;

@Aspect
@Component
public class JsonFieldToStringAspect {
    @Around("@annotation(castAsString)")
    public Object convertFieldToString(ProceedingJoinPoint joinPoint, CastAsString castAsString) throws Throwable {
        // 执行目标方法，获取返回值
        Object result = joinPoint.proceed();

        // 如果返回值是字符串类型的 JSON
        if (result instanceof String) {
            String json = (String) result;
            // 使用 JsonPath 解析 JSON
            DocumentContext documentContext = JsonPath.parse(json);
             //method11(castAsString,documentContext);
            method22(castAsString,documentContext);

            // 返回修改后的 JSON
            return documentContext.jsonString();
        }
        return result;
    }

    private void method22(CastAsString castAsString, DocumentContext documentContext) {
        String[] paths = castAsString.paths();
//            if ((null != paths && paths.length >=1)) {
//                String[] var4 =paths;
//                int var5 = paths.length;
//                for (int var6 = 0; var6 < var5; ++var6) {
//                    String path=var4[var6];
//                    documentContext.map(path,
//                            (currentValue, configuration)->{return this.handle(currentValue);},
//                            new Predicate[0]);
//                }}
        for (String path : paths) {
            documentContext.map(path,
                    (currentValue, configuration)->{return this.handle(currentValue);},
                    new Predicate[0]);
        }


    }
    public   String handle(Object fieldValue){
        String stringValue = "";
        if (( fieldValue instanceof  Double || fieldValue instanceof BigDecimal)) {
            if (String.valueOf(fieldValue).contains(".")) {
                stringValue = String.format("%.2f",fieldValue);
            }else{
                stringValue = String.format("%.0f",fieldValue);
            }
        }else{
            stringValue = String.valueOf(fieldValue);
        }
        return  stringValue;
    }

    private void method11(CastAsString castAsString, DocumentContext documentContext) {
        // 遍历注解中的每个字段路径
        for (String fieldPath : castAsString.paths()) {
            // 获取指定字段的值
            Object fieldValue = documentContext.read(fieldPath);

            // 将字段值转换为字符串
            String stringValue = "";
            if ((fieldValue instanceof JSONArray)) {
                fieldValue =   ((JSONArray) fieldValue).get(0);
            }
            if (( fieldValue instanceof  Double || fieldValue instanceof BigDecimal)) {
                if (String.valueOf(fieldValue).contains(".")) {
                    stringValue = String.format("%.2f",fieldValue);
                }else{
                    stringValue = String.format("%.0f",fieldValue);
                }
            }else{
                stringValue = String.valueOf(fieldValue);
            }
            // 更新 JSON 中的字段值
            documentContext.set(fieldPath, stringValue);
        }
    }
}