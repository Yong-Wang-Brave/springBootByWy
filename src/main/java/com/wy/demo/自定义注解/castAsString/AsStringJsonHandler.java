package com.wy.demo.自定义注解.castAsString;

import org.springframework.stereotype.Component;

@Component(value = "castAsStringResponseJsonHandler")
public class AsStringJsonHandler extends AbstractJsonHandler{
    @Override
    protected Object handle(Object value) {
        return null==value?null:String.valueOf(value);
    }
}
