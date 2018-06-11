package com.izhuantou.damain.manager;

import java.util.ArrayList;
import java.util.List;

import com.izhuantou.damain.BasePojo;

public class TreeMenu extends BasePojo {
    private static final long serialVersionUID = 1L;
    // oid
    private String oid;
    // 文件名称
    private String name;
    // 模块名称
    private String title;
    // 图标名称
    private String icon;
    // 跳转路径
    private String jump;
    // 下一级菜单
    private List<TreeMenu> menuList = new ArrayList<>();

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

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getIcon() {
	return icon;
    }

    public void setIcon(String icon) {
	this.icon = icon;
    }

    public String getJump() {
	return jump;
    }

    public void setJump(String jump) {
	this.jump = jump;
    }

    public List<TreeMenu> getMenuList() {
	return menuList;
    }

    public void setMenuList(List<TreeMenu> menuList) {
	this.menuList = menuList;
    }

    @Override
    public String toString() {
	return "TreeMenu [oid=" + oid + ", name=" + name + ", title=" + title + ", icon=" + icon + ", jump=" + jump
		+ ", menuList=" + menuList + "]";
    }

}
