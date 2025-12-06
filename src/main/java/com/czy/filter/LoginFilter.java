package com.czy.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 陈政缘
 * Date  2025/7/8 下午2:37
 */

/**
 * 过滤器：定义一个类，实现javax.servlet.Filter接口，重写方法
 * doFilter()在此方法处理助词是否登录
 */

@WebFilter("/manage/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig cfg) {}
    public void destroy() {}

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest  request  = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        // 放行登录提交地址
        String uri = request.getRequestURI();
        if (uri.endsWith("/manage/login.do") || uri.endsWith("/login.jsp")) {
            chain.doFilter(req, res);
            return;
        }

        // 只检查 session 中的 “user”
        Object user = (session == null ? null : session.getAttribute("user"));
        if (user != null) {
            chain.doFilter(req, res);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
