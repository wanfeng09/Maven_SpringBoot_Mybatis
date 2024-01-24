package com.hui.api;
/*
 * 前后端不分离项目测试
 * 1、pom.xml文件中引入dom4j的依赖，用于解析XML文件
 * 2、编写工具类【WebResolveXML】、对应的实体类【WebObj】、XML数据文件
 * 3、在static目录下编写静态文件html
 * 4、编写Controller程序，处理请求，响应数据
 *
 *
 * 三层架构：单一职责原则
 * Dao（数据访问层/持久层）：数据的增删改查
 * Service（业务逻辑层）：逻辑处理
 * Controller（控制层）：接收请求、响应数据
 *
 * 分层解耦
 * 内聚：软件中各个功能模块内部的功能联系
 * 耦合：衡量软件中各个层/模块之间的依赖、关联的程度
 * 软件设计原则：高内聚低耦合
 *
 * 控制反转：Inversion Of Control，简称IOC。
 * 对象的创建控制权由程序自身转移到外部（容器），这种思想称为控制反转。
 * 依赖注入：Dependency Injection，简称DI。
 * 容器为应用程序提供运行时，所依赖的资源，称之为依赖注入。
 * Bean对象：IOC容器中的创建、管理的对象，称之为bean。
 *
 * IOC & DI入门
 * 1、Service层及Dao层的实现类，交给IOC容器【@Component】
 * 2、为Controller及Service注入运行时【@Autowired】，依赖的对象
 *
 * Bean注入
 * 使用@Autowired注解是，默认是按照类型自动装配的，如果存在多个相同类型的bean，将会报错
 * 解决：使用@Primary、@Autowired + @Qualifier("bean名称")、@Resource("bean名称")
 * @Autowired：spring框架提供的注解，按照类型注入
 * @Resource("bean名称")：是JDK提供的注解，按照名称注入
 *
 *
 * Bean声明
 * 声明bean的时候，可以通过value属性指定bean的名字，如果没有指定，默认为类名首字母小写
 * springBoot集合的web开发中，声明控制器bean只能用@Controller。
 *
 * 要把某个对象交给IOC容器管理，需要再对应的类上加上以下注解之一：
 * |注解|说明|位置|
 * |@Component|声明bean的基础注解|不属于以下三类时，用此注解|
 * |@Controller|@Component的衍生注解|标注再控制类上|
 * |@Service|@Component的衍生注解|标注再业务类上|
 * |@Repository|@Component的衍生注解|标注再数据访问类上（由于与mybatis整合，用的少）|
 *
 * bean组件扫描器
 * 声明bean的四大注解，想要生效，还需要被组件扫描注解@ComponentScan扫描
 * @ComponentScan注解是隐式配置，在启动类xxxApplication中，默认扫描范围是启动了所在的包及其子包
 *
 *
 *
 *
 *
 * */

import com.hui.framework.NewService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Result;
import pojo.WebObj;

import java.util.List;

@RestController
public class WebTest {
    @Resource(name="newServiceClassTwo")
    // @Autowired // 运行时，IOC容器会提供该类型的bean对象，并赋值改变量，即依赖注入
    // @Qualifier("newServiceClassTwo")
    private NewService newService;
    // 高耦合【垃圾】
    // private NewService newService = new NewServiceClass();
    @RequestMapping("api/webList")
    public Result WebList(){
       // 调用service，获取数据
        List<WebObj> list = newService.listService();
        return Result.success(list);
    }
   /* @RequestMapping("api/webList")
    public Result WebList(){
        // 加载并解析TestData.xml文件
        String file = this.getClass().getClassLoader().getResource("TestData.xml").getFile();
        // System.out.println(file);
        List<WebObj> list = OriginalWebTest.parse(file,WebObj.class);
        // 对数据进行转换处理
        list.stream().forEach(ele -> {
            String name = ele.getName();
             ele.setName("编号" + name);
        });
        return Result.success(list);
    }*/
}


