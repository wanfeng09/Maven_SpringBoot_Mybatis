package com.hui.aop;
/*
* AOP
* Aspect Oriented Programming：面向特定方法编程
* 使用场景：记录操作日志/权限控制/事务管理/...
* 优势：减少重复代码、提高开发效率、维护方便
*
* 连接点：JoinPoint，可以被AOP控制的方法（暗含方法执行时的相关信息）
* 通知：Advice，指那些重复的逻辑，也就是共性功能（最终体现为一个方法）
* 切入点：PointCut，匹配连接点的条件，通知仅会在切入点方法执行时被应用
* 切面：Aspect，描述通知与切入点的对应关系
* 目标对象：Target，通知所应用的对象
*
* 通知顺序
* 当有多个切面的切入点都匹配到了目标方法，目标方法运行时，多个通知方法都会被执行。
* 执行顺序
* 1、不同切面类中，默认按照切面类的类名字母排序
*   目标方法前的通知方法：字母排名靠前的先执行
*   目标方法后的通知方法：字母排名靠前的后执行
* 2、用@Order(数字)加载切面类上来控制顺序
*   目标方法前的通知方法：数字小的先执行
*   目标方法后的通知方法：数字小的后执行
*
* @Around环绕通知需要自己调用ProceedingJoinPoint.proceed()来让原始方法执行，其他通知不需要考虑目标方法执行
* @Around环绕通知方法的返回值，必须执行为Object，来接收原始方法的返回值
* @PointCut该注解的作用是将公共的切点表达式抽取出来，需要用到时引用该切点表达式即可。【private：仅在当前切面类中引用该表达式；public：在其他外部切面类中也可以引用该表达式】
*
* 通知类型|说明
* @Around|环绕通知，此注解标注的通知方法在目标方法前、后都被执行
* @Before|前置通知，此注解标注的通知方法在目标方法前被执行
* @After|后置通知，此注解标注的通知方法在目标方法后被执行，无论是否有异常都会执行
* @AfterReturning|返回后通知，此注解标注的通知方法在目标方法后被执行，有异常不会执行
* @AfterThrowing|异常后通知，此注解标注的通知方法发生异常后执行
*
* 切入点表达式
* 描述切入点方法的一种表达式
* 作用：决定项目中的哪些方法需要加入通知
* 常见形式：execution(...)根据方法的签名来匹配/annotation(...)根据注解匹配
*
* execution根据方法的返回值、包名、类名、方法名、方法参数等信息来匹配
* execution(访问修饰符? 返回值 包名.类名.?方法名(方法参数) throws 异常?)
* ?可省略【访问修饰符|返回值 包名.类名.|throws 异常】
*
* 可以使用通配符描述切入点 TestAspect4
* (*):单个独立的任意符号，可以通配任意返回值、包名、类名、方法名、任意类型的一个参数，也可以通配包、类、方法名的一部分
* (..):多个连续的任意符号，可以通配任意层级的包，或任意类型、任意个数的参数
* (&&/||/!): 来组合比较复杂的切入点表达式
*
* 书写建议
* 所有业务方法名在命名时尽量规范，方便切入点表达式快速匹配。如：查询方法find开头，更新update开头
* 描述切入点方法通常基础接口描述，而不是直接描述实现类，增强拓展性
* 在满足业务需要的前提下，尽量缩小切入点的匹配范围。
*
* annotation切入点表达式，用于匹配标识有特点注解的方法 AnnotationAspect
*
* 连接点JoinPoint
* 获得方法执行时的相关信息，如目标类名、方法名、方法参数等。
* 对于@Around通知，获取连接点信息只能使用ProceedingJoinPoint
* 对于其他4种通知，获取连接点信息只能使用JoinPoint，它是ProceedingJoinPoint的父类型
*
*
* */
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect // AOP类
public class TimeAspect {
    @Around("execution(* com.hui.sqlServer.*.*(..))") // 切入点表达式
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始时间
        long begin = System.currentTimeMillis();
        // 调用原始方法运行,返回运行结果
        Object result = joinPoint.proceed();
        // 记录结束时间，计算方法耗时
        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature() + "方法执行耗时："+(end-begin));
        return result; // 返回运行结果【可篡改运行结果返回】
    }
}
