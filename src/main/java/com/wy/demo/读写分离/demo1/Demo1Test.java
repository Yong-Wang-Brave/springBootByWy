package com.wy.demo.读写分离.demo1;

import com.wy.demo.读写分离.DsType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class Demo1Test {
    @Autowired
    User1Service userService;

    @Before
    public void before() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig.class);
        context.refresh();
        this.userService = context.getBean(User1Service.class);
    }

    @Test
    public void test1() {
        System.out.println(this.userService.getUserNameById(1, DsType.MASTER));
        System.out.println(this.userService.getUserNameById(1, DsType.SLAVE));
    }

    @Test
    public void test2() {
        long id = System.currentTimeMillis();
        System.out.println(id);
        this.userService.insert(id, "张三");
    }

    @Test
    public void test3() throws SQLException {
        this.userService.test1(1, DsType.SLAVE);
    }
}
