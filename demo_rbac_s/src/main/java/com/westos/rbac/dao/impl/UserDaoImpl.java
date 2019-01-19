package com.westos.rbac.dao.impl;

import com.westos.rbac.dao.RoleDao;
import com.westos.rbac.dao.UserDao;
import com.westos.rbac.domain.Role;
import com.westos.rbac.domain.User;
import com.westos.rbac.util.JdbcUtil;
import com.westos.rbac.util.StringUtil;

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
public class UserDaoImpl implements UserDao {
    private RoleDao roleDao = new RoleDaoImpl();
    @Override
    public User findByUsername(String username) {
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement("SELECT a.ID,a.USERNAME,a.PASSWORD,a.ORG_ID,a.ORG_IDS FROM rbac_user a WHERE a.USERNAME=?")) {
                psmt.setString(1, username);
                ResultSet rs = psmt.executeQuery();
                if(rs.next()) {
                    User user = rsToUser(rs);
                    List<Role> roles = roleDao.findByUserId(user.getId());
                    user.setRoles(roles);
                    return user;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private User rsToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setOrgId(rs.getInt("org_id"));
        user.setOrgIds(StringUtil.split(rs.getString("org_ids")));
        return user;
    }


    @Override
    public User findById(int userId) {
        String sql = "SELECT a.ID,a.USERNAME,a.PASSWORD,a.ORG_ID,a.ORG_IDS FROM rbac_user a WHERE a.ID=?";
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.setInt(1, userId);
                ResultSet rs = psmt.executeQuery();
                List<User> list = new ArrayList<>();
                if (rs.next()) {
                    return rsToUser(rs);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT a.ID,a.USERNAME,a.PASSWORD,a.ORG_ID,a.ORG_IDS FROM rbac_user a";
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {        
                ResultSet rs = psmt.executeQuery();
                List<User> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(rsToUser(rs));
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<User> findByPage(int page, int rows) {
        String sql = "select * from rbac_user limit ?,?";
        try (Connection conn = JdbcUtil.getConnection()) {
            try (PreparedStatement psmt = conn.prepareStatement(sql)) {
                // 给？赋值  (page-1)*10
                // page = 1  rows = 10   ==> 0
                // page = 2  rows = 10   ==> 10
                // page = 3  ........... ==> 20
                psmt.setInt(1, (page-1) * rows);
                psmt.setInt(2, rows);
                ResultSet rs = psmt.executeQuery();
                List<User> list = new ArrayList<>();
                while(rs.next()) {
                    User user = rsToUser(rs);
                    list.add(user);
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public int findCount() {
        String sql = "SELECT count(*) FROM RBAC_USER";
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {        
                ResultSet rs = psmt.executeQuery();
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO RBAC_USER(ID,USERNAME,PASSWORD,ORG_ID,ORG_IDS) VALUES (null,?,?,?,?)";
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                psmt.setString(1, user.getUsername());
                psmt.setString(2, user.getPassword());
                psmt.setInt(3, user.getOrgId());
                String orgIds = StringUtil.join(user.getOrgIds());
                psmt.setString(4, orgIds);
                psmt.executeUpdate();
                // 获取刚刚生成的序列值
                ResultSet rs = psmt.getGeneratedKeys();
                rs.next();
                user.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    public void deleteUserRole(int userId){
        String sql = "DELETE FROM RBAC_USER_ROLE WHERE USER_ID=?";
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.setInt(1, userId);
                psmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUserRole(int userId, int roleId) {
        String sql = "INSERT INTO RBAC_USER_ROLE (USER_ID, ROLE_ID) VALUES (?,?)";
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.setInt(1, userId);
                psmt.setInt(2, roleId);
                psmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
