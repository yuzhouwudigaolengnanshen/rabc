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
    private ModuleDao moduleDao  = new ModuleDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 补充跳至修改角色的模块表单的代码
        //拿到了一个id
        String id = req.getParameter("id");

        //查询所有角色
        List<Module> allModules = moduleDao.findAll();
        //
        List<Module> roleModules = moduleDao.findByRoleId(Integer.parseInt(id));

        req.setAttribute("id",id);
        req.setAttribute("allModules",allModules);
        req.setAttribute("roleModules",roleModules);

        req.getRequestDispatcher("/jsp/system/role/tomodifymodule.jsp").forward(req,resp);

    }
}
