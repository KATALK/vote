package com.example.demo.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Order(1) //如果有多个filter,则序号越小，越早被执行
@WebFilter(filterName = "FilterDemo",urlPatterns = "/*") //过滤所有路径请求
public class FilterDemo implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("拦截器");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
