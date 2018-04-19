package com.izhuantou.damain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 菜单实体类
 *
 * @author fucheng
 * @date 2018-01-17
 */
@Table(name = "tb_roles")
public class Roles extends BasePojo {
    /** 
    *  
    */
    private static final long serialVersionUID = 1L;
    // 唯一主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String oid;
    // 角色名称
    private String name;
    // 角色状态 0 正常 ，1 已删除
    private Integer status;
    // 是否为父节点 0 是 1 不是
    private Integer isParent;
    // 父节点ID
    private String parentId;

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

    @Override
    public String toString() {
	return "Roles [oid=" + oid + ", name=" + name + ", status=" + status + ", isParent=" + isParent + ", parentId="
		+ parentId + "]";
    }

}
