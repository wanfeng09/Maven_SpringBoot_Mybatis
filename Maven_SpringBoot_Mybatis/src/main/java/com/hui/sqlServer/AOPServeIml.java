package com.hui.sqlServer;

import com.hui.sqlMapper.AOPUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.TbUserInfo;

import java.time.LocalDateTime;

@Service
public class AOPServeIml implements AOPServe {

    @Autowired
    private AOPUserMapper aopUserMapper;

    @Override
    public void add(TbUserInfo tbUserInfo) {
        tbUserInfo.setCreateTime(LocalDateTime.now());
        tbUserInfo.setUpdateTime(LocalDateTime.now());
        aopUserMapper.add(tbUserInfo);
    }

    @Override
    public void del(Integer id) {
         aopUserMapper.del(id);
    }

    @Override
    public void update(String username,String realname,Integer id) {
         aopUserMapper.update(username,realname,id);
    }

    @Override
    public TbUserInfo find(String username) {
        return aopUserMapper.find(username);
    }
}
