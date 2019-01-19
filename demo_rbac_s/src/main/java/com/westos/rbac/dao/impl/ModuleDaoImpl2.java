package com.westos.rbac.dao.impl;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.westos.rbac.dao.ModuleDao;
import com.westos.rbac.domain.Module;
import com.westos.rbac.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ModuleDaoImpl2 implements ModuleDao {
    @Override
    public List<Module> findAll() {
        try (Connection conn = JdbcUtil.getConnection()) {
            String sql = "select * from rbac_module";
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
                // all 中保存了所有权限
                List<Module> all = new ArrayList<>();
                ResultSet rs = psmt.executeQuery();
                while(rs.next()) {
                    Module module = rsToModule(rs);
                    all.add(module);
                }

                List<Module> list1 = new ArrayList<>();
                // key 存储权限的 id， value 是权限对象自己
                Map<Integer, Module> map = new HashMap<>();
                // 遍历 all， 找到其中一级的权限
                for (Module m : all) {
                    if (m.getPid() == 0) {
                        list1.add(m);
                    }
                    map.put(m.getId(), m);
                }

                // 建立父子关系
                for (Module module : all) {
                    int pid = module.getPid();
                    Module parent = map.get(pid);
                    if(parent != null) {
                        parent.getChildren().add(module);
                    }
                }

                return list1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Module> findByRoleId(int roleId) {
        return null;
    }
    private Module rsToModule(ResultSet rs) throws SQLException {
        Module parent = new Module();
        parent.setId(rs.getInt("id"));
        parent.setName(rs.getString("name"));
        parent.setCode(rs.getString("code"));
        parent.setPid(rs.getInt("pid"));
        return parent;
    }
}
