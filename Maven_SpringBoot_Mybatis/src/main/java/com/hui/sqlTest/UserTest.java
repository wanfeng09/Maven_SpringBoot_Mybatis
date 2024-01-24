package com.hui.sqlTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // 有参数构造器
@NoArgsConstructor  // 无参数构造器
public class UserTest {
    private String name;
    private  int id;
    private int age;
    private String realName;
}
