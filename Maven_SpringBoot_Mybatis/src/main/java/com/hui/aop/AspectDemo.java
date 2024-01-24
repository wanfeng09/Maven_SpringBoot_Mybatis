package com.hui.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class AspectDemo {
    @Before("execution(* com.hui.sqlServer.UserServerIml.*(..))")
    public void before(JoinPoint joinPoint){
        String className = joinPoint.getClass().getName();
        log.info("before...."+className);
    }

    @Around("execution(* com.hui.sqlServer.UserServerIml.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("around before...");
        // 获得目标类名
        String className = joinPoint.getTarget().getClass().getName();
        log.info("获得目标类名:{}",className);
        // 获取目标方法签名
        Signature signature = joinPoint.getSignature();
        log.info("获取目标方法签名:{}",signature);
        // 获取目标方法名
        String methodName = joinPoint.getSignature().getName();
        log.info("获取目标方法名:{}",methodName);
        // 获取目标方法运行参数
        Object[] args = joinPoint.getArgs();
        log.info("获取目标方法运行参数:{}", Arrays.toString(args));
        // 放行
        Object result = joinPoint.proceed();
        log.info("around after...");
        return result;
    }

    @After("execution(* com.hui.sqlServer.UserServerIml.*(..))")
    public void after(){
        log.info("after....");
    }

    @AfterReturning("execution(* com.hui.sqlServer.UserServerIml.*(..))")
    public void afterReturning(){
        log.info("afterReturning....");
    }

    @AfterThrowing("execution(* com.hui.sqlServer.UserServerIml.*(..))")
    public void afterThrowing(){
        log.info("afterThrowing....");
    }

}
