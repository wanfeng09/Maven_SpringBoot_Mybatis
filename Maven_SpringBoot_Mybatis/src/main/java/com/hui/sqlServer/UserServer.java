package com.hui.sqlServer;

import com.hui.sqlTest.TbUser;
import pojo.PageBean;
import pojo.UserLogin;

import java.time.LocalDateTime;

public interface UserServer {
    PageBean getUsers(Integer pageNum, Integer pageSize);

    PageBean findUser(Integer pageNum, Integer pageSize,String username, Integer age, LocalDateTime begin,LocalDateTime end);

    void addUser(TbUser tbUser);

    void deleteUser(Integer id) throws Exception;

    UserLogin getUsernameAndPassword(UserLogin u);
}
