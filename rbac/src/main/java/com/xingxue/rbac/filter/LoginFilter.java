package com.xingxue.rbac.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yihang
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        // 如果是login页面，放行
        if (req.getRequestURI().equals(req.getContextPath() + "/login.jsp")||
                req.getRequestURI().equals(req.getContextPath() + "/login") ||
                req.getRequestURI().equals(req.getContextPath() + "/logout")) {
            chain.doFilter(req, response);
            return;
        }
        // 如果是那些静态资源：*.html, /img/**, /js/**, css/** 放行
        if (req.getRequestURI().endsWith("*.html")) {
            chain.doFilter(req, response);
            return;
        }
        if (req.getRequestURI().startsWith(req.getContextPath() + "/css/") ||
                req.getRequestURI().startsWith(req.getContextPath() + "/js/") ||
                req.getRequestURI().startsWith(req.getContextPath() + "/img/")) {
            chain.doFilter(req, response);
            return;
        }

        // 检查用户是否登录
        Object user = req.getSession().getAttribute("principal");
        if (user != null) {
            chain.doFilter(req, response);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?error=");
        }
    }

    @Override
    public void destroy() {

    }
}
