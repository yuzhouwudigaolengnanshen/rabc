package com.westos.rbac.controller;

import com.westos.rbac.dao.impl.UserDaoImpl;
import com.westos.rbac.domain.User;

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

    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username==null || username.equals("")){
            resp.sendRedirect("/login.jsp?error");
            return;
        }
        if(password==null || password.equals("")){
            resp.sendRedirect("/login.jsp?error");
            return;
        }
        // 查询数据库中的用户对象
        User user = userDao.findByUsername(username);

        // 没有查询到
        if(user == null) {
            req.setAttribute("error", "用户不存在");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
//            resp.sendRedirect("/login.jsp?error='用户不存在'");
            return;
        }
        // 密码不正确
        if(!user.getPassword().equals(password)){
            req.setAttribute("error", "密码错误");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
//            resp.sendRedirect("/login.jsp?error='密码不正确'");
            return;
        }
        // 将认证信息存入session作用域
        req.getSession().setAttribute("principal", user);
        resp.sendRedirect("/index.jsp");
    }
}
