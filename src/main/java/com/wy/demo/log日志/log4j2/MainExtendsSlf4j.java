package com.wy.demo.log日志.log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MainExtendsSlf4j {
    static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("log4j2 demo");
    }
}
