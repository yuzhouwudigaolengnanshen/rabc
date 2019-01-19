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
import java.util.List;

/**
 * @author yihang
 */
@WebServlet("/system/user/page")
public class UserPageServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        String rows = req.getParameter("rows");

        int p = 1; // 默认值
        if (page != null) { // 如果传递了 page 参数，则使用传递的 page 参数
            p = Integer.valueOf(page); // null
        }
        int r = 5;
        if (rows != null) {
            r = Integer.valueOf(rows);
        }
        List<User> list = userDao.findByPage(p, r);


        // 计算总页数
        int count = userDao.findCount(); // 查询总数
        int total = (count - 1) / r + 1;


        // 存入作用域并转发
        req.setAttribute("list", list);
        req.setAttribute("total", total);
        req.setAttribute("current", p);
        req.setAttribute("rows",rows);
        req.getRequestDispatcher("/jsp/system/user/page.jsp").forward(req, resp);
    }
}
