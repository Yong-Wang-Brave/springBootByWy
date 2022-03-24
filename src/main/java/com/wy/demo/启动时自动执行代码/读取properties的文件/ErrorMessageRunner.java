package com.wy.demo.启动时自动执行代码.读取properties的文件;

import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(3)
public class ErrorMessageRunner implements ApplicationRunner {
    @Autowired
    ErrorMessage errorMessage;
    @Override
    public void run(ApplicationArguments args) throws Exception {
errorMessage.getLevel().forEach((k,v)->{
    System.out.println(k+" : "+v);
});
    }
}
