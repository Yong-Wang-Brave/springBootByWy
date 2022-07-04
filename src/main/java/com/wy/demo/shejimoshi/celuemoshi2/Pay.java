package com.wy.demo.shejimoshi.celuemoshi2;

import com.wy.demo.Exception.Exception2.Result;
import com.wy.demo.spring.DTO.InParam;

/**
 * 支付接口
 * @Author WDYin
 * @Date 2022/4/16
 **/
public interface Pay {

    /**
     * 获取支付类型
     * @return
     */
    PayTypeEnum getPayType();

    /**
     * 定义支付方法
     */
    Result pay(InParam in);
}
