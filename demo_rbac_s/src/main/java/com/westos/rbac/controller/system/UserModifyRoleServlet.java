package com.westos.rbac.controller.system;

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
@WebServlet("/system/user/modifyrole")
public class UserModifyRoleServlet extends HttpServlet {
    UserDaoImpl userDaoimpl = new UserDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer userId = Integer.valueOf(req.getParameter("userId"));
        String[] roles = req.getParameterValues("roles"); // 字符串的 角色编号 集合

        int[] roleIds = new int[roles.length];
        for (int i = 0; i < roles.length; i++) {
            roleIds[i] = Integer.valueOf(roles[i]);
        }

        // 保存至数据库， 先删除中间表中旧的记录，再添加表单传递过来的新记录
        userDaoimpl.deleteUserRole(userId);
        for (int roleId : roleIds) {
            userDaoimpl.insertUserRole(userId, roleId);
        }
        resp.sendRedirect("/system/user/page");
    }
}
