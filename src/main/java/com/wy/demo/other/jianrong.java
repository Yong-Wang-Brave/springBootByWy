package com.wy.demo.other;

import com.wy.demo.other.jianrong202205525.GamePHP;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@Log4j2

public class jianrong {
    @PostMapping("/post")

    private static String getParams(HttpServletRequest request,@GamePHP String aa) throws Exception {

        String contentType = request.getContentType();
        if (contentType.contains("application/json")) {
            // json 解析...
            return "application/json";
        } else if (contentType.contains("application/x-www-form-urlencoded")) {
            // form 表单解析 ...
            return "application/x-www-form-urlencoded";
        } else if (contentType.contains("multipart")) {
            // 文件流解析
            return "multipart";
        } else {
            throw new Exception("不支持的content-type");
        }

    }
}
