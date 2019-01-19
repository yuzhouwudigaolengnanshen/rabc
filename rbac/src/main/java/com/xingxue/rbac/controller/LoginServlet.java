package com.xingxue.rbac.controller;

import com.xingxue.rbac.entity.User;
import com.xingxue.rbac.service.UserService;
import com.xingxue.rbac.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yihang
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username==null || username.equals("")){
            resp.sendRedirect(req.getContextPath()+"/login.jsp?error");
            return;
        }
        if(password==null || password.equals("")){
            resp.sendRedirect(req.getContextPath()+"/login.jsp?error");
            return;
        }
        // 查询数据库中的用户对象
        User user = userService.findByUsername(username);

        // 没有查询到
        if(user == null) {
            resp.sendRedirect(req.getContextPath()+"/login.jsp?error");
            return;
        }
        // 密码不正确
        if(!user.getPassword().equals(password)){
            resp.sendRedirect(req.getContextPath()+"/login.jsp?error");
            return;
        }
        // 将认证信息存入session作用域
        req.getSession().setAttribute("principal", user);
        resp.sendRedirect(req.getContextPath()+"/index.jsp");
    }
}
