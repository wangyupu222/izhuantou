package com.izhuantou.damain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 菜单实体类
 *
 * @author fucheng
 * @date 2018-01-06
 */
@Table(name = "tb_menu")
public class Menu extends BasePojo {
    /** 
    *  
    */
    private static final long serialVersionUID = 1L;
    // 唯一主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String oid;
    // 菜单名称
    private String name;
    // 菜单状态 0 正常 ，1 已删除
    private Integer status;
    // 菜单优先级，desc
    @Column(name = "sort_order")
    private Integer sortOrder;
    // 是否为父节点 0 是 1 不是
    @Column(name = "is_parent")
    private Integer isParent;
    // 父节点ID
    @Column(name = "parent_id")
    private String parentId;
    // 如果为叶子节点则对应的页面位置
    @Column(name = "page_url")
    private String pageUrl;

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

    public Integer getStatus() {
	return status;
    }

    public void setStatus(Integer status) {
	this.status = status;
    }

    public Integer getSortOrder() {
	return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
	this.sortOrder = sortOrder;
    }

    public Integer getIsParent() {
	return isParent;
    }

    public void setIsParent(Integer isParent) {
	this.isParent = isParent;
    }

    public String getParentId() {
	return parentId;
    }

    public void setParentId(String parentId) {
	this.parentId = parentId;
    }

    public String getPageUrl() {
	return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
	this.pageUrl = pageUrl;
    }

    @Override
    public String toString() {
	return "Menu [oid=" + oid + ", name=" + name + ", status=" + status + ", sortOrder=" + sortOrder + ", isParent="
		+ isParent + ", parentId=" + parentId + ", pageUrl=" + pageUrl + "]";
    }

}
