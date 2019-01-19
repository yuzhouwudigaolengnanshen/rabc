package com.xingxue.rbac.dao.impl;

import com.xingxue.rbac.dao.RoleDao;
import com.xingxue.rbac.entity.Role;
import com.xingxue.rbac.util.JdbcUtil;

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
    @Override
    public List<Role> findAll() {
        String sql = "SELECT a.ID, a.NAME FROM RBAC_ROLE a";
        ResultSet rs = null;
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
            rs = psmt.executeQuery();
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
        String sql = "SELECT a.ID, a.NAME FROM RBAC_ROLE a INNER JOIN RBAC_USER_ROLE b ON a.ID=b.ROLE_ID WHERE b.USER_ID=?";
        ResultSet rs = null;
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.setInt(1, userId);
                rs = psmt.executeQuery();
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

    /**
     * 将rs转换为Role对象
     */
    private Role rsToRole(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setId(rs.getInt("ID"));
        role.setName(rs.getString("NAME"));
        return role;
    }

    @Override
    public void deleteRoleModule(int roleId) {
        String sql = "DELETE FROM RBAC_ROLE_MODULE WHERE ROLE_ID=?";
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.setInt(1, roleId);
                psmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertRoleModule(int roleId, int moduleId) {
        String sql = "INSERT INTO RBAC_ROLE_MODULE (ROLE_ID, MODULE_ID) VALUES (?,?)";
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.setInt(1, roleId);
                psmt.setInt(2, moduleId);
                psmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
