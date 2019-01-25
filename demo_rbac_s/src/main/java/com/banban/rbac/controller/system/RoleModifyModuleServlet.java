package com.banban.rbac.controller.system;

import com.banban.rbac.dao.ModuleDao;
import com.banban.rbac.dao.impl.ModuleDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet("/system/role/modifymodule")
public class RoleModifyModuleServlet extends HttpServlet {
    private ModuleDao moduleDao = new ModuleDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String roleId = req.getParameter("roleId");
        System.out.println("角色id为:"+roleId);
        String[] moduleIds = req.getParameterValues("moduleId");
        for (String id : moduleIds) {
            System.out.println("模块id为:"+id);
        }
        //流 字符串数组转整数数组
        List<Integer> modules = Arrays.stream(moduleIds).map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        //删除中间旧记录
        moduleDao.deleteByRoleId(Integer.parseInt(roleId));

        //添加新选中的记录
        for (Integer moduleId : modules) {
            moduleDao.insertRoleModule(Integer.valueOf(roleId),moduleId);
        }

        resp.sendRedirect("/system/role/all");
    }
}
