package com.wy.demo.log日志.log4j2;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args){
        LOGGER.info("log4j2 demo");
    }
}
