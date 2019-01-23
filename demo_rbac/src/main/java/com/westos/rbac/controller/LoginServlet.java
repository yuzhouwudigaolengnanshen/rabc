package com.westos.rbac.controller;

import com.westos.rbac.dao.UserDao;
import com.westos.rbac.dao.impl.UserDaoImpl;
import com.westos.rbac.domain.User;
import com.westos.rbac.util.Md5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDao userDao = new UserDaoImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 参数中用户输入的验证码
        String captcha = req.getParameter("captcha");

        // session 中的验证码正确答案
        Object result = req.getSession().getAttribute("result");
        if(captcha != null && ! captcha.equalsIgnoreCase(result.toString())) { // 验证不通过
            req.setAttribute("error", "验证码不正确");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }


        User user = userDao.findByUsername(username);
        if(user == null) {
            req.setAttribute("error", "用户名不存在");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        // user.getPassword()是数据库中加密后的密码
        if (!user.getPassword().equals(Md5Util.md5(password))) {
            req.setAttribute("error", "密码不正确");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/index.jsp");
    }
}
