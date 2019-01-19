package com.xingxue.rbac.entity;

import java.io.Serializable;
import java.util.*;

/**
 * 用户类
 *
 * @author yihang
 */
@SuppressWarnings("serial")
public class User implements Serializable {
    /**
     * 用户编号
     */
    private int id;

    /**
     * 用户登录名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 角色集合
     */
    private List<Role> roles;

    /**
     * 用户所属机构
     */
    private Integer orgId;

    /**
     * 用户能够操作的机构集合
     */
    private Integer[] orgIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer[] getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(Integer[] orgIds) {
        this.orgIds = orgIds;
    }

    public Collection<Integer> getModules(){
        Set<Integer> set = new HashSet<>();
        for (Role role : roles) {
            List<Module> modules = role.getModules();
            for (Module module : modules) {
                set.add(module.getId());
            }
        }
        return set;
    }

    public boolean checkPermission(String uri){
        Set<String> set = new HashSet<>();
        for (Role role : roles) {
            List<Module> modules = role.getModules();
            for (Module module : modules) {
                if(uri.startsWith(module.getCode())) {
                    return true;
                }
            }
        }
        return false;
    }

    public Collection<Integer> getParentModules(){
        Set<Integer> set = new HashSet<>();
        for (Role role : roles) {
            List<Module> modules = role.getModules();
            for (Module module : modules) {
                set.add(module.getPid());
            }
        }
        return set;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", orgId=" + orgId +
                ", orgIds=" + Arrays.toString(orgIds) +
                '}';
    }
}
