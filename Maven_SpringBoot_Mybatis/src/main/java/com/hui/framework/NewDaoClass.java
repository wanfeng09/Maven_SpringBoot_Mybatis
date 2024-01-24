package com.hui.framework;

import org.springframework.stereotype.Repository;
import pojo.WebObj;

import java.util.List;

/**
 * Dao（数据访问层/持久层）：数据的增删改查
 */
@Repository
// @Component //将当前类交给IOC容器管理，成为IOC容器中的bean
public class NewDaoClass implements NewDao{

    @Override
    public List<WebObj> listDao() {
        // 加载并解析TestData.xml文件
        String file = this.getClass().getClassLoader().getResource("TestData.xml").getFile();
        // System.out.println(file);
        List<WebObj> list = WebResolveXML.parse(file,WebObj.class);
        return list;
    }
}
