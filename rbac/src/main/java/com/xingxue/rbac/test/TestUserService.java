package com.xingxue.rbac.test;

import com.xingxue.rbac.entity.Module;
import com.xingxue.rbac.entity.Role;
import com.xingxue.rbac.entity.User;
import com.xingxue.rbac.service.UserService;
import com.xingxue.rbac.service.impl.UserServiceImpl;

/**
 * @author yihang
 */
public class TestUserService {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        User admin = userService.findByUsername("admin");
        System.out.println(admin);
        System.out.println("角色有：");
        for (Role role : admin.getRoles()) {
            System.out.println(role.getName());
            System.out.println("权限有：");
            for (Module module : role.getModules()) {
                System.out.println("\t"+module.getName());
            }
        }
    }
}
