package com.izhuantou.damain.vo;

import java.util.ArrayList;
import java.util.List;

import com.izhuantou.damain.BasePojo;
import com.izhuantou.damain.manager.UserGroup;

/**
 * 组织树
 *
 * @author fucheng
 * @date 2018-06-4
 */
public class GroupDTO extends BasePojo {

    private static final long serialVersionUID = 1L;
    // oid
    private String oid;
    // 组织名称
    private String name;
    // 下一级组织
    private List<UserGroup> userGroupList = new ArrayList<>();

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

    public List<UserGroup> getUserGroupList() {
	return userGroupList;
    }

    public void setUserGroupList(List<UserGroup> userGroupList) {
	this.userGroupList = userGroupList;
    }

    @Override
    public String toString() {
	return "GroupDTO [oid=" + oid + ", name=" + name + ", userGroupList=" + userGroupList + "]";
    }

}
