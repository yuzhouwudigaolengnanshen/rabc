package com.xingxue.rbac.test;

import com.xingxue.rbac.entity.Module;
import com.xingxue.rbac.service.ModuleService;
import com.xingxue.rbac.service.impl.ModuleServiceImpl;

import java.util.List;

/**
 * @author yihang
 */
public class TestModuleService {
    public static void main(String[] args) {
        ModuleService service = new ModuleServiceImpl();
        List<Module> top = service.findAll();
        for (Module m1 : top) {
            System.out.println(m1.getName());
            for (Module m2 : m1.getChildren()) {
                System.out.println("\t"+m2.getName());
            }
        }
    }
}
