package com.hui.api;

import com.hui.sqlTest.TbUser;
import com.hui.sqlTest.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pojo.Result;
import pojo.User;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class UserLogin {
    @Autowired
    public TestMapper testMapper;
    // 原始方式，获取请求参数，需要通过HttpServletRequest对象手动获取【繁琐，不推荐】
    // http://localhost:8080/api/user
   /* @RequestMapping("/api/user")
    public String userInfo(HttpServletRequest request){
        // 获取请求参数
        String name = request.getParameter("name");
        String ageStr = request.getParameter("ageStr");
        int age = Integer.parseInt(ageStr); // 类型转换
        System.out.println("用户名为：" + name + "，年龄是：" + age);
        return "OK";
    }*/

    /**
     * springboot方式，定义形参即可接收参数，会自动类型转换【推荐】
     * 注意
     * 如果方法形参名称与请求参数名称不匹配，可以使用@RequestParam完成映射
     * @RequestParam中的required属性默认true【必传】，不传递报错。可选请设置false
     * @param name 姓名
     * @param age 年龄
     * @return "OK"
     */
    @RequestMapping("/api/user")
    public String userInfo(String name, Integer age, @RequestParam(name = "userSex",required = false) String sex){
        return "用户名为：" + name + "，年龄是：" + age + "，性别----" + sex;
    }

    /**
     * 实体参数
     * 实体对象：请求参数名和形参对象属性名相同，定义POJO【实体类、对象】接收即可
     * 复杂的实体对象：请求参数名和形参对象属性名相同，按照对象层次结构关系即可接收嵌套POJO属性参数。
     * @param u 实体对象
     * @return Result 统一响应结果
     */
    @RequestMapping("api/userEntity")
    public Result userEntityInfo(User u){
        return Result.success(u);
    }

    /**
     * 数组参数：请求参数名与形参数组名称相同且请求参数为多个，定义数组类型形参即可接收参数
     * @param list 数组参数
     * @return
     */
    @RequestMapping("api/arrList")
    public Result arrayParam(String[] list){
        // return Result.success(Arrays.toString(list));
        return Result.success(list);
    }

    /**
     * 集合参数：请求参数名与形参集合名称相同且请求参数为多个，@RequestParam绑定参数关系
     * @param list 集合参数
     * @return
     */
    @RequestMapping("api/listParam")
    public Result listParam(@RequestParam List<String> list){
        return Result.success(list);
    }

    /**
     * 日期参数：使用@DateTimeFormat注解完成日期参数格式转换
     * @param time 日期参数
     * @return
     */
    @GetMapping("api/dateTime")
    public Result dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime time){
        return Result.success(time);
    }

    /**
     * JSON参数：JSON数据键名与形参对象属性名相同，定义POJO类型形参即可接收参数，需要使用@RequestBody标识
     * @param user JSON参数
     * @return
     */
    @RequestMapping("api/jsonUser")
    public Result jsonParam(@RequestBody User user){
        return Result.success(user);
    }

    /**
     * 路径参数：通过请求URL直接传递参数，使用{}来标识该路径参数，需要使用@PathVariable获取路径参数
     * @param id 路径参数
     * @return
     */
    @RequestMapping("api/path/{id}")
    public String pathParam(@PathVariable Integer id){
        return "OK";
    }

    @RequestMapping("api/path/{id}/{name}")
    public String pathMulParam(@PathVariable Integer id,@PathVariable String name){
        return "OK";
    }
    @PostMapping("api/getSqlData")
    public Result sqlParams(){
        List<TbUser> list = testMapper.findList2("xia");
        return Result.success(list);
    }

}
