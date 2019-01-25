package com.banban.rbac.controller.system;

import com.banban.rbac.dao.ModuleDao;
import com.banban.rbac.dao.impl.ModuleDaoImpl;
import com.banban.rbac.domain.Module;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/system/role/tomodifymodule")
public class RoleToModifyModuleServlet extends HttpServlet {
    private ModuleDao moduleDao  = new ModuleDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //拿到了一个id 角色id roleid
        String id = req.getParameter("id");

        //获取所有的权限
        List<Module> allModules = moduleDao.findAll();
        //某一角色的权限集合
        List<Module> roleModules = moduleDao.findByRoleId(Integer.parseInt(id));

        req.setAttribute("id",id);
        req.setAttribute("allModules",allModules);
        req.setAttribute("roleModules",roleModules);

        req.getRequestDispatcher("/jsp/system/role/tomodifymodule.jsp").forward(req,resp);

    }
}
