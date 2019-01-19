package com.xingxue.rbac.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 角色类
 * @author yihang
 */
@SuppressWarnings("serial")
public class Role implements Serializable {
    /**
     * 角色编号
     */
    private int id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 模块集合
     */
    private List<Module> modules;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
