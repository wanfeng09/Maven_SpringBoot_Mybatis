package com.hui.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
* 拦截器Interceptor
* 是一种动态拦截方法调用的机制，类似于过滤器。Spring框架中提供的，用来动态拦截控制器方法的执行。
* 作用：拦截请求，在指定的方法调用前后，根据业务需要执行预设的代码
*
* 步骤
* 1、定义拦截器，实现HandlerInterceptor接口，并重写其所有方法。
* 2、注册拦截器
*
* |拦截路径|含义|
* |/*|一级路径|
* |/**|任意级路径|
* |/user/*|user下的一级路径|
* |/user/**|user下的任意级路径|
*
*
* */
@Component
public class InterceptorDemo  implements HandlerInterceptor {
    /**
     * 目标资源方法执行前执行，返回true放行，反之。
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        return true;
    }

    /**
     * 目标资源方法执行后执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    /**
     * 视图渲染完毕后执行，最后执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
