package com.xingxue.rbac.controller.system;

import com.xingxue.rbac.entity.Role;
import com.xingxue.rbac.service.RoleService;
import com.xingxue.rbac.service.impl.RoleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yihang
 */
@WebServlet("/system/user/tomodifyrole")
public class UserToModifyRoleServlet extends HttpServlet {
    private RoleService roleService = new RoleServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询所有角色
        List<Role> roles = roleService.findAll();

        // 查询该用户所有角色
        String idStr = req.getParameter("id");
        List<Role> userRoles = roleService.findByUserId(Integer.parseInt(idStr));
        // 判断时仅需要角色编号集合
        List<Integer> ids = new ArrayList<>();
        for (Role role : userRoles) {
            ids.add(role.getId());
        }

        // 将结果存入作用域
        req.setAttribute("roles", roles);
        req.setAttribute("ids", ids);

        // 跳转
        req.getRequestDispatcher("/jsp/system/user/tomodifyrole.jsp").forward(req,resp);
    }
}
