package com.banban.rbac.controller;

import com.banban.rbac.dao.ModuleDao;
import com.banban.rbac.dao.impl.ModuleDaoImpl;
import com.banban.rbac.dao.impl.UserDaoImpl;
import com.banban.rbac.domain.Module;
import com.banban.rbac.domain.Role;
import com.banban.rbac.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDaoImpl userDao = new UserDaoImpl();
    private ModuleDao moduleDao = new ModuleDaoImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // jsp输入的验证码
        String captcha = req.getParameter("captcha");
        // session 中的验证码正确答案
        Object result = req.getSession().getAttribute("result");
        if(captcha != null && ! captcha.equalsIgnoreCase(result.toString())) { // 验证不通过
            req.setAttribute("error", "验证码不正确");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username==null || username.equals("")){
            resp.sendRedirect("/login.jsp");
            return;
        }
        if(password==null || password.equals("")){
            resp.sendRedirect("/login.jsp");
            return;
        }
        // 查询数据库中的用户对象
        User user = userDao.findByUsername(username);

        // 没有查询到
        if(user == null) {
            req.setAttribute("error", "用户不存在");             // 请求转发 作用域  jsp 取值用EL表达式${error}
            req.getRequestDispatcher("/login.jsp").forward(req,resp);

//            req.getSession().setAttribute("error","用户不存在");  //请求重定向 作用域 jsp取值EL表达式取值 ${error}
//            resp.sendRedirect("/login.jsp");


//            String encode = URLEncoder.encode("用户不存在", "utf-8");  //请求重定向参数传递 jsp取值使用 param.参数名
//            resp.sendRedirect("/login.jsp?error="+encode);
            return;
        }
        // 密码不正确
        if(!user.getPassword().equals(password)){
            req.setAttribute("error", "密码错误");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
//            resp.sendRedirect("/login.jsp?error=密码不正确");
            return;
        }
        // 将认证信息存入session作用域
        req.getSession().setAttribute("principal", user);

        //实现动态菜单
        //查看当前用户所拥有得角色 以及角色含有得权限
//        System.out.println("用户名是：" + user.getUsername());
//        for (Role role : user.getRoles()) {
//            System.out.println("角色是：" + role.getName());
//            for (Module module : role.getModules()) {
//                System.out.println("\t权限是:" + module.getName());
//            }
//        }
        // 所有权限信息
        List<Module> allModules = moduleDao.findAll();
        req.getSession().setAttribute("allModules", allModules);

        resp.sendRedirect("/index.jsp");
    }
}
