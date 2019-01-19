package com.xingxue.rbac.test;


import com.xingxue.rbac.dao.RoleDao;
import com.xingxue.rbac.dao.impl.RoleDaoImpl;
import com.xingxue.rbac.entity.Role;

import java.util.List;

/**
 * @author yihang
 */
public class TestRoleDao {

    public static void main(String[] args) {
        RoleDao dao = new RoleDaoImpl();
        System.out.println("测试查询所有的角色...");
        List<Role> all = dao.findAll();
        System.out.println("角色总数为：" + all.size());
        System.out.println("测试查询某一用户的角色...");
        List<Role> list = dao.findByUserId(1);
        System.out.println("admin的角色有：");
        for (Role role : list) {
            System.out.println(role.getName());
        }

    }
}
