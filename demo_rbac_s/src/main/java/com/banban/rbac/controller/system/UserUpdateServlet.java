package com.banban.rbac.controller.system;

import com.banban.rbac.dao.UserDao;
import com.banban.rbac.dao.impl.UserDaoImpl;
import com.banban.rbac.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/system/user/update")
public class UserUpdateServlet extends HttpServlet {
    private UserDao userDao =  new UserDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String orgId = req.getParameter("orgId");

        user.setId(Integer.parseInt(id));
        user.setUsername(username);
        user.setPassword(password);
        user.setOrgId(Integer.parseInt(orgId));

        userDao.update(user);
        resp.sendRedirect("page");

    }
}
