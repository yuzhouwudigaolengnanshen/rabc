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
import java.util.List;

/**
 * @author yihang
 */
@WebServlet({"/system/role","/system/role/all"})
public class RoleAllServlet extends HttpServlet {
    private RoleService roleService = new RoleServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Role> list = roleService.findAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/jsp/system/role/all.jsp").forward(req,resp);
    }
}
