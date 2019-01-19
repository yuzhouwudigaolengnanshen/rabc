package com.xingxue.rbac.controller.system;

import com.xingxue.rbac.service.UserService;
import com.xingxue.rbac.service.impl.UserServiceImpl;
import com.xingxue.rbac.util.StringUtil;

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
    private UserService userService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdStr = req.getParameter("userId");
        String[] roleIdStr = req.getParameterValues("roleId");

        // 进行修改
        userService.modifyRoles(Integer.parseInt(userIdStr), StringUtil.strToInt(roleIdStr));

        // 修改成功，回到分页页面
        resp.sendRedirect("page");
    }
}
