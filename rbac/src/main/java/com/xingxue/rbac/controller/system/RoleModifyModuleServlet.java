package com.xingxue.rbac.controller.system;

import com.xingxue.rbac.service.RoleService;
import com.xingxue.rbac.service.impl.RoleServiceImpl;
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
@WebServlet("/system/role/modifymodule")
public class RoleModifyModuleServlet extends HttpServlet {
    private RoleService roleService = new RoleServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roleIdStr = req.getParameter("roleId");
        String[] modudleIdStr = req.getParameterValues("moduleId");

        // 进行修改
        roleService.modifyRoleModule(Integer.parseInt(roleIdStr), StringUtil.strToInt(modudleIdStr));

        // 修改成功，回到分页页面
        resp.sendRedirect("all");
    }
}
