package com.hui.controller;

import com.hui.sqlServer.UserServer;
import com.hui.sqlTest.TbUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pojo.PageBean;
import pojo.Result;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/users")
public class SqlController {

    @Autowired
    private UserServer userServer;

    @GetMapping
    public Result getUsers(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "6") Integer pageSize){
        log.info("分页查询，参数：",pageNum,pageSize);
        // 调用分页查询
        PageBean p = userServer.getUsers(pageNum,pageSize);
        return Result.success(p);
    }

    @GetMapping("/findUsers")
    public Result findUsers(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "6") Integer pageSize, String username, Integer age,
                            @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss") LocalDateTime begin ,
                            @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss") LocalDateTime end){
        log.info("分页查询，参数：",pageNum,pageSize,username,age,begin,end);
        // 调用分页查询
        PageBean p = userServer.findUser(pageNum,pageSize,username,age,begin,end);
        return Result.success(p);
    }

    @PostMapping
    public Result addUser(@RequestBody TbUser tbUser){
        userServer.addUser(tbUser);
        return Result.success();
    }

    @PutMapping
    public Result editUser(){
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delUser(@PathVariable Integer id) throws Exception {
        userServer.deleteUser(id);
        return Result.success();
    }
}
