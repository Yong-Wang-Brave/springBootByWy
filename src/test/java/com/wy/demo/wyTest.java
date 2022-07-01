package com.wy.demo;


import com.wy.demo.other.jianrong202205525.MyMvcConfigurer;
import com.wy.demo.spring.proxy.IUserDao;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ImportResource(value="classpath:application.properties")
@SpringBootTest(classes = {DemoApplication.class})
@Log4j2
 public class wyTest {
    @Resource
    IUserDao userDao;
    @Test
    public void test_IUserDao() {
       // BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring-config.xml");
        //IUserDao userDao = beanFactory.getBean("userDao", IUserDao.class);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyMvcConfigurer.class);
        IUserDao userDao = context.getBean("ProxyBeanFactory", IUserDao.class);
        String res = userDao.queryUserInfo();
        log.info("测试结果：{}", res);
    }


}
