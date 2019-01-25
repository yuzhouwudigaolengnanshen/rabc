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


@WebServlet("/system/user/toupdate")
public class UserToUpdateServlet extends HttpServlet {
    private UserDao userDao =  new UserDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("UserId");


        User user = userDao.findById(Integer.parseInt(userId));
        req.setAttribute("user",user);

        req.getRequestDispatcher("/jsp/system/user/toupdate.jsp")
                .forward(req, resp);
    }
}
