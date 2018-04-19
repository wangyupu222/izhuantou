package com.izhuantou.damain.pay;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 体验金
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "pay_customertyj")
public class PayCustomerTyj extends BasePojo {
    /**
     * 
     */
    private static final long serialVersionUID = -4687728417739666171L;
    /**
     * OID
     */
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 登录人OID
     */
    @Column(name = "memberOID")
    private String memberOID;
    /**
     * 登录人手机
     */
    private String mobile;
    /**
     * 体验用户
     */
    @Column(name = "nameCN")
    private String nameCN;

    /**
     * 体验金金额
     */
    @Column(name = "tyjPrice")
    private BigDecimal tyjPrice;

    /**
     * 是否已使用(0获得未到期,1已可以使用,2使用过)
     */
    @Column(name = "isUsed")
    private String isUsed;

    /**
     * 结算对应资金池OID
     */
    @Column(name = "cashPoolOID")
    private String cashPoolOID;

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

    public String getMobile() {
	return mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }

    public String getNameCN() {
	return nameCN;
    }

    public void setNameCN(String nameCN) {
	this.nameCN = nameCN;
    }

    public BigDecimal getTyjPrice() {
	return tyjPrice;
    }

    public void setTyjPrice(BigDecimal tyjPrice) {
	this.tyjPrice = tyjPrice;
    }

    public String getIsUsed() {
	return isUsed;
    }

    public void setIsUsed(String isUsed) {
	this.isUsed = isUsed;
    }

    public String getCashPoolOID() {
	return cashPoolOID;
    }

    public void setCashPoolOID(String cashPoolOID) {
	this.cashPoolOID = cashPoolOID;
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
	return "PayCustomertyj [OID=" + OID + ", memberOID=" + memberOID + ", mobile=" + mobile + ", nameCN=" + nameCN
		+ ", tyjPrice=" + tyjPrice + ", isUsed=" + isUsed + ", cashPoolOID=" + cashPoolOID + ", describe0="
		+ describe0 + ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid="
		+ valid + ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime + ", refresh=" + refresh
		+ ", version=" + version + "]";
    }

}
