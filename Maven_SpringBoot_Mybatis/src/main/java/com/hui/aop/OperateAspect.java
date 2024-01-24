package com.hui.aop;

import com.alibaba.fastjson.JSONObject;
import com.hui.sqlMapper.AOPUserMapper;
import com.hui.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.OperateLogAOP;

import java.time.LocalDateTime;
import java.util.Arrays;

/*
* 将增删改查相关接口的草错日志记录到数据库表中
* 日志信息：操作人、操作时间、执行方法的全类名、执行方法名、方法运行时参数、返回值、方法执行时长
*
* 步骤
* 1、为增删改查添加统一功能，使用AOP技术【@Around|环绕通知】
* 2、增删改查方法名没有规律，使用自定义注解完成目标方法匹配
* */
@Slf4j
@Component
@Aspect
public class OperateAspect {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private  AOPUserMapper aopUserMapper;
    @Around("@annotation(com.hui.annotation.AnLiLog)")
   public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 操作人ID-登录员工ID
        // 获取请求头中的jwt令牌，解析令牌
        String token = request.getHeader("token");
        Claims claims = JWTUtils.parseJWT(token);
        Integer operateUserId = (Integer) claims.get("id");

        // 操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        // 操作类名
        String className = joinPoint.getTarget().getClass().getName();
        // 方法名
        String methodName = joinPoint.getSignature().getName();
        // 操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        long begin = System.currentTimeMillis();
        // 放行
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        // 方法返回值
        String returnValue = JSONObject.toJSONString(result);
        // 耗时
        Long costTime = end - begin;

        // 记录操作日志
        OperateLogAOP logAOP = new OperateLogAOP(null,operateUserId,operateTime,className,methodName,methodParams,returnValue,costTime);

        aopUserMapper.insertLog(logAOP);
        log.info("AOP操作日志: {}",logAOP);
        return result;

   }
}
