package com.wy.demo;

import com.wy.demo.shejimoshi.celuemoshi2.PayContext;
import com.wy.demo.shejimoshi.celuemoshi2.PayTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author WDYin
 * @Date 2021/12/2
 * @Description
 **/
@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class SpringbootPayTest {

    @Autowired
    private PayContext payContext;

    @Test
    public void test(){
        payContext.pay(PayTypeEnum.BALANCE.getCode());
    }




}
