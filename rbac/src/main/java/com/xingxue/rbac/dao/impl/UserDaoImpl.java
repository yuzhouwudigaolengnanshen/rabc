package com.xingxue.rbac.dao.impl;

import com.xingxue.rbac.dao.UserDao;
import com.xingxue.rbac.entity.User;
import com.xingxue.rbac.util.JdbcUtil;
import com.xingxue.rbac.util.StringUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yihang
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User findByUsername(String username) {
        String sql = "SELECT a.ID,a.USERNAME,a.PASSWORD,a.ORG_ID,a.ORG_IDS FROM rbac_user a WHERE a.USERNAME=?";
        ResultSet rs = null;
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.setString(1, username);
                rs = psmt.executeQuery();
                if (rs.next()) {
                    return rsToUser(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(int userId) {
        String sql = "SELECT a.ID,a.USERNAME,a.PASSWORD,a.ORG_ID,a.ORG_IDS FROM rbac_user a WHERE a.ID=?";
        ResultSet rs = null;
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.setInt(1, userId);
                rs = psmt.executeQuery();
                if (rs.next()) {
                    return rsToUser(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将rs转换为User对象
     */
    private User rsToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("ID"));
        user.setUsername(rs.getString("USERNAME"));
        user.setPassword(rs.getString("PASSWORD"));
        user.setOrgId(rs.getInt("ORG_ID"));
        String str = rs.getString("ORG_IDS");
        Integer[] orgIds = StringUtil.split(str);
        user.setOrgIds(orgIds);
        return user;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT a.ID,a.USERNAME,a.PASSWORD,a.ORG_ID,a.ORG_IDS FROM rbac_user a";
        ResultSet rs = null;
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
                rs = psmt.executeQuery();
                List<User> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(rsToUser(rs));
                }
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findByPage(int page, int rows) {
        String sql = "SELECT a.ID,a.USERNAME,a.PASSWORD,a.ORG_ID,a.ORG_IDS FROM rbac_user a LIMIT ?,?";
        ResultSet rs = null;
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.setInt(1, (page - 1) * rows);
                psmt.setInt(2, rows);
                rs = psmt.executeQuery();
                List<User> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(rsToUser(rs));
                }
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int findCount() {
        String sql = "SELECT count(*) FROM RBAC_USER";
        ResultSet rs = null;
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
                rs = psmt.executeQuery();
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO RBAC_USER(ID,USERNAME,PASSWORD,ORG_ID,ORG_IDS) VALUES (RBAC_USER_SEQ.nextval,?,?,?,?)";

        ResultSet rs = null;
        try(Connection conn = JdbcUtil.getConnection()) {
            try (PreparedStatement psmt = conn.prepareStatement(sql, new String[]{"id"});){
                psmt.setString(1, user.getUsername());
                psmt.setString(2, user.getPassword());
                psmt.setInt(3, user.getOrgId());
                String orgIds = StringUtil.join(user.getOrgIds());
                psmt.setString(4, orgIds);
                psmt.executeUpdate();
                // 获取刚刚生成的序列值
                rs = psmt.getGeneratedKeys();
                rs.next();
                user.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE RBAC_USER SET PASSWORD=?, ORG_ID=?, ORG_IDS=? WHERE ID=?";
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
            psmt.setString(1, user.getPassword());
            psmt.setInt(2, user.getOrgId());
            String orgIds = StringUtil.join(user.getOrgIds());
            psmt.setString(3, orgIds);
            psmt.setInt(4, user.getId());
            psmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int userId) {
        String sql = "DELETE FROM RBAC_USER WHERE ID=?";
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.setInt(1, userId);
                psmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUserRole(int userId) {
        String sql = "DELETE FROM RBAC_USER_ROLE WHERE USER_ID=?";
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
            psmt.setInt(1, userId);
            psmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertUserRole(int userId, int roleId) {
        String sql = "INSERT INTO RBAC_USER_ROLE (USER_ID, ROLE_ID) VALUES (?,?)";
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.setInt(1, userId);
                psmt.setInt(2, roleId);
                psmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
