package com.hui.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 请求处理类
// 浏览器访问地址：http://localhost:8080/hello
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
