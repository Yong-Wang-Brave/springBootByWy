package com.wy.demo.shejimoshi.策略模式22;

import com.wy.demo.Exception.Exception2.Result;
import com.wy.demo.spring.DTO.InParam;

public interface TypeService {

    Result getTypeList(InParam type);
}
