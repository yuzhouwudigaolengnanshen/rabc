package com.xingxue.rbac.controller.system;

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
@WebServlet("/system/user/add")
public class UserAddServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String orgIdStr = req.getParameter("orgId");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setOrgId(Integer.parseInt(orgIdStr));
        userService.insert(user);
        resp.sendRedirect("page");
    }
}
