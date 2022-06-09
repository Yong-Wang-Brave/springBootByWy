/*
package com.wy.demo.lightspot.function;

import com.wy.demo.java8.VUtils;
import com.wy.demo.自定义注解.demo1.MyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAnnotationApplicationTests {
    @Resource
    private MyService myService;

    @Test
   public void testAOP() {
        myService.doService();
    }

    @Test
    public void isTrue1(){
        VUtils.isTure(false).throwMessage("俺要抛出异常了");
    }
    @Test
    public void isTrue2(){
        VUtils.isTure(true).throwMessage("参数为true，俺要抛出异常了");
    }

    @Test
    public void isTrue3(){
        VUtils.isTureOrFalse(true).trueOrFalseHandle(
                ()->{
                    System.out.println("true");
                },
                ()->{
                    System.out.println("false");
                }
        );
    }


    @Test
    public void isBlankOrNoBlank(){
        VUtils.isBlankOrNoBlank("Hello")
                .presentOrElseHandle(System.out::println,()-> System.out.println("空"));
    }

}
*/
