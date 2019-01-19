package com.westos.rbac.controller.system;

import com.westos.rbac.dao.RoleDao;
import com.westos.rbac.dao.impl.RoleDaoImpl;
import com.westos.rbac.domain.Role;

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
    private RoleDao roleDao = new RoleDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //查询所有角色
        List<Role> roles = roleDao.findAll();

        //查询该用户所有角色
        String userId = req.getParameter("UserId");
        List<Role> userRoles = roleDao.findByUserId(Integer.parseInt(userId));

        req.setAttribute("roles", roles);
        req.setAttribute("userRoles", userRoles); // 1, list(1,2)
        req.setAttribute("userId", userId);
        req.getRequestDispatcher("/jsp/system/user/tomodifyrole.jsp")
                .forward(req, resp);


    }
}
