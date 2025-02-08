package com.wy.demo.自定义注解.castAsString;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.Predicate;

import java.lang.reflect.Method;


public abstract class AbstractJsonHandler implements  IJsonHandler{
    public AbstractJsonHandler(){}
    @Override
    public void parse(DocumentContext documentContext, String[] paths, Method method){
        if ((null != paths && paths.length >=1)) {
            String[] var4 =paths;
            int var5 = paths.length;
            for (int var6 = 0; var6 < var5; ++var6) {
                String path=var4[var6];
                documentContext.map(path,
                        (currentValue, configuration)->{return this.handle(currentValue);},
                        new Predicate[0]);
            }
        }else{

        }
    }
    protected abstract Object handle(Object object);
}
