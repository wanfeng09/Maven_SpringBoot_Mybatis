package com.hui.sqlTest;
/*
* lombok
* 通过注释的形式自动生成构造器、getter/setter、equals、hashcode、toString等方法，并可以自动化生辰日志变量，简化Java开发，提高效率
*
*
* */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TbUser {
    private int id;
    private String username;
    private String realname;
    private int age;
    private char gender;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
