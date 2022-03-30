package com.wy.demo.log日志.logback;
//logback继承了slf4j
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("logback demo");
    }
}
