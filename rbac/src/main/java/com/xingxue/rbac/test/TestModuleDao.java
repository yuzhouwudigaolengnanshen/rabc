package com.xingxue.rbac.test;

import com.xingxue.rbac.dao.ModuleDao;
import com.xingxue.rbac.dao.impl.ModuleDaoImpl;
import com.xingxue.rbac.entity.Module;

import java.util.List;

/**
 * @author yihang
 */
public class TestModuleDao {

    public static void main(String[] args) {
        ModuleDao dao = new ModuleDaoImpl();
        System.out.println("测试查询所有的模块...");
        List<Module> all = dao.findAll();
        System.out.println("模块总数：" + all.size());

        System.out.println("测试根据角色查询所有模块...");
        List<Module> list = dao.findByRoleId(1);
        System.out.println("系统管理员的模块有：");
        for (Module module : list) {
            System.out.println(module.getName());
        }

    }
}
