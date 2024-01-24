package com.hui.controller;

import com.alibaba.fastjson.JSONObject;
import com.hui.annotation.AnLiLog;
import com.hui.sqlServer.AOPServe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import pojo.Result;
import pojo.TbUserInfo;

// @Lazy  // 延迟初始化（延迟到第一次使用时） 单例
@Scope("prototype") // 创建一个新的实例（new一个类） 非单例。
@Slf4j
@RestController
@RequestMapping("/AOP")
public class AOPController {
    @Autowired
    private AOPServe aopServe;

    // 测试bean作用域
    public AOPController(){
        System.out.println("测试bean作用域-----------无参数构造器");
    }
    @AnLiLog
    @PostMapping
    public Result add(@RequestBody TbUserInfo tbUserInfo){
        log.info("---------------:{}", JSONObject.toJSONString(tbUserInfo));
        aopServe.add(tbUserInfo);
        return Result.success("添加成功！");
    }
    @AnLiLog
    @DeleteMapping("/{id}")
    public Result del(@PathVariable Integer id){
        aopServe.del(id);
        return Result.success("删除成功");
    }
    @AnLiLog
    @PutMapping
    public Result update(String username, String realname,Integer id){
        log.info("-----修改信息----------:{},{},{}", username,realname,id);
        aopServe.update(username,realname,id);
        return Result.success("修改成功");
    }
    @AnLiLog
    @GetMapping
    public Result find(String username){
        TbUserInfo user = aopServe.find(username);
        return Result.success(user);
    }
}
