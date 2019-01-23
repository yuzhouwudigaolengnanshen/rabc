package com.westos.rbac.dao;

import com.westos.rbac.domain.Module;

import java.util.List;

/**
 * @author yihang
 */
public interface ModuleDao {

    /**
     * 查询所有模块
     * @return 模块集合
     */
    List<Module> findAll();

    /**
     * 查询某一角色的模块
     * @param roleId 角色编号
     * @return 模块集合
     */
    List<Module> findByRoleId(int roleId);

    /**
     * 根据id删除角色模块
     * @param roleId 角色编号
     *
     */
    void deleteByRoleId(int roleId);

    /**
     *  将对应的角色id 和 模块id 添加到表rbac_role_module中
     * @param roleId
     * @param moduleId
     */
    void insertRoleModule(Integer roleId, Integer moduleId);
}
