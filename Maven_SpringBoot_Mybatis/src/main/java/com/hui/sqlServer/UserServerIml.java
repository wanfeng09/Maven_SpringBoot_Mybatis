package com.hui.sqlServer;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hui.annotation.MyLog;
import com.hui.sqlMapper.UserMapper;
import com.hui.sqlTest.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.PageBean;
import pojo.UserLogin;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service（业务逻辑层）：逻辑处理
 */
@Service
public class UserServerIml implements UserServer {

    @Autowired
    private UserMapper userMapper;

    @MyLog
    @Override
    public PageBean getUsers(Integer pageNum, Integer pageSize) {
       /* // 获取用户总数
        Long total = userMapper.countUsers();
        // 获取分页查询结果列表
        Integer start = (pageNum - 1) * pageSize;
        List<TbUser> list = userMapper.getUsers(start,pageSize);*/

        // 设置分页参数【使用分页插件】
        PageHelper.startPage(pageNum,pageSize);
        List<TbUser> list = userMapper.getUsers();
        Page<TbUser> p = (Page<TbUser>) list;

        return new PageBean(p.getTotal(),p.getResult());
    }


    @Override
    public PageBean findUser(Integer pageNum, Integer pageSize,String username, Integer age, LocalDateTime begin, LocalDateTime end) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbUser> list = userMapper.findUser(username,age,begin,end);
        Page<TbUser> p = (Page<TbUser>) list;
        return new PageBean(p.getTotal(),p.getResult());
    }

    @Transactional(rollbackFor = Exception.class) // 事务管理，所有异常都会回滚
    @Override
    public void deleteUser(Integer id) throws Exception {
        userMapper.deleteUser(id); // 删除同部门的用户
       /* int i = 1/0;
        if(true){throw new Exception("出错啦！");}*/
        userMapper.deleteUserType(id); // 删除部门
    }

    @Override
    public void addUser(TbUser tbUser) {
        tbUser.setCreateTime(LocalDateTime.now());
        tbUser.setUpdateTime(LocalDateTime.now());
        userMapper.addUser(tbUser);
    }

    @MyLog
    @Override
    public UserLogin getUsernameAndPassword(UserLogin u) {
        UserLogin user = userMapper.getUsernameAndPassword(u);
        return user;
    }
}
