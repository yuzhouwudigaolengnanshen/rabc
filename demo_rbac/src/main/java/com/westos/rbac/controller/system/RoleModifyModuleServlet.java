package com.westos.rbac.controller.system;

import com.westos.rbac.dao.ModuleDao;
import com.westos.rbac.dao.impl.ModuleDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yihang
 */
@WebServlet("/system/role/modifymodule")
public class RoleModifyModuleServlet extends HttpServlet {

    private ModuleDao moduleDao = new ModuleDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取表单提交的参数
        String roleId = req.getParameter("roleId");
        String[] moduleIds = req.getParameterValues("moduleId");

        List<Integer> modules = Arrays.stream(moduleIds).map(s -> Integer.valueOf(s)).collect(Collectors.toList());

        // 删除中间表旧的记录
        moduleDao.deleteByRoleId(Integer.valueOf(roleId));

        // 添加新选中的记录
        for(Integer moduleId : modules) {
            moduleDao.insertRoleModule(Integer.valueOf(roleId), moduleId);
        }

        resp.sendRedirect("/system/role/all");
    }
}
