package com.wy.demo.jwt;

import com.wy.demo.controller.dto.User;
import com.wy.demo.mybatis.mappers.UserrMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private UserrMapper userrMapper;
    @Override
    public String login(String userName, String passWord) throws Exception {
        //登录验证
        User user = userrMapper.findByUserNameAndPassword(userName, passWord);
        if (user == null) {
            return null;
        }
        //如果能查出，则表示账号密码正确，生成jwt返回
        String uuid = UUID.randomUUID().toString().replace("-", "");
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", user.getUserName());
        map.put("age", user.getPassWord());
        return jwtUtil.createJWT(uuid, "login subject", 0L, map);
    }
}