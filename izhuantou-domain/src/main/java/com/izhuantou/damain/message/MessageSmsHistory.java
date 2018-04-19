package com.izhuantou.damain.message;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 短息消息记录
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "message_smshistory")
public class MessageSmsHistory extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = -7256357461743143500L;
    /**
     * OID
     */
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 发送者
     */
    @Column(name = "sendUser")
    private String sendUser;

    /**
     * 接受用户
     */
    @Column(name = "receiveUser")
    private String receiveUser;

    /**
     * 发送短信返回状态
     */
    private String state;

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
    @Column(name = "addUserOID")
    private String addUserOID;
    /**
     * 修改用户OID
     */
    @Column(name = "updUserOID")
    private String updUserOID;
    /**
     * 是否有效
     */

    private Boolean valid;

    /**
     * 添加的时间
     */
    @Column(name = "addDateTime")
    private Date addDateTime;
    /**
     * 更新的时间
     */
    @Column(name = "updDateTime")
    private Date updDateTime;
    /**
     * 是否更新
     */

    private Boolean refresh;
    /**
     * 版本号
     */
    private Integer version;

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

    public String getReceiveUser() {
	return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
	this.receiveUser = receiveUser;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
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

    @Override
    public String toString() {
	return "MessageSMSHistory [OID=" + OID + ", title=" + title + ", content=" + content + ", sendUser=" + sendUser
		+ ", receiveUser=" + receiveUser + ", state=" + state + ", describe0=" + describe0 + ", NO=" + NO
		+ ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid + ", addDateTime="
		+ addDateTime + ", updDateTime=" + updDateTime + ", refresh=" + refresh + ", version=" + version + "]";
    }

}
