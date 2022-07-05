package com.wy.demo.shejimoshi.策略模式22;

import cn.hutool.core.collection.CollectionUtil;
import com.wy.demo.Exception.Exception2.Result;
import com.wy.demo.Exception.Exception2.ServiceeException;
import com.wy.demo.Tools.StringUtil;
import com.wy.demo.spring.DTO.InParam;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@Log4j2
public class TestCelueController {

    @Autowired
    TypeFactory typeCelue;
    @Autowired
    SpringContextHelper2 type2;

    @PostMapping("/findByType")
    Result testCelue(@RequestBody(required = false) InParam in) {
        ArrayList<String> objects = new ArrayList<>();
        if (StringUtil.isNullOrEmpty(in.getType())) {
objects.add("类型不能为空");
        }
        if (StringUtil.isNullOrEmpty(in.getName())) {
            objects.add("名字不能为空");
        }

        if (CollectionUtil.isNotEmpty(objects)) {
            throw  new ServiceeException(StringUtils.join(objects,"!"));
        }
        Object bean = type2.getBean("bikeImpl");
        return Result.ok(typeCelue.getType(in.getType()).getTypeList(in));
    }
}
