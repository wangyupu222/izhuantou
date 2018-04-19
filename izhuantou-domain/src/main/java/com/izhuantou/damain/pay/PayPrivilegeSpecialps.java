package com.izhuantou.damain.pay;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 回款触发加息券 特殊加息券补充说明
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "pay_privilegespecialps")
public class PayPrivilegeSpecialps extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1672956523323052082L;

    @Column(name = "OID")
    private String OID;

    /**
     * 出借人OID
     */
    @Column(name = "memberOID")
    private String memberOID;

    /**
     * 对应特权券OID
     */
    @Column(name = "privilegeOID")
    private String privilegeOID;

    /**
     * 出借金额
     */
    private BigDecimal money;

    /**
     * 加息券最低出借金额
     */
    @Column(name = "lowMoney")
    private BigDecimal lowMoney;

    /**
     * 回款日期
     */
    @Column(name = "returnDate")
    private Date returnDate;

    /**
     * 发券日期(结算日前3天)
     */
    @Column(name = "sendDate")
    private Date sendDate;

    /**
     * 未来出借产品OID
     */
    @Column(name = "productInfoOID")
    private String productInfoOID;

    /**
     * 未来出借产品的加息天数
     */
    @Column(name = "JXDay")
    private Integer JXDay;

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
    /**
     * 发券状态 0:未发送 1：已发送
     */
    private String sendType;

    /**
     * 使用状态 0:未使用 1：已使用
     */
    private String isUsed;

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

    public String getPrivilegeOID() {
	return privilegeOID;
    }

    public void setPrivilegeOID(String privilegeOID) {
	this.privilegeOID = privilegeOID;
    }

    public BigDecimal getMoney() {
	return money;
    }

    public void setMoney(BigDecimal money) {
	this.money = money;
    }

    public BigDecimal getLowMoney() {
	return lowMoney;
    }

    public void setLowMoney(BigDecimal lowMoney) {
	this.lowMoney = lowMoney;
    }

    public Date getReturnDate() {
	return returnDate;
    }

    public void setReturnDate(Date returnDate) {
	this.returnDate = returnDate;
    }

    public Date getSendDate() {
	return sendDate;
    }

    public void setSendDate(Date sendDate) {
	this.sendDate = sendDate;
    }

    public String getProductInfoOID() {
	return productInfoOID;
    }

    public void setProductInfoOID(String productInfoOID) {
	this.productInfoOID = productInfoOID;
    }

    public Integer getJXDay() {
	return JXDay;
    }

    public void setJXDay(Integer jXDay) {
	JXDay = jXDay;
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

    public String getSendType() {
	return sendType;
    }

    public void setSendType(String sendType) {
	this.sendType = sendType;
    }

    public String getIsUsed() {
	return isUsed;
    }

    public void setIsUsed(String isUsed) {
	this.isUsed = isUsed;
    }

    @Override
    public String toString() {
	return "PAY_PrivilegeSpecialPS [OID=" + OID + ", memberOID=" + memberOID + ", privilegeOID=" + privilegeOID
		+ ", money=" + money + ", lowMoney=" + lowMoney + ", returnDate=" + returnDate + ", sendDate="
		+ sendDate + ", productInfoOID=" + productInfoOID + ", JXDay=" + JXDay + ", describe0=" + describe0
		+ ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid
		+ ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime + ", refresh=" + refresh
		+ ", version=" + version + ", sendType=" + sendType + ", isUsed=" + isUsed + "]";
    }

}
