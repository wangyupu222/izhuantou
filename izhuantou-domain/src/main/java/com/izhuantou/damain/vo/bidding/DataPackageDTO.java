package com.izhuantou.damain.vo.bidding;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author dear
 * @version 1.0
 */
public class DataPackageDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6952456792908956826L;
    /**
     * 产品OID
     */
    private String businessOID;
    /**
     * 用户OID
     */
    private String memberOID;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 还款方式(等本等息,先息后本)
     */
    private String debitType;
    /**
     * 借款编号（借款申请审批后形成编号）
     */
    private String loanNumber;
    /**
     * 期限（月）
     */
    private Integer repayNumber;
    /**
     * 月 年
     */
    private String repayCycle;
    /**
     * 年利率
     */
    private BigDecimal debitRate;
    /**
     * 标的金额
     */
    private BigDecimal money;
    /**
     * 已投金额
     */
    private BigDecimal holdmoney;
    /**
     * 一次性手续费
     */
    private BigDecimal onceProceduresRate;
    /**
     * 提前还款违约金
     */
    private BigDecimal earlyRepay;
    /**
     * 逾期违约金（天）
     */
    private BigDecimal overdueRate;
    /**
     * 逾期管理费（天）
     */
    private BigDecimal overdueExpenseRate;
    /**
     * 年平台管理费率
     */
    private BigDecimal manageRate;
    /**
     * 年管理咨询费率
     */
    private BigDecimal serviceRate;

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

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDebitType() {
	return debitType;
    }

    public void setDebitType(String debitType) {
	this.debitType = debitType;
    }

    public String getLoanNumber() {
	return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
	this.loanNumber = loanNumber;
    }

    public Integer getRepayNumber() {
	return repayNumber;
    }

    public void setRepayNumber(Integer repayNumber) {
	this.repayNumber = repayNumber;
    }

    public String getRepayCycle() {
	return repayCycle;
    }

    public void setRepayCycle(String repayCycle) {
	this.repayCycle = repayCycle;
    }

    public BigDecimal getDebitRate() {
	return debitRate;
    }

    public void setDebitRate(BigDecimal debitRate) {
	this.debitRate = debitRate;
    }

    public BigDecimal getMoney() {
	return money;
    }

    public void setMoney(BigDecimal money) {
	this.money = money;
    }

    public BigDecimal getHoldmoney() {
	return holdmoney;
    }

    public void setHoldmoney(BigDecimal holdmoney) {
	this.holdmoney = holdmoney;
    }

    public BigDecimal getOnceProceduresRate() {
	return onceProceduresRate;
    }

    public void setOnceProceduresRate(BigDecimal onceProceduresRate) {
	this.onceProceduresRate = onceProceduresRate;
    }

    public BigDecimal getEarlyRepay() {
	return earlyRepay;
    }

    public void setEarlyRepay(BigDecimal earlyRepay) {
	this.earlyRepay = earlyRepay;
    }

    public BigDecimal getOverdueRate() {
	return overdueRate;
    }

    public void setOverdueRate(BigDecimal overdueRate) {
	this.overdueRate = overdueRate;
    }

    public BigDecimal getOverdueExpenseRate() {
	return overdueExpenseRate;
    }

    public void setOverdueExpenseRate(BigDecimal overdueExpenseRate) {
	this.overdueExpenseRate = overdueExpenseRate;
    }

    public BigDecimal getManageRate() {
	return manageRate;
    }

    public void setManageRate(BigDecimal manageRate) {
	this.manageRate = manageRate;
    }

    public BigDecimal getServiceRate() {
	return serviceRate;
    }

    public void setServiceRate(BigDecimal serviceRate) {
	this.serviceRate = serviceRate;
    }

    @Override
    public String toString() {
	return "DataPackageDTO [businessOID=" + businessOID + ", memberOID=" + memberOID + ", name=" + name
		+ ", debitType=" + debitType + ", loanNumber=" + loanNumber + ", repayNumber=" + repayNumber
		+ ", repayCycle=" + repayCycle + ", debitRate=" + debitRate + ", money=" + money + ", holdmoney="
		+ holdmoney + ", onceProceduresRate=" + onceProceduresRate + ", earlyRepay=" + earlyRepay
		+ ", overdueRate=" + overdueRate + ", overdueExpenseRate=" + overdueExpenseRate + ", manageRate="
		+ manageRate + ", serviceRate=" + serviceRate + "]";
    }

}
