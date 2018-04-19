package com.izhuantou.damain.pay;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 银行卡更换
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "pay_changecard")
public class PayChangeCard extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 4926612984722295714L;
    /**
     * 生成的OID
     */
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 用户memberOID
     */
    @Column(name = "memberOID")
    private String memberOID;
    /**
     * 申请更换的银行编码
     */
    @Column(name = "bankCodeNew")
    private String bankCodeNew;
    /**
     * 申请更换的银行卡号
     */
    @Column(name = "bankNumNew")
    private String bankNumNew;
    /**
     * 旧的银行编码
     */
    @Column(name = "bankCodeOld")
    private String bankCodeOld;
    /**
     * 旧的银行卡号
     */
    @Column(name = "bankNumOld")
    private String bankNumOld;
    /**
     * 申请状态 0000 ：提交申请失败 1111:提交申请成功
     */
    @Column(name = "requestStatus")
    private String requestStatus;
    /**
     * 成功状态 0000：更换失败 1111:更换成功
     */
    @Column(name = "successStatus")
    private String successStatus;
    /**
     * 申请时填写的手机号
     */
    private String mobile;
    /**
     * 备用字段
     */
    private String remark;
    /**
     * 向富友请求ID
     */
    @Column(name = "requestID")
    private String requestID;
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

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public String getBankCodeNew() {
	return bankCodeNew;
    }

    public void setBankCodeNew(String bankCodeNew) {
	this.bankCodeNew = bankCodeNew;
    }

    public String getBankNumNew() {
	return bankNumNew;
    }

    public void setBankNumNew(String bankNumNew) {
	this.bankNumNew = bankNumNew;
    }

    public String getBankCodeOld() {
	return bankCodeOld;
    }

    public void setBankCodeOld(String bankCodeOld) {
	this.bankCodeOld = bankCodeOld;
    }

    public String getBankNumOld() {
	return bankNumOld;
    }

    public void setBankNumOld(String bankNumOld) {
	this.bankNumOld = bankNumOld;
    }

    public String getRequestStatus() {
	return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
	this.requestStatus = requestStatus;
    }

    public String getSuccessStatus() {
	return successStatus;
    }

    public void setSuccessStatus(String successStatus) {
	this.successStatus = successStatus;
    }

    public String getMobile() {
	return mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }

    public String getRemark() {
	return remark;
    }

    public void setRemark(String remark) {
	this.remark = remark;
    }

    public String getRequestID() {
	return requestID;
    }

    public void setRequestID(String requestID) {
	this.requestID = requestID;
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
	return "PayChangeCard [OID=" + OID + ", memberOID=" + memberOID + ", bankCodeNew=" + bankCodeNew
		+ ", bankNumNew=" + bankNumNew + ", bankCodeOld=" + bankCodeOld + ", bankNumOld=" + bankNumOld
		+ ", requestStatus=" + requestStatus + ", successStatus=" + successStatus + ", mobile=" + mobile
		+ ", remark=" + remark + ", requestID=" + requestID + ", describe0=" + describe0 + ", NO=" + NO
		+ ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid + ", addDateTime="
		+ addDateTime + ", updDateTime=" + updDateTime + ", refresh=" + refresh + ", version=" + version + "]";
    }

}
