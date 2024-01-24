package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* 统一响应结果封装类
* 响应数据
* @ResponseBody
* 类型：方法注解、类注解
* 位置：Controller方法上、类上
* 作用：将方法返回值直接响应，如果返回值类型是实体对象/集合，将会转换成JSON格式响应
* 重点：@RestController = @Controller + @ResponseBody
* */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private int code; // 请求码 200成功 201失败
    private String msg; // 提示信息
    private Object data; // 数据

    /**
     * 请求成功方法
     * @param data 返回数据
     * @return
     */
    public static Result success(Object data){
        return new Result(200,"success",data);
    }

    public static Result success(){
        return new Result(200,"success",null);
    }

    /**
     * 请求失败方法
     * @param msg 返回失败的提示信息
     * @return
     */
    public static Result error(String msg){
        return new Result(201,"error",msg);
    }
}
