package com.banban.rbac.controller.system;

import com.banban.rbac.dao.RoleDao;
import com.banban.rbac.dao.impl.RoleDaoImpl;
import com.banban.rbac.domain.Role;

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
@WebServlet("/system/role/all")
public class RoleAllServlet extends HttpServlet {
    RoleDao roleDao =  new RoleDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Role> moduleDaoAll = roleDao.findAll();
        req.getSession().setAttribute("list",moduleDaoAll);
        //System.out.println(moduleDaoAll);
        req.getRequestDispatcher("/jsp/system/role/all.jsp").forward(req,resp);
        //resp.sendRedirect("jsp/system/role/all.jsp");
    }
}
