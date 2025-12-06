package com.czy.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈政缘
 * Date  2025/7/8 下午2:41
 */

/**
 * 编码格式过滤器
 * 给所有控制器设置请求和相应的编码格式
 */

@WebFilter("*.do")  // 注意此保护路径不带/
public class CharFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 把ServletRequest对象转为HttpServletRequest对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 把servletResponse对象转为HttpServletResponse对象
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 设置请求的编码格式
        request.setCharacterEncoding("UTF-8");
        // 设置响应的编码格式
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 放行
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
