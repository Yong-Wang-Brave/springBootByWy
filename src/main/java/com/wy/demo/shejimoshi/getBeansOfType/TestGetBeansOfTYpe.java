package com.wy.demo.shejimoshi.getBeansOfType;


import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.google.common.collect.Lists;
import com.wy.demo.shejimoshi.策略模式22.Car;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TestGetBeansOfTYpe {

@GetMapping("/getByUserId")
/**
 *输出该接口实现类的所有结果。
 */
public List<Car> getCar(String userId){
        List<Car> carList = Lists.newArrayList();
        Map<String, Cars> beansOfType = SpringUtil.getBeansOfType(Cars.class);
        if (MapUtil.isNotEmpty(beansOfType)) {
            beansOfType.forEach((k,v)->{
                carList.addAll(v.getByName(userId));
            });
        }
        return carList;
    }



}
