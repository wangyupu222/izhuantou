package com.izhuantou.damain.pay;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 客户提现相关记录
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "pay_withdrawalrecord")
public class PayWithdrawalRecord extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 5264919519872882822L;

    /**
     * 用户OID
     */
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 用户OID
     */
    @Column(name = "memberOID")
    private String memberOID;

    /**
     * 提现手续费
     */
    private BigDecimal sxf;

    /**
     * 免费额度
     */
    private BigDecimal mfed;

    /**
     * 免费额度之前
     */
    private BigDecimal mfedbefor;
    /**
     * 提现状态 0：不成功 1：成功
     */
    private String status;

    /**
     * 提现金额
     */
    private BigDecimal money;

    /**
     * 提交流水
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
     * 修改时间
     */
    @Column(name = "updDateTime")
    private Date updDateTime;

    /**
     * 增加时间
     */
    @Column(name = "addDateTime")
    private Date addDateTime;
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

    public BigDecimal getSxf() {
	return sxf;
    }

    public void setSxf(BigDecimal sxf) {
	this.sxf = sxf;
    }

    public BigDecimal getMfed() {
	return mfed;
    }

    public void setMfed(BigDecimal mfed) {
	this.mfed = mfed;
    }

    public BigDecimal getMfedbefor() {
	return mfedbefor;
    }

    public void setMfedbefor(BigDecimal mfedbefor) {
	this.mfedbefor = mfedbefor;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public BigDecimal getMoney() {
	return money;
    }

    public void setMoney(BigDecimal money) {
	this.money = money;
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

    public Date getUpdDateTime() {
	return updDateTime;
    }

    public void setUpdDateTime(Date updDateTime) {
	this.updDateTime = updDateTime;
    }

    public Date getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(Date addDateTime) {
	this.addDateTime = addDateTime;
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
	return "PayWithdrawalRecord [OID=" + OID + ", memberOID=" + memberOID + ", sxf=" + sxf + ", mfed=" + mfed
		+ ", mfedbefor=" + mfedbefor + ", status=" + status + ", money=" + money + ", requestID=" + requestID
		+ ", describe0=" + describe0 + ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID="
		+ updUserOID + ", valid=" + valid + ", updDateTime=" + updDateTime + ", addDateTime=" + addDateTime
		+ ", refresh=" + refresh + ", version=" + version + "]";
    }

}
