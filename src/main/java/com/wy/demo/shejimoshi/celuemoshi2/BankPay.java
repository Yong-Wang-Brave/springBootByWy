package com.wy.demo.shejimoshi.celuemoshi2;

import com.wy.demo.Exception.Exception2.Result;
import com.wy.demo.spring.DTO.InParam;
import org.springframework.stereotype.Service;

/**
 * 银行卡支付
 * @Author WDYin
 * @Date 2022/4/16
 **/
@Service
public class BankPay implements Pay {

    /**
     * 设置银行卡支付类型
     * @return
     */
    @Override
    public PayTypeEnum getPayType() {
        return PayTypeEnum.BANK;
    }

    /**
     * 银行卡支付逻辑
     */
    @Override
    public Result pay(InParam in) {
        BankPayDTO bankPayDTO = new BankPayDTO();
        bankPayDTO.setName(in.getType());
        return  Result.ok();
    }
}
