package com.hui;

import com.example.EnableHeaderConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.example","com.hui"}) //自定义配置扫描其他包
// @ServletComponentScan // 开启Servlet组件支持
// @Import({TokenParse.class}) // 导入普通类
// @Import({HeaderConfig.class}) // 导入配置类
// @Import({MyImportSelector.class}) // 导入ImportSelector接口实现类

@SpringBootApplication // 默认扫描当前包及其子包
@EnableHeaderConfig
public class MavenSpringBootMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MavenSpringBootMybatisApplication.class, args);
    }

    // 声明第三方bean【不建议在这里声明，请通过@Configuration注解声明一个配置类】
   /* @Bean // 将当前方法的返回值对象交给IOC容器管理，成为IOC容器bean
    public SAXReader saxReader(){
        return new SAXReader();  // 获取解析器对象
    }*/

}
