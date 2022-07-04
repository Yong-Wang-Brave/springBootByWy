package com.wy.demo.shejimoshi.celuemoshi2;

import com.wy.demo.Exception.Exception2.Result;
import com.wy.demo.spring.DTO.InParam;
import org.springframework.stereotype.Service;

/**
 * 账户余额支付
 * @Author WDYin
 * @Date 2022/4/16
 **/
@Service
public class BalancePay implements Pay {

    /**
     * 设置账户余额类型
     * @return
     */
    @Override
    public PayTypeEnum getPayType() {
        return PayTypeEnum.BALANCE;
    }

    /**
     * 账户余额支付逻辑
     */
    @Override
    public Result pay(InParam in) {
        System.out.println(getPayType().getValue());
        BalancePayDTO balancePayDTO = new BalancePayDTO();
        balancePayDTO.setName(in.getType());
        return Result.ok(balancePayDTO);
    }
}
