package com.wy.demo.BaseJunit4Test;

import com.wy.demo.DemoApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ImportResource(value={"classpath:application.properties"})
@SpringBootTest(classes= {DemoApplication.class})
public class BaseJunit4Test {
}
