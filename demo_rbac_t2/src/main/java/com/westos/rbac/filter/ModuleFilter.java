package com.westos.rbac.filter;

import com.westos.rbac.domain.Module;
import com.westos.rbac.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/system/*","/order/*", "/product/*"})
public class ModuleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        // 获取当前路径
        String uri = req.getRequestURI(); // 得到了当前路径 /system/user/page
        System.out.println("当前路径是:" + uri);
        User user = (User)req.getSession().getAttribute("user");
        for (Module module : user.getModules()) {
            // 当前路径是否以 数据库中 code 路径开头
            System.out.println("module:" + module.getCode());
            if(uri.startsWith(module.getCode())&& module.getCode().length()>0) {
                chain.doFilter(req, resp);
                System.out.println("已放行=============");
                return;
            }
        }
        System.out.println("没有放行=============");
        // 没有权限，跳转至 403 页面
        resp.sendError(403);
    }

    @Override
    public void destroy() {

    }
}
