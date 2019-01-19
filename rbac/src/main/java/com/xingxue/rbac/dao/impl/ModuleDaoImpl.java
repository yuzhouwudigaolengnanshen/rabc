package com.xingxue.rbac.dao.impl;

import com.xingxue.rbac.dao.ModuleDao;
import com.xingxue.rbac.entity.Module;
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
public class ModuleDaoImpl implements ModuleDao {
    @Override
    public List<Module> findAll() {
        String sql = "SELECT a.ID, a.NAME,a.CODE,a.PID FROM RBAC_MODULE a";

        ResultSet rs = null;
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {

                rs = psmt.executeQuery();
                List<Module> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(rsToModule(rs));
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Module> findByRoleId(int roleId) {
        String sql = "SELECT a.ID, a.NAME,a.CODE,a.PID FROM RBAC_MODULE a INNER JOIN RBAC_ROLE_MODULE b on a.ID=b.MODULE_ID WHERE b.ROLE_ID=?";
        ResultSet rs = null;
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.setInt(1, roleId);
                rs = psmt.executeQuery();
                List<Module> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(rsToModule(rs));
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * 将rs转换为Module对象
     */
    private Module rsToModule(ResultSet rs) throws SQLException {
        Module module = new Module();
        module.setId(rs.getInt("ID"));
        module.setName(rs.getString("NAME"));
        module.setCode(rs.getString("CODE"));
        module.setPid(rs.getInt("PID"));
        return module;
    }
}
