package com.hui.controller;

import com.hui.sqlServer.UserServer;
import com.hui.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pojo.Result;
import pojo.UserLogin;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private UserServer userServer;

    @PostMapping("/login")
    public Result login(@RequestBody UserLogin u){
        UserLogin user = userServer.getUsernameAndPassword(u);
        // 登录成功，生成令牌
        if(user != null){
            Map<String,Object> map = new HashMap<>();
            map.put("id",user.getId());
            map.put("username",user.getUsername());
            String token = JWTUtils.generateJWT(map);
            return Result.success(token);
        }

        // 登录失败
        return Result.error("用户名或密码错误!");
    }
}
