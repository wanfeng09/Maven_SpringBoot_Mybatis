package com.hui.framework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.WebObj;

import java.util.List;

/**
 * Service（业务逻辑层）：逻辑处理
 */
// @Primary
@Service
// @Component //将当前类交给IOC容器管理，成为IOC容器中的bean
public class NewServiceClass implements NewService{
    @Autowired // 运行时，IOC容器会提供该类型的bean对象，并赋值改变量，即依赖注入
    private NewDao newDao;
    // 高耦合【垃圾】
    // private NewDao newDao= new NewDaoClass();

    @Override
    public List<WebObj> listService() {
        // 获取数据
        List<WebObj> list = newDao.listDao();
        /*list.stream().forEach(ele -> {
            String name = ele.getName();
            ele.setName("编号" + name);
        });*/
        return list;
    }
}
