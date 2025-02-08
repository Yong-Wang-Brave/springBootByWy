package com.wy.demo.自定义注解.castAsString;

import com.jayway.jsonpath.DocumentContext;

import java.lang.reflect.Method;

public interface IJsonHandler {
    void parse(DocumentContext documentContext, String[]  paths, Method method);
}
