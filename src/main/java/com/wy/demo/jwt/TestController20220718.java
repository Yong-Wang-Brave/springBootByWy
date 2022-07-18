package com.wy.demo.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/jwt")
public class TestController20220718 {
    @Resource
    private JwtUtil jwtUtil;
    @RequestMapping("/test")
    public Map<String, Object> test(@RequestParam("jwt") String jwt) {
        //这个步骤可以使用自定义注解+AOP编程做解析jwt的逻辑，这里为了简便就直接写在controller里
        Claims claims = jwtUtil.parseJWT(jwt);
        String name = claims.get("name", String.class);
        String age = claims.get("age", String.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);
        map.put("code", "0");
        map.put("msg", "请求成功");
        return map;
    }
}