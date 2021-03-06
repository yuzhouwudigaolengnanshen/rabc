package com.westos.rbac.controller.system;

import com.westos.rbac.dao.UserDao;
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
@WebServlet("/system/user/toupdate")
public class UserToUpdateServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取要修改的用户id
        String userId = req.getParameter("userId");

        // 查询用户旧的信息
        User user = userDao.findById(Integer.valueOf(userId));
        req.setAttribute("user", user);
        // 跳转至修改表单
        req.getRequestDispatcher("/jsp/system/user/toupdate.jsp").forward(req, resp);
    }
}
