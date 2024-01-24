package com.hui;

import com.example.HeaderGenerator;
import com.example.HeaderParse;
import com.example.TokenParse;
import com.hui.controller.AOPController;
import com.hui.sqlTest.TbUser;
import com.hui.sqlTest.TestMapper;
import com.hui.sqlTest.UserTest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest // springboot整合单元测试的注解
class MavenSpringBootMybatisApplicationTests {

    /*@Test
    void contextLoads() {
    }*/
    @Autowired
    public TestMapper testMapper;



    /**
     * mybatis推荐！！！
     */
    @Test
    public void testUserMapper(){
        List<TbUser> list = testMapper.getList();
        list.stream().forEach(ele -> {
            System.out.println(ele.getUsername());
        });
    }

    /**
     * 了解即可，不用写
     * 对比mybatis：
     * JDBC（Java DataBase Connectivity）：使用Java语言操作关系型数据库的一套API
     * 缺点：硬编码，繁琐，资源浪费，性能降低
     *
     * @throws Exception
     */
    @Test
    public void testJdbc() throws Exception{
        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 获取连接对象
        String url = "jdbc:mysql://localhost:3306/db_test_01";
        String username = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url,username,password);

        // 获取执行sql的对象statement，执行sql，返回结果
        String sql = "select * from tb_user";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        // 封装结果数据
        List<UserTest> userLists = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String userName = resultSet.getString("username");
            String realName = resultSet.getString("realName");
            int age = resultSet.getInt("age");
            UserTest userTest = new UserTest(userName,id,age,realName);
            userLists.add(userTest);
        }

        // 释放资源
        statement.close();
        connection.close();

        userLists.stream().forEach(ele -> {
            System.out.println(ele.getRealName());
        });
    }


    @Test
    public void testDelete(){
        int result =  testMapper.delete(10);
        System.out.println(result); // 1:成功 0：失败
    }

    @Test
    public void testInsert(){
        TbUser tb = new TbUser(1,"夏露露2","xialuli2",12,'女', LocalDateTime.now(),LocalDateTime.now());
        testMapper.insert(tb);
        System.out.println(tb.getId()); //10
    }

    @Test
    public void testUpdate(){
        TbUser tb = new TbUser(10,"艾尔莎","Sha",18,'女', LocalDateTime.now(),LocalDateTime.now());
        testMapper.update(tb);
    }

    @Test
    public void testSelectId(){
        TbUser tbUser = testMapper.selectId(10);
        System.out.println(tbUser);
    }

    @Test
    public void testFindList(){
        List<TbUser> list = testMapper.findList("xia");
        System.out.println(list);
    }

    @Test
    public void testFindList2(){
        List<TbUser> list = testMapper.findList2("xia");
        System.out.println(list);
    }

    @Test
    public void testBatchDel(){
        List<Integer> ids = Arrays.asList(11,12,13);
        System.out.println(ids);
        testMapper.batchDel(ids);
    }

    @Test
    public void testJwt() throws Exception {
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("userName","hui3");
        // 签名算法+载荷+有效期1h
        // 密钥字符串至少4位
        // jdk17 【jjwt 和 jaxb-api依赖】
        String str = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256,"huiSecret")
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();
        System.out.println(str);

    }

    @Test
    public void parseJwt(){
        Claims claims = Jwts.parser().setSigningKey("huiSecret")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlck5hbWUiOiJodWkzIiwiZXhwIjoxNzA1NTQ0Mjg5fQ.2EolEk9V0Bvs_V_yFDbcdLPs3_Rq6fE4BD9XASAjwb4")
                .getBody();
        System.out.println(claims);
        // {id=1, userName=hui3, exp=1705544289}
    }
    /*
    * 默认情况下，spring项目启动时，会吧bean都创建好放在IOC容器中【还会收到作用域及延迟初始化影响，这里主要针对于默认的单例非延迟加载的bean而言】
    * 如果想要主动获取这些bean，可以通过如下方式：
    * 根据name获取bean：object getBean(String name)
    * 根据类型获取bean：<T> T getBean(Class<T> requiredType)
    * 根据name获取bean（带类型转换）：<T> T getBean(String name,Class<T> requiredType)
    *
    * 作用域|说明
    * singleton|容器内同名称的bean只有一个实例（单例））默认
    * prototype|每次使用该bean时会创建新的实例（非单例）
    * request|每个请求范围内会创建新的实例（web环境）
    * session|每个会话范围范围内会创建新的实例（web环境）
    * application|每个应用范围内会创建新的实例（web环境）
    *
    * bean作用域
    * 可以通过@Scope注解来进行配置作用域
    * 默认singleton的bean，在容器启动时被创建，可以使用@Lazy注解来延迟初始化（延迟到第一次使用时）
    * @Scope("prototype"),每一次使用该bean的时候创建一个新的实例（new一个类）。
    * 实际开发当中，绝大部门的bean都是单例的，也就是说绝大部门bean不需要配置@Scope属性
    *
    * */

    @Autowired
    private ApplicationContext applicationContext; // IOC容器对象

    @Test
    public void testGetBean(){
        // 根据Bean的名称获取【驼峰,默认类名首字母小写，不是则类名】
        AOPController beanAop = (AOPController) applicationContext.getBean("AOPController");
        System.out.println(beanAop);

        // 根据bean的类型获取
        AOPController beanAop2 = applicationContext.getBean(AOPController.class);
        System.out.println(beanAop2);

        // 根据bean的名称及类型获取
        AOPController beanAop3 = applicationContext.getBean("AOPController",AOPController.class);
        System.out.println(beanAop3);

    }


    @Test
    public void testScopeBean(){
        for (int i = 0; i < 10; i++) {
            AOPController beanAop = applicationContext.getBean(AOPController.class);
            System.out.println(beanAop);
        }
    }

    @Autowired
    private SAXReader saxReader;

    @Test
    public void testThirdBean() throws Exception {
//        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(this.getClass().getClassLoader().getResource("TestData.xml"));
        Element root = document.getRootElement();
        System.out.println(root);
        List<Element> elements = root.elements("info");
        for (Element e: elements){
            String name = e.element("name").getText();
            String age = e.element("age").getText();
            System.out.println(name+"---->"+age);
        }

    }

    /*
    * Springboot的自动配置就是当Spring容器启动后，一些配置类、bean对象就自动存入到了IOC容器中。
    *
    * 自动配置原理
    * 作用：按照一定的条件进行判断，在满足给定条件后才会注册对应的bean对象到Spring IOC容器中。
    * 位置：方法、类
    * @Conditional本身是一个父注解，派生出大量的子注解
    *   @ConditionalOnClass：判断环境中是否有对应字节码文件，才注册bean到IOC容器。
    *   @ConditionalOnMissingBean：判断环境中没有对应的bean（类型或名称），才注册bean到IOC容器。
    *   @ConditionalOnProperty:判断配置文件中有对应属性和值，才注册bean到IOC容器
    *
    * 方式1：@ComponentScan组件扫描【不推荐】
    * 方式2：@import导入。使用@import导入的类会被Spring加载到IOC容器中
    * 导入形式主要有：导入【普通类、配置类、ImportSelector接口实现类、@EnableXxxx注解，封装@Import注解（推荐）】
    *
    * */

    @Test
    public void TokenParseTests(){
        System.out.println(applicationContext.getBean(TokenParse.class));
    }

    @Test
    public void HeaderGeneratorTests(){
        System.out.println(applicationContext.getBean(HeaderGenerator.class));
    }

    @Test
    public void HeaderParseTests(){
        System.out.println(applicationContext.getBean(HeaderParse.class));
    }
}
