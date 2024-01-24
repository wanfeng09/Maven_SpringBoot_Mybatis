package com.hui.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/*
* 根据注解匹配
*
* */
@Slf4j
@Aspect
@Component
public class AnnotationAspect {
    @Pointcut("@annotation(com.hui.annotation.MyLog)")
    private void pt(){}
    @Before("pt()")
    public void before(){
        log.info("before...Annotation");
    }
}
