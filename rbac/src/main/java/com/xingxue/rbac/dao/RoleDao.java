package com.xingxue.rbac.dao;

import com.xingxue.rbac.entity.Role;

import java.util.List;

/**
 * @author yihang
 */
public interface RoleDao {

    /**
     * 查询所有角色
     *
     * @return 角色集合
     */
    List<Role> findAll();

    /**
     * 查询某一用户的角色
     * @param userId 用户编号
     * @return 角色集合
     */
    List<Role> findByUserId(int userId);

    /**
     * 删除某角色的所有模块
     * @param roleId 角色编号
     */
    void deleteRoleModule(int roleId);

    /**
     * 为该角色添加模块
     * @param roleId 角色编号
     * @param moduleId 模块编号
     */
    void insertRoleModule(int roleId, int moduleId);
}
