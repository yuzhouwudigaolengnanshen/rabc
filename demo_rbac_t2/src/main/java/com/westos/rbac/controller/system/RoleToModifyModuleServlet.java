package com.westos.rbac.controller.system;

import com.westos.rbac.dao.ModuleDao;
import com.westos.rbac.dao.RoleDao;
import com.westos.rbac.dao.impl.ModuleDaoImpl;
import com.westos.rbac.dao.impl.RoleDaoImpl;
import com.westos.rbac.domain.Module;

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
@WebServlet("/system/role/tomodifymodule")
public class RoleToModifyModuleServlet extends HttpServlet {
    private ModuleDao moduleDao = new ModuleDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取角色 id
        String id = req.getParameter("id");

        // 某一角色的权限集合
        List<Module> roleModules = moduleDao.findByRoleId(Integer.valueOf(id));

        // 获取所有的权限
        List<Module> allModules = moduleDao.findAll();

        req.setAttribute("id", id);
        req.setAttribute("roleModules", roleModules);
        req.setAttribute("allModules", allModules);
        req.getRequestDispatcher("/jsp/system/role/tomodifymodule.jsp").forward(req, resp);
    }
}
