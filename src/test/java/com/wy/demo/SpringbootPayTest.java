package com.wy.demo;

import cn.hutool.extra.spring.SpringUtil;
import com.wy.demo.config.zidingyi.StudentConfig;
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


    @Autowired // 自动装配学生配置实体
    private StudentConfig studentConfig;

    @Test
    public void test(){
        payContext.pay(PayTypeEnum.BALANCE.getCode());
    }

    @Test
    public void testStudentConfig() {
        // 输出学生配置实体信息
        System.out.println(studentConfig);
    }
    @Test
    //hutool获取配置文件的环境
    public void hutoolConfig() {
        String activeProfile = SpringUtil.getActiveProfile();
        System.out.println(activeProfile);
    }

}
