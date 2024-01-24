package com.hui.utils;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Autowired
    private InterceptorDemo interceptorDemo;
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(interceptorDemo).addPathPatterns("/**");
    }
}
