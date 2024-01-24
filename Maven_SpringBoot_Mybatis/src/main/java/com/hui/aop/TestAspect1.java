package com.hui.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2) // 控制顺序
@Slf4j
@Aspect
@Component
public class TestAspect1 {
    @Before("execution(* com.hui.sqlServer.UserServerIml.*(..))")
    public void before(){
        log.info("before1....");
    }

    @After("execution(* com.hui.sqlServer.UserServerIml.*(..))")
    public void after(){
        log.info("after1....");
    }
}
