package com.izhuantou.damain.pay;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 资金池（只团标）
 */
@Table(name = "pay_cashpool")
public class PayCashPool extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = -1185862654136649711L;
    /**
     * OID
     */
    @Id
    @Column(name = "OID")
    private String OID;

    /**
     * 业务OID
     */
    @Column(name = "businessOID")
    private String businessOID;
    /**
     * 出会员OID
     */
    @Column(name = "memberOID")
    private String memberOID;
    /**
     * 操作金额
     */
    private BigDecimal money;
    /**
     * 操作利息
     */
    private BigDecimal interest;

    /**
     * 本金
     */
    @Column(name = "principalMoney")
    private BigDecimal principalMoney;
    /**
     * 利息
     */
    @Column(name = "interestMoney")
    private BigDecimal interestMoney;

    /**
     * 利率
     */
    private BigDecimal rate;
    /**
     * 特权本金
     */
    @Column(name = "privilegePrincipal")
    private BigDecimal privilegePrincipal;
    /**
     * 特权利息
     */
    @Column(name = "privilegeInterest")
    private BigDecimal privilegeInterest;
    /**
     * 状态
     */
    private String state;

    /**
     * 返还次数
     */
    @Column(name = "returnNumber")
    private Integer returnNumber;
    /**
     * 开始时间
     */
    @Column(name = "startDateTime")
    private Date startDateTime;
    /**
     * 结束时间
     */
    @Column(name = "endDateTime")
    private Date endDateTime;
    /**
     * 剩余返还其数
     */
    @Column(name = "returnSurplusNumber")
    private Integer returnSurplusNumber;
    /**
     * 利率
     */
    @Column(name = "creditRate")
    private BigDecimal creditRate;

    /**
     * 贷款类型
     */
    @Column(name = "creditType")
    private String creditType;
    /**
     * 还款周期
     */
    @Column(name = "returnCycle")
    private String returnCycle;
    /**
     * 利息合计
     */
    @Column(name = "totalInterestMoney")
    private BigDecimal totalInterestMoney;
    /**
     * 特权OID
     */
    @Column(name = "tqOID")
    private String tqOID;
    /**
     * 数据来源 app/web
     */
    private String laiyuan;

    /**
     * 出借时间 保存毫秒值
     */
    @Column(name = "addDateTimeStr")
    private String addDateTimeStr;
    /**
     * 额外加息利率
     */
    @Column(name = "JXother")
    private BigDecimal JXother;

    /**
     * 额外加息
     */
    @Column(name = "JXinterest")
    private BigDecimal JXinterest;
    /**
     * 代收特权
     */
    @Column(name = "privilegeIncome")
    private BigDecimal privilegeIncome;
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

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
    }

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public BigDecimal getMoney() {
	return money;
    }

    public void setMoney(BigDecimal money) {
	this.money = money;
    }

    public BigDecimal getInterest() {
	return interest;
    }

    public void setInterest(BigDecimal interest) {
	this.interest = interest;
    }

    public BigDecimal getPrincipalMoney() {
	return principalMoney;
    }

    public void setPrincipalMoney(BigDecimal principalMoney) {
	this.principalMoney = principalMoney;
    }

    public BigDecimal getInterestMoney() {
	return interestMoney;
    }

    public void setInterestMoney(BigDecimal interestMoney) {
	this.interestMoney = interestMoney;
    }

    public BigDecimal getRate() {
	return rate;
    }

    public void setRate(BigDecimal rate) {
	this.rate = rate;
    }

    public BigDecimal getPrivilegePrincipal() {
	return privilegePrincipal;
    }

    public void setPrivilegePrincipal(BigDecimal privilegePrincipal) {
	this.privilegePrincipal = privilegePrincipal;
    }

    public BigDecimal getPrivilegeInterest() {
	return privilegeInterest;
    }

    public void setPrivilegeInterest(BigDecimal privilegeInterest) {
	this.privilegeInterest = privilegeInterest;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public Integer getReturnNumber() {
	return returnNumber;
    }

    public void setReturnNumber(Integer returnNumber) {
	this.returnNumber = returnNumber;
    }

    public Date getStartDateTime() {
	return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
	this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
	return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
	this.endDateTime = endDateTime;
    }

    public Integer getReturnSurplusNumber() {
	return returnSurplusNumber;
    }

    public void setReturnSurplusNumber(Integer returnSurplusNumber) {
	this.returnSurplusNumber = returnSurplusNumber;
    }

    public BigDecimal getCreditRate() {
	return creditRate;
    }

    public void setCreditRate(BigDecimal creditRate) {
	this.creditRate = creditRate;
    }

    public String getCreditType() {
	return creditType;
    }

    public void setCreditType(String creditType) {
	this.creditType = creditType;
    }

    public String getReturnCycle() {
	return returnCycle;
    }

    public void setReturnCycle(String returnCycle) {
	this.returnCycle = returnCycle;
    }

    public BigDecimal getTotalInterestMoney() {
	return totalInterestMoney;
    }

    public void setTotalInterestMoney(BigDecimal totalInterestMoney) {
	this.totalInterestMoney = totalInterestMoney;
    }

    public String getTqOID() {
	return tqOID;
    }

    public void setTqOID(String tqOID) {
	this.tqOID = tqOID;
    }

    public String getLaiyuan() {
	return laiyuan;
    }

    public void setLaiyuan(String laiyuan) {
	this.laiyuan = laiyuan;
    }

    public String getAddDateTimeStr() {
	return addDateTimeStr;
    }

    public void setAddDateTimeStr(String addDateTimeStr) {
	this.addDateTimeStr = addDateTimeStr;
    }

    public BigDecimal getJXother() {
	return JXother;
    }

    public void setJXother(BigDecimal jXother) {
	JXother = jXother;
    }

    public BigDecimal getJXinterest() {
	return JXinterest;
    }

    public void setJXinterest(BigDecimal jXinterest) {
	JXinterest = jXinterest;
    }

    public BigDecimal getPrivilegeIncome() {
	return privilegeIncome;
    }

    public void setPrivilegeIncome(BigDecimal privilegeIncome) {
	this.privilegeIncome = privilegeIncome;
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

    public BigDecimal getRedmoney() {
	return redmoney;
    }

    public void setRedmoney(BigDecimal redmoney) {
	this.redmoney = redmoney;
    }

    private BigDecimal redmoney;

    @Override
    public String toString() {
	return "PayCashPool [OID=" + OID + ", businessOID=" + businessOID + ", memberOID=" + memberOID + ", money="
		+ money + ", interest=" + interest + ", principalMoney=" + principalMoney + ", interestMoney="
		+ interestMoney + ", rate=" + rate + ", privilegePrincipal=" + privilegePrincipal
		+ ", privilegeInterest=" + privilegeInterest + ", state=" + state + ", returnNumber=" + returnNumber
		+ ", startDateTime=" + startDateTime + ", endDateTime=" + endDateTime + ", returnSurplusNumber="
		+ returnSurplusNumber + ", creditRate=" + creditRate + ", creditType=" + creditType + ", returnCycle="
		+ returnCycle + ", totalInterestMoney=" + totalInterestMoney + ", tqOID=" + tqOID + ", laiyuan="
		+ laiyuan + ", addDateTimeStr=" + addDateTimeStr + ", JXother=" + JXother + ", JXinterest=" + JXinterest
		+ ", privilegeIncome=" + privilegeIncome + ", describe0=" + describe0 + ", NO=" + NO + ", addUserOID="
		+ addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid + ", addDateTime=" + addDateTime
		+ ", updDateTime=" + updDateTime + ", refresh=" + refresh + ", version=" + version + ", redmoney="
		+ redmoney + "]";
    }

}
