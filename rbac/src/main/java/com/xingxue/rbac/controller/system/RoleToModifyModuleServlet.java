package com.xingxue.rbac.controller.system;

import com.xingxue.rbac.entity.Module;
import com.xingxue.rbac.service.ModuleService;
import com.xingxue.rbac.service.impl.ModuleServiceImpl;

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
@WebServlet("/system/role/tomodifymodule")
public class RoleToModifyModuleServlet extends HttpServlet {
    private ModuleService moduleService = new ModuleServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取角色编号
        String roleId = req.getParameter("id");

        // 查询所有模块
        List<Module> modules = moduleService.findAll();
        // 查询该角色所拥有的模块
        List<Module> roleModules = moduleService.findByRoleId(Integer.parseInt(roleId));

        // 仅需要模块的编号集合
        List<Integer> ids = new ArrayList<>();
        for (Module roleModule : roleModules) {
            ids.add(roleModule.getId());
        }

        // 存入作用域
        req.setAttribute("modules", modules);
        req.setAttribute("ids", ids);

        // 转发
        req.getRequestDispatcher("/jsp/system/role/tomodifymodule.jsp").forward(req,resp);
    }
}
