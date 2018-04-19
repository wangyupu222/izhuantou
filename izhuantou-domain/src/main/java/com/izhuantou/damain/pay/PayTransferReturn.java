package com.izhuantou.damain.pay;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 债转资金记录
 * 
 * @author yangbosen
 * @version 1.0
 */
@Table(name = "pay_transferreturn")
public class PayTransferReturn extends BasePojo {
    /**
     * 
     */
    private static final long serialVersionUID = 567754264297010499L;
    /**
     * 业务OID
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
     * 借贷OID
     */
    @Column(name = "debitCreditOID")
    private String debitCreditOID;

    /**
     * 资金池OID
     */
    @Column(name = "cashPoolOID")
    private String cashPoolOID;

    /**
     * 出会员OID
     */
    @Column(name = "outMemberOID")
    private String outMemberOID;
    /**
     * 入会员OID
     */
    @Column(name = "inMemberOID")
    private String inMemberOID;

    /**
     * 贷款类型
     */
    @Column(name = "creditType")
    private String creditType;

    /**
     * 贷款利率
     */
    @Column(name = "creditRate")
    private BigDecimal creditRate;

    /**
     * 总金额
     */
    private BigDecimal money;
    /**
     * 本金
     */
    @Column(name = "principalMoney")
    private BigDecimal principalMoney;
    /**
     * 代付利息
     */
    @Column(name = "advanceInterest")
    private BigDecimal advanceInterest;
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
     * 剩余返还其数
     */
    @Column(name = "returnSurplusNumber")
    private Integer returnSurplusNumber;
    /**
     * 剩余利息
     */
    private BigDecimal interest;
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
     * 转出后的借贷关系OID
     */
    @Column(name = "debitCreditOIDNew")
    private String debitCreditOIDNew;
    /**
     * 状态
     */
    private String state;

    /**
     * 债转借款编号（借款申请审批后形成编号）
     * 
     * @author terry
     */
    @Column(name = "debtLoanNumber")
    private String debtLoanNumber;
    /**
     * 出借时间 精确到毫秒
     */
    @Column(name = "addDateTimeStr")
    private String addDateTimeStr;

    /**
     * 数据来源 app/wep
     */

    private String laiyuan;
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

    public String getDebitCreditOID() {
	return debitCreditOID;
    }

    public void setDebitCreditOID(String debitCreditOID) {
	this.debitCreditOID = debitCreditOID;
    }

    public String getCashPoolOID() {
	return cashPoolOID;
    }

    public void setCashPoolOID(String cashPoolOID) {
	this.cashPoolOID = cashPoolOID;
    }

    public String getOutMemberOID() {
	return outMemberOID;
    }

    public void setOutMemberOID(String outMemberOID) {
	this.outMemberOID = outMemberOID;
    }

    public String getInMemberOID() {
	return inMemberOID;
    }

    public void setInMemberOID(String inMemberOID) {
	this.inMemberOID = inMemberOID;
    }

    public String getCreditType() {
	return creditType;
    }

    public void setCreditType(String creditType) {
	this.creditType = creditType;
    }

    public BigDecimal getCreditRate() {
	return creditRate;
    }

    public void setCreditRate(BigDecimal creditRate) {
	this.creditRate = creditRate;
    }

    public BigDecimal getMoney() {
	return money;
    }

    public void setMoney(BigDecimal money) {
	this.money = money;
    }

    public BigDecimal getPrincipalMoney() {
	return principalMoney;
    }

    public void setPrincipalMoney(BigDecimal principalMoney) {
	this.principalMoney = principalMoney;
    }

    public BigDecimal getAdvanceInterest() {
	return advanceInterest;
    }

    public void setAdvanceInterest(BigDecimal advanceInterest) {
	this.advanceInterest = advanceInterest;
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

    public Integer getReturnSurplusNumber() {
	return returnSurplusNumber;
    }

    public void setReturnSurplusNumber(Integer returnSurplusNumber) {
	this.returnSurplusNumber = returnSurplusNumber;
    }

    public BigDecimal getInterest() {
	return interest;
    }

    public void setInterest(BigDecimal interest) {
	this.interest = interest;
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

    public String getDebitCreditOIDNew() {
	return debitCreditOIDNew;
    }

    public void setDebitCreditOIDNew(String debitCreditOIDNew) {
	this.debitCreditOIDNew = debitCreditOIDNew;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public String getDebtLoanNumber() {
	return debtLoanNumber;
    }

    public void setDebtLoanNumber(String debtLoanNumber) {
	this.debtLoanNumber = debtLoanNumber;
    }

    public String getAddDateTimeStr() {
	return addDateTimeStr;
    }

    public void setAddDateTimeStr(String addDateTimeStr) {
	this.addDateTimeStr = addDateTimeStr;
    }

    public String getLaiyuan() {
	return laiyuan;
    }

    public void setLaiyuan(String laiyuan) {
	this.laiyuan = laiyuan;
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
	return "PAY_TransferReturn [OID=" + OID + ", businessOID=" + businessOID + ", debitCreditOID=" + debitCreditOID
		+ ", cashPoolOID=" + cashPoolOID + ", outMemberOID=" + outMemberOID + ", inMemberOID=" + inMemberOID
		+ ", creditType=" + creditType + ", creditRate=" + creditRate + ", money=" + money + ", principalMoney="
		+ principalMoney + ", advanceInterest=" + advanceInterest + ", privilegePrincipal=" + privilegePrincipal
		+ ", privilegeInterest=" + privilegeInterest + ", returnSurplusNumber=" + returnSurplusNumber
		+ ", interest=" + interest + ", startDateTime=" + startDateTime + ", endDateTime=" + endDateTime
		+ ", debitCreditOIDNew=" + debitCreditOIDNew + ", state=" + state + ", debtLoanNumber=" + debtLoanNumber
		+ ", addDateTimeStr=" + addDateTimeStr + ", laiyuan=" + laiyuan + ", describe0=" + describe0 + ", NO="
		+ NO + ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid
		+ ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime + ", refresh=" + refresh
		+ ", version=" + version + "]";
    }

}
