package com.xingxue.rbac.service.impl;

import java.util.*;

import com.xingxue.rbac.dao.ModuleDao;
import com.xingxue.rbac.dao.impl.ModuleDaoImpl;
import com.xingxue.rbac.entity.Module;
import com.xingxue.rbac.service.ModuleService;
import com.xingxue.rbac.util.JdbcUtil;

/**
 * @author yihang
 */
public class ModuleServiceImpl implements ModuleService {

    private ModuleDao moduleDao = new ModuleDaoImpl();

    @Override
    public List<Module> findAll() {
        try {
            // 所有的模块
            List<Module> all = moduleDao.findAll();
            // 所有模块放入map集合，便于快速查找
            Map<Integer,Module> allMap = new HashMap<>();
            // 顶层（一级）模块
            List<Module> top = new ArrayList<>();
            for (Module module : all) {
            	// 顶层模块放入top集合
                if(module.getPid()==0) {
                    top.add(module);
                }
                allMap.put(module.getId(), module);
            }
            // 建立父子关系
            for(Module module : all) {
            	if(module.getPid() > 0) {
            		allMap.get(module.getPid()).getChildren().add(module);
            	}
            }
            return top;
        }catch (Exception e){
            return  Collections.emptyList();
        }
    }

    @Override
    public List<Module> findByRoleId(int roleId) {
        try {
            return moduleDao.findByRoleId(roleId);
        }catch (Exception e){
            return  Collections.emptyList();
        }
    }
}
