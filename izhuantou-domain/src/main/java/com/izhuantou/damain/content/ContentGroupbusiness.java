package com.izhuantou.damain.content;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 内容组管理实体
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "content_groupbusiness")
public class ContentGroupbusiness extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = -8648750535068447762L;
    /**
       *
       */
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 父结点OID
     */
    @Column(name = "parentOID")
    private String parentOID;

    /**
     * 子结点结点Class
     */
    @Column(name = "leafEntity")
    private String leafEntity;

    /**
     * 名称
     */
    private String name;
    /**
     * 中文名称
     */
    @Column(name = "nameCN")
    private String nameCN;

    /**
     * 角色
     */
    private String role;

    /**
     * 链接URL
     */
    @Column(name = "linkPath")
    private String linkPath;

    /**
     * 实体类
     */
    @Column(name = "entityClass")
    private String entityClass;
    /**
     * 树实体控制类
     */
    @Column(name = "entityControlClass")
    private String entityControlClass;

    /**
     * 关联模板名称
     */
    @Column(name = "linkTempletName")
    private String linkTempletName;
    /**
     * 编码排序
     */
    @Column(name = "NO")
    private Integer NO;

    /**
     * 描述
     */
    private String describe0;

    /**
     * 是否有效
     */
    private Boolean valid;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 是否更新
     */
    private Boolean refresh;

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getParentOID() {
	return parentOID;
    }

    public void setParentOID(String parentOID) {
	this.parentOID = parentOID;
    }

    public String getLeafEntity() {
	return leafEntity;
    }

    public void setLeafEntity(String leafEntity) {
	this.leafEntity = leafEntity;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getNameCN() {
	return nameCN;
    }

    public void setNameCN(String nameCN) {
	this.nameCN = nameCN;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public String getLinkPath() {
	return linkPath;
    }

    public void setLinkPath(String linkPath) {
	this.linkPath = linkPath;
    }

    public String getEntityClass() {
	return entityClass;
    }

    public void setEntityClass(String entityClass) {
	this.entityClass = entityClass;
    }

    public String getEntityControlClass() {
	return entityControlClass;
    }

    public void setEntityControlClass(String entityControlClass) {
	this.entityControlClass = entityControlClass;
    }

    public String getLinkTempletName() {
	return linkTempletName;
    }

    public void setLinkTempletName(String linkTempletName) {
	this.linkTempletName = linkTempletName;
    }

    public Integer getNO() {
	return NO;
    }

    public void setNO(Integer nO) {
	NO = nO;
    }

    public String getDescribe0() {
	return describe0;
    }

    public void setDescribe0(String describe0) {
	this.describe0 = describe0;
    }

    public Boolean getValid() {
	return valid;
    }

    public void setValid(Boolean valid) {
	this.valid = valid;
    }

    public Integer getVersion() {
	return version;
    }

    public void setVersion(Integer version) {
	this.version = version;
    }

    public Boolean getRefresh() {
	return refresh;
    }

    public void setRefresh(Boolean refresh) {
	this.refresh = refresh;
    }

    @Override
    public String toString() {
	return "ContentGroupbusiness [OID=" + OID + ", parentOID=" + parentOID + ", leafEntity=" + leafEntity
		+ ", name=" + name + ", nameCN=" + nameCN + ", role=" + role + ", linkPath=" + linkPath
		+ ", entityClass=" + entityClass + ", entityControlClass=" + entityControlClass + ", linkTempletName="
		+ linkTempletName + ", NO=" + NO + ", describe0=" + describe0 + ", valid=" + valid + ", version="
		+ version + ", refresh=" + refresh + "]";
    }

}
