package com.izhuantou.damain.p2p;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 紧急公告信息表
 * @author aries
 * @version 1.0
 */
@Table(name="p2p_urgentNotice")
public class P2pUrgentNotice extends BasePojo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2924341199449380743L;
	@Id
    @Column(name = "OID")
    private String OID;
	/**
     * 公告标题
     */
    private String name;
    /**
     * 作者
     */
    private String author;
    /**
     * 类型(1内部 2外部)
     */
    private String type;
	/**
     * 公告内容
     */
    private String messages;
    /**
     * 发布起始时间
     */
    private Date startTime;
    /**
     * 发布终止时间
     */
    private Date endTime;
    /**
     * 描述
     */
    private String describe0;
    /**
     * 序号
     */
    @Column(name = "NO")
    private Integer NO;
    /**
     * 增加用户OID
     */
    @Column(name = "add_user_oid")
    private String addUserOID;

    /**
     * 修改用户OID
     */
    @Column(name = "upd_user_oid")
    private String updUserOID;
    /**
     * 是否有效(0无效 1有效)
     * 执行删除操作时将值修改为0（假删除）
     */
    private Boolean valid;

    /**
     * 添加的时间
     */
    private Date addDateTime;
    /**
     * 更新的时间
     */
    private Date updDateTime;
    /**
     * 是否更新
     */
    private Boolean refresh;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 父级OID
     */
    @Column(name = "parent_oid")
    private String parentOID;

    /**
     * 分支实体
     */
    private String branchEntity;

    /**
     * 数据地址
     */
    private String datePath;

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getDescribe0() {
		return describe0;
	}

	public void setDescribe0(String describe0) {
		this.describe0 = describe0;
	}

	public Integer getNO() {
		return NO;
	}

	public void setNO(Integer nO) {
		NO = nO;
	}

	public String getAddUserOID() {
		return addUserOID;
	}

	public void setAddUserOID(String addUserOID) {
		this.addUserOID = addUserOID;
	}

	public String getUpdUserOID() {
		return updUserOID;
	}

	public void setUpdUserOID(String updUserOID) {
		this.updUserOID = updUserOID;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Date getAddDateTime() {
		return addDateTime;
	}

	public void setAddDateTime(Date addDateTime) {
		this.addDateTime = addDateTime;
	}

	public Date getUpdDateTime() {
		return updDateTime;
	}

	public void setUpdDateTime(Date updDateTime) {
		this.updDateTime = updDateTime;
	}

	public Boolean getRefresh() {
		return refresh;
	}

	public void setRefresh(Boolean refresh) {
		this.refresh = refresh;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getParentOID() {
		return parentOID;
	}

	public void setParentOID(String parentOID) {
		this.parentOID = parentOID;
	}

	public String getBranchEntity() {
		return branchEntity;
	}

	public void setBranchEntity(String branchEntity) {
		this.branchEntity = branchEntity;
	}

	public String getDatePath() {
		return datePath;
	}

	public void setDatePath(String datePath) {
		this.datePath = datePath;
	}

	@Override
	public String toString() {
		return "P2pUrgentNotice [OID=" + OID + ", name=" + name + ", author=" + author + ", type=" + type
				+ ", messages=" + messages + ", startTime=" + startTime + ", endTime=" + endTime + ", describe0="
				+ describe0 + ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid="
				+ valid + ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime + ", refresh=" + refresh
				+ ", version=" + version + ", parentOID=" + parentOID + ", branchEntity=" + branchEntity + ", datePath="
				+ datePath + "]";
	}
    

}
