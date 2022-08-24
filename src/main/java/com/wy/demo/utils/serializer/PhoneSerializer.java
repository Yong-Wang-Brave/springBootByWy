package com.wy.demo.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.wy.demo.Tools.StringUtil;
import com.wy.demo.utils.DesensitizationUtil;

import java.io.IOException;

public class PhoneSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        //如果是正紧信息，进行隐藏
        if (!StringUtil.isNullOrEmpty(value)) {
            gen.writeString(DesensitizationUtil.around(value,1,1));
        }
        else{gen.writeString(value);}
    }
}
