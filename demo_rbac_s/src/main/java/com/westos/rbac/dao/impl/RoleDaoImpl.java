package com.westos.rbac.dao.impl;

import com.westos.rbac.dao.ModuleDao;
import com.westos.rbac.dao.RoleDao;
import com.westos.rbac.domain.Module;
import com.westos.rbac.domain.Role;
import com.westos.rbac.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yihang
 */
public class RoleDaoImpl implements RoleDao {
    private ModuleDao moduleDao = new ModuleDaoImpl();

    @Override
    public List<Role> findAll() {
        String sql = "SELECT a.ID, a.NAME FROM RBAC_ROLE a";
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {        
                ResultSet rs = psmt.executeQuery();
                List<Role> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(rsToRole(rs));
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Role> findByUserId(int userId) {
        try(Connection conn = JdbcUtil.getConnection()) {
            String sql = "select * from rbac_user_role a inner join rbac_role b on a.role_id=b.id where a.user_id=?";
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.setInt(1, userId);
                ResultSet rs = psmt.executeQuery();
                List<Role> list = new ArrayList<>();
                while(rs.next()) {
                    Role role = rsToRole(rs);
                    List<Module> modules = moduleDao.findByRoleId(role.getId());
                    role.setModules(modules);
                    list.add(role);
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private Role rsToRole(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setId(rs.getInt("id"));
        role.setName(rs.getString("name"));
        return role;
    }
}
