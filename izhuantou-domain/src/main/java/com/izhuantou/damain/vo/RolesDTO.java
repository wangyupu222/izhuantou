package com.izhuantou.damain.vo;

import java.util.ArrayList;
import java.util.List;

import com.izhuantou.damain.BasePojo;
import com.izhuantou.damain.manager.Roles;

/**
 * 角色树
 *
 * @author fucheng
 * @date 2018-05-31
 */
public class RolesDTO extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // oid
    private String oid;
    // 角色名称
    private String name;
    // 下一级角色
    private List<Roles> roleList = new ArrayList<>();

    public String getOid() {
	return oid;
    }

    public void setOid(String oid) {
	this.oid = oid;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<Roles> getRoleList() {
	return roleList;
    }

    public void setRoleList(List<Roles> roleList) {
	this.roleList = roleList;
    }

    @Override
    public String toString() {
	return "RolesDTO [oid=" + oid + ", name=" + name + ", roleList=" + roleList + "]";
    }

}
