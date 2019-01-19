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
import java.util.List;

/**
 * @author yihang
 */
@WebServlet({"/system/user","/system/user/page"})
public class UserPageServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 默认页码
        int page = 1;
        // 默认每页记录数
        int rows = 5;

        // 如果传递了page和rows参数，使用它们覆盖默认值
        String pageStr = req.getParameter("page");
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }
        String rowStr = req.getParameter("rows");
        if (rowStr != null) {
            rows = Integer.parseInt(rowStr);
        }

        // 调用service
        List<User> list = userService.findByPage(page, rows);
        int count = userService.findCount();
        // 计算总页数
        int total = (count - 1) / rows + 1;
        // 将结果存入作用域
        req.setAttribute("list", list);
        req.setAttribute("total", total);
        req.setAttribute("page", page);
        // 转发至jsp
        req.getRequestDispatcher("/jsp/system/user/page.jsp").forward(req,resp);
    }
}
