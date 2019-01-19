package com.westos.rbac.filter.LoginFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //拿到session
        HttpSession session = req.getSession();

        // 如果是login页面，放行
        if (req.getRequestURI().equals( "/login.jsp")||
                req.getRequestURI().equals("/login") ||
                req.getRequestURI().equals("/logout")) {
            chain.doFilter(req, response);
            return;
        }
        // 如果是那些静态资源：*.html, /img/**, /js/**, css/** 放行
        if (req.getRequestURI().endsWith("*.html")) {
            chain.doFilter(req, response);
            return;
        }
        if (req.getRequestURI().startsWith("/css/") ||
                req.getRequestURI().startsWith("/js/") ||
                req.getRequestURI().startsWith("/img/")) {
            chain.doFilter(req, response);
            return;
        }

        // 检查用户是否登录
        Object user = session.getAttribute("principal");
        if (user != null) {
            chain.doFilter(req, response);
        } else {
            session.setAttribute("error","您尚未登录,请先登录");
            resp.sendRedirect("/login.jsp");
//            resp.sendRedirect("/login.jsp?error='尚未登陆'");
        }
    }

    @Override
    public void destroy() {

    }
}
