package com.hui.sqlServer;

import pojo.TbUserInfo;

public interface AOPServe {
    // 抽象方法
    void add(TbUserInfo tbUserInfo);
    void del(Integer id);
    void update(String username,String realname,Integer id);
    TbUserInfo find(String username);
}
