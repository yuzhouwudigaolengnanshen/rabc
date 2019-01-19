package com.xingxue.rbac.service;

import com.xingxue.rbac.entity.Role;

import java.util.List;

/**
 * @author yihang
 */
public interface RoleService {
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
     * 为某一角色添加模块
     * @param roleId 角色编号
     * @param moduleIds 要添加的模块编号
     */
    void modifyRoleModule(int roleId, int[] moduleIds);
}
