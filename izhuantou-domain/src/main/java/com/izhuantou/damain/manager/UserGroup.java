package com.izhuantou.damain.manager;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 用户组实体
 * 
 * @author fucheng
 * @data 2018-05-3
 *
 */
@Table(name = "tb_user_group")
public class UserGroup extends BasePojo {
    private static final long serialVersionUID = 1L;

    // 唯一主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String oid;
    // 组织名称
    private String name;
    // 是否为父节点 0 是 1 不是
    private Integer isParent;
    // 父节点ID
    private String parentId;
    // 状态
    private String status;

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

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    @Override
    public String toString() {
	return "UserGroup [oid=" + oid + ", name=" + name + ", isParent=" + isParent + ", parentId=" + parentId
		+ ", status=" + status + "]";
    }

}
