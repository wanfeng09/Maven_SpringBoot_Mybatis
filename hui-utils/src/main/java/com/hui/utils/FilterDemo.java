package com.hui.utils;

import jakarta.servlet.*;

import java.io.IOException;


/*
* filter过滤器
* javaWeb三大组件（Servlet、Filter、Listener）之一。
* 过滤器可以把对资源的请求拦截下来，从而实现一些特殊的功能。
* 过滤器一般完成一些通用的操作，比如：登录效验、统一编码处理、敏感字符处理等
*
* 步骤
* 1、定义Filter：定义一个类，实现Filter接口，并重写其所有方法。
* 2、配置Filter：Filter类上加@WebFilter注解，配置拦截资源的路径。引导类上加@ServletComponentScan开启Servlet组件支持。
*
* |拦截路径|urlPatterns值|含义|
* |拦截具体路径|/login|只能访问/login路径时，才会被拦截|
* |目录拦截|/user/*|访问/user下的所有资源，都会被拦截|
* |拦截所有|/*|访问所有资源，都会被拦截|
*
* 过滤器链
* 一个web应用中，可以配置多个过滤器，这多个过滤器就形成了一个过滤器链
* 顺序：注解配置的Filter，优先级是按照过滤器类名（字符串）的自然排序
*
* */

//@WebFilter(urlPatterns = "/*")
public class FilterDemo implements Filter {

    /**
     * 初始化方法，web服务器启动，创建Filter时调用，只调用一次
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化");
        // Filter.super.init(filterConfig);
    }

    /**
     * 拦截请求，调用该方法，可调用多次
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截请求,放行前逻辑");
        // 放行
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("拦截请求,放行后逻辑");

    }

    /**
     * 销毁方法。服务器关闭时调用，只调用一次
     */
    @Override
    public void destroy() {
        System.out.println("销毁方法");
       // Filter.super.destroy();
    }
}
