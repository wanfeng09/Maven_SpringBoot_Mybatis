package com.hui.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Slf4j
@Aspect
@Component
public class TestAspect4 {
    // @Pointcut("execution(public com.hui.pojo.PageBean com.hui.sqlServer.UserServerIml.getUsers(java.lang.Integer,java.lang.Integer))")
    // @Pointcut("execution(com.hui.pojo.PageBean com.hui.sqlServer.UserServerIml.getUsers(java.lang.Integer,java.lang.Integer))")
    // @Pointcut("execution(com.hui.pojo.PageBean getUsers(java.lang.Integer,java.lang.Integer))")
    // @Pointcut("execution(com.hui.pojo.PageBean com.hui.sqlServer.UserServerIml.*(java.lang.Integer,java.lang.Integer))")
    // @Pointcut("execution(* com.hui.sqlServer.*.*Users(..))")
    // @Pointcut("execution(* com.*.*.*.*(..))")
    @Pointcut("execution(* com.hui.sqlServer.UserServerIml.getUsers(..)) || execution(* com.hui.sqlServer.UserServerIml.deleteUser(..))")
    private void pt(){}

    @Before("pt()")
    public void before(){
        log.info("before4...");
    }
}
