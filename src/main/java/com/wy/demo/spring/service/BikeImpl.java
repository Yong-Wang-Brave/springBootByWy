package com.wy.demo.spring.service;


import com.wy.demo.Exception.Exception2.Result;
import com.wy.demo.spring.DTO.InParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BikeImpl implements  TypeService{
    @Override
    public Result getTypeList(InParam type) {
        log.info("获取类型列表"+type);
        return Result.ok("自行车") ;
    }
}
