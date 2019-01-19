package com.westos.rbac.controller.system;

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


/**
 * @author yihang
 */
@WebServlet("/system/user/add")
public class UserAddServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password"); // 明文密码
        Integer orgId = Integer.valueOf(req.getParameter("orgId"));

        User user = new User();
        user.setUsername(username);

        // md5 散列算法
        // 1. 无论原始密码长度是多少， 最终运算的结果长度固定为 16个 32个16进制字符
        // 2. 不能通过结果逆推
        // 3. 输入内容一样，运算结果一样; 输入内容即使改动了一个字节，运算结果也会不同

        user.setPassword(password);  //暂时取消MD5加密
        user.setOrgId(orgId);

        userDao.insert(user);

        resp.sendRedirect("/system/user/page");

    }
}
