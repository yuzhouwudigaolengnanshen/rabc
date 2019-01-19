package com.xingxue.rbac.service.impl;

import com.xingxue.rbac.dao.RoleDao;
import com.xingxue.rbac.dao.impl.RoleDaoImpl;
import com.xingxue.rbac.entity.Role;
import com.xingxue.rbac.service.RoleService;
import com.xingxue.rbac.util.JdbcUtil;

import java.util.List;

/**
 * @author yihang
 */
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao = new RoleDaoImpl();

    @Override
    public List<Role> findAll() {
            return roleDao.findAll();

    }

    @Override
    public List<Role> findByUserId(int userId) {
            return roleDao.findByUserId(userId);

    }

    @Override
    public void modifyRoleModule(int roleId, int[] moduleIds) {
        try {
            JdbcUtil.begin();
            roleDao.deleteRoleModule(roleId);
            for (int moduleId : moduleIds) {
                roleDao.insertRoleModule(roleId,moduleId);
            }
            JdbcUtil.commit();
        } catch (Exception e) {
            JdbcUtil.rollback();
            throw new RuntimeException(e);
        }
    }
}
