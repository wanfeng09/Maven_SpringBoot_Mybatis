package com.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeaderConfig {
    @Bean
    // @ConditionalOnClass(name="io.jsonwebtoken.Jwts") // 环境中存在指定的这个类，才会将该bean加入IOC容器中
    // @ConditionalOnMissingBean // 不存在该类型的bean，才会将该bean加入IOC容器中---指定类型（value属性）或 名称（name属性）
    @ConditionalOnProperty(name="name",havingValue = "hui") // 配置文件中存在指定的属性与值，才会将该bean加入IOC容器中
    public HeaderGenerator headerGenerator(){
        return new HeaderGenerator();
    }

    @Bean
    public HeaderParse headerParse(){
        return new HeaderParse();
    }
}
