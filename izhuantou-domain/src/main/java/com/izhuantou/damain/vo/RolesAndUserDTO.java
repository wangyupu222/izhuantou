package com.izhuantou.damain.vo;

import java.util.ArrayList;
import java.util.List;

import com.izhuantou.damain.BasePojo;
import com.izhuantou.damain.manager.ManagerUser;

/**
 * 角色用户关系
 * 
 * @author Cannon
 *
 */
public class RolesAndUserDTO extends BasePojo {
    private static final long serialVersionUID = 1L;

    // oid
    private String oid;
    // 角色名称
    private String name;
    // 该角色拥有的用户总数
    private Integer count;
    // 该角色下拥有的用户详情
    private List<ManagerUser> userList = new ArrayList<>();

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

    public Integer getCount() {
	return count;
    }

    public void setCount(Integer count) {
	this.count = count;
    }

    public List<ManagerUser> getUserList() {
	return userList;
    }

    public void setUserList(List<ManagerUser> userList) {
	this.userList = userList;
    }

    @Override
    public String toString() {
	return "RolesAndUserDTO [oid=" + oid + ", name=" + name + ", count=" + count + ", userList=" + userList + "]";
    }

}
