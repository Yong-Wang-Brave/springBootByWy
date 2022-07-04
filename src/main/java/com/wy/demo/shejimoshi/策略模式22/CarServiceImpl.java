package com.wy.demo.shejimoshi.策略模式22;

import com.wy.demo.Exception.Exception2.Result;
import com.wy.demo.spring.DTO.InParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CarServiceImpl implements TypeService {

    @Override
    public Result getTypeList(InParam type) {
        log.info("获取类型列表"+type);
        Car car = new Car();
        car.setName(type.getType());
        return Result.ok(car);
    }
}
