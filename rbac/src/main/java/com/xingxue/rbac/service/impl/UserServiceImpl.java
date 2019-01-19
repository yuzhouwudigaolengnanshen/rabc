package com.xingxue.rbac.service.impl;

import com.xingxue.rbac.dao.ModuleDao;
import com.xingxue.rbac.dao.RoleDao;
import com.xingxue.rbac.dao.UserDao;
import com.xingxue.rbac.dao.impl.ModuleDaoImpl;
import com.xingxue.rbac.dao.impl.RoleDaoImpl;
import com.xingxue.rbac.dao.impl.UserDaoImpl;
import com.xingxue.rbac.entity.Module;
import com.xingxue.rbac.entity.Role;
import com.xingxue.rbac.entity.User;
import com.xingxue.rbac.service.UserService;
import com.xingxue.rbac.util.JdbcUtil;

import java.util.List;

/**
 * @author yihang
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    private RoleDao roleDao = new RoleDaoImpl();
    private ModuleDao moduleDao = new ModuleDaoImpl();

    @Override
    public User findByUsername(String username) {

            User user = userDao.findByUsername(username);
            // 没有查询到用户返回null
            if (user == null) {
                return null;
            }
            // 查询用户所有的角色
            List<Role> roles = roleDao.findByUserId(user.getId());
            user.setRoles(roles);
            // 查询每个角色的权限
            for (Role role : roles) {
                List<Module> modules = moduleDao.findByRoleId(role.getId());
                role.setModules(modules);
            }
            return user;

    }

    @Override
    public User findById(int userId) {

            return userDao.findById(userId);

    }

    @Override
    public List<User> findAll() {

            return userDao.findAll();

    }

    @Override
    public List<User> findByPage(int page, int rows) {

            return userDao.findByPage(page, rows);

    }

    @Override
    public int findCount() {

            return userDao.findCount();

    }

    @Override
    public void insert(User user) {

            userDao.insert(user);

    }

    @Override
    public void update(User user) {

            userDao.update(user);

    }

    @Override
    public void delete(int userId) {
        try {
            JdbcUtil.begin();
            userDao.deleteUserRole(userId);
            userDao.delete(userId);
            JdbcUtil.commit();
        } catch (Exception e) {
            JdbcUtil.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifyRoles(int userId, int[] roleIds) {
        try {
            JdbcUtil.begin();
            userDao.deleteUserRole(userId);
            for (int roleId : roleIds) {
                userDao.insertUserRole(userId, roleId);
            }
            JdbcUtil.commit();
        } catch (Exception e) {
            JdbcUtil.rollback();
            throw new RuntimeException(e);
        }
    }
}
