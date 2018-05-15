package com.izhuantou.damain.message;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 消息相关表实体
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "message_contentbusiness")
public class MessageContentBusiness extends BasePojo {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 表中唯一标识
	 */
	@Id
	@Column(name = "OID")
	private String OID;
	/**
	 * 消息头
	 */
	@Column(name = "title")
	private String title;
	/**
	 * 消息内容
	 */
	@Column(name = "content")
	private String content;
	/**
	 * 发送人
	 */
	@Column(name = "sendUser")
	private String sendUser;
	/**
	 * 发送时间
	 */
	@Column(name = "sendDateTime")
	private Date sendDateTime;
	/**
	 * 接受用户oid(member_menber表中的memberOID)
	 */
	@Column(name = "receiveUserOID")
	private String receiveUserOID;
	/**
	 * 已读时间
	 */
	@Column(name = "receiveDateTime")
	private String receiveDateTime;
	/**
	 * 
	 */
	@Column(name = "businessOID")
	private String businessOID;
	/**
	 * 
	 */
	@Column(name = "businessType")
	private String businessType;
	/**
	 * 
	 */
	private String describe0;
	/**
	 * 
	 */
	@Column(name = "NO")
	private Integer NO;
	/**
	 * 
	 */
	@Column(name = "addUserOID")
	private String addUserOID;
	/**
	 * 
	 */
	@Column(name = "updUserOID")
	private String updUserOID;
	/**
	 * 
	 */
	private Boolean valid;
	/**
	 * 
	 */
	@Column(name = "addDateTime")
	private Date addDateTime;
	/**
	 * 
	 */
	@Column(name = "updDateTime")
	private Date updDateTime;
	/**
	 * 
	 */
	private Boolean refresh;
	/**
	 * 
	 */
	private Integer version;
	/**
	 * 
	 */
	@Column(name = "countMessageAll")
	private Integer countMessageAll;
	/**
	 * 
	 */
	@Column(name = "countMessage")
	private Integer countMessage;

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

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendUser() {
		return sendUser;
	}

	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}

	public Date getSendDateTime() {
		return sendDateTime;
	}

	public void setSendDateTime(Date sendDateTime) {
		this.sendDateTime = sendDateTime;
	}

	public String getReceiveUserOID() {
		return receiveUserOID;
	}

	public void setReceiveUserOID(String receiveUserOID) {
		this.receiveUserOID = receiveUserOID;
	}

	public String getReceiveDateTime() {
		return receiveDateTime;
	}

	public void setReceiveDateTime(String receiveDateTime) {
		this.receiveDateTime = receiveDateTime;
	}

	public String getBusinessOID() {
		return businessOID;
	}

	public void setBusinessOID(String businessOID) {
		this.businessOID = businessOID;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Integer getCountMessageAll() {
		return countMessageAll;
	}

	public void setCountMessageAll(Integer countMessageAll) {
		this.countMessageAll = countMessageAll;
	}

	public Integer getCountMessage() {
		return countMessage;
	}

	public void setCountMessage(Integer countMessage) {
		this.countMessage = countMessage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MESSAGE_ContentBusiness [OID=" + OID + ", title=" + title + ", content=" + content + ", sendUser="
				+ sendUser + ", sendDateTime=" + sendDateTime + ", receiveUserOID=" + receiveUserOID
				+ ", receiveDateTime=" + receiveDateTime + ", businessOID=" + businessOID + ", businessType="
				+ businessType + ", describe0=" + describe0 + ", NO=" + NO + ", addUserOID=" + addUserOID
				+ ", updUserOID=" + updUserOID + ", valid=" + valid + ", addDateTime=" + addDateTime + ", updDateTime="
				+ updDateTime + ", refresh=" + refresh + ", version=" + version + ", countMessageAll=" + countMessageAll
				+ ", countMessage=" + countMessage + "]";
	}

}
