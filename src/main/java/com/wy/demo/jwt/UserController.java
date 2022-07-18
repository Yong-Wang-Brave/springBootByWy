package com.wy.demo.jwt;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JwtResponse login(@RequestParam(name = "userName") String userName,
                             @RequestParam(name = "passWord") String passWord){
        String jwt = "";
        try {
            jwt = userService.login(userName, passWord);
            return JwtResponse.success(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            return JwtResponse.fail(jwt);
        }
    }
}