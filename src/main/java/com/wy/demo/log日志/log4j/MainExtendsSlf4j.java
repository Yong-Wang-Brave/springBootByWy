package com.wy.demo.log日志.log4j;
import com.wy.demo.log日志.log4j2.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//log4j引入继承slf4j的jar包就可以实现logback的方式(logback默认实现了slf4j)
//也就是实现了门面模式。
public class MainExtendsSlf4j {
    static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("log4j demo");
    }
}
