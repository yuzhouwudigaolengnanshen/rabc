package com.banban.rbac.controller.system;

import com.banban.rbac.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/system/user/delete")
public class UserDeleteServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userID = req.getParameter("UserId");

        UserDaoImpl userDao = new UserDaoImpl();
        userDao.delete(Integer.parseInt(userID));

        resp.sendRedirect("/system/user/page");

    }
}
