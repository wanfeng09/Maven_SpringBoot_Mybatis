package com.hui.config;

import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* 第三方bean
* 管理第三方bean对象，需要用到@Bean注解。自定义使用@Component及衍生注解声明bean
* 统一管理第三方Bean，可以通过@Configuration注解声明一个配置类
*
* 通过@Bean注解的name和value属性可以声明bean的名称，如果不指定，默认bean的名称就是方法名
* 如果第三方bean需要依赖其他bean对象，直接在bean定义方法中设置形参即可，容器会根据类型自动装配
*
* */
@Configuration // 配置类
public class CommonConfig {
    // 声明第三方bean
    @Bean // // 将当前方法的返回值对象交给IOC容器管理，成为IOC容器bean
    public SAXReader saxReader(){
        return new SAXReader();  // 获取解析器对象
    }
}
