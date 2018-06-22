package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class RepayMentDTO implements Serializable{

   
    /**
	 * 
	 */
	private static final long serialVersionUID = -2711671332469121727L;
	
	/**
     * 还款计划的时间，真正借款的时间
     */
    private String loandatetime;
    /**
     * 期数
     */
    private Integer term;
    /**
     * 利息
     */
    private BigDecimal interestMoney;
    /**
     * 利息合计
     */
    private BigDecimal totalInterestMoney;
    /**
     * 利率
     */
    private BigDecimal rate;
    /**
     *  剩余本金
     */
    private BigDecimal surplusPrincipal;
    /**
     * 本金
     */
    private BigDecimal principalMoney;
    /**
     * 产品名称
     */
    private String businessName;
    /**
     * WEBP2P_BiddingExamine.applyAmount money, 申请金额（借款的金额）
     */
    private BigDecimal money;
    /**
     * 年利率
     */
    private BigDecimal debitRate;
    /**
     * PAY_RepayPlan.money
     */
    private BigDecimal allmoney;
    /**
     * 还款日期
     */
    private String repayDate;
    /**
     * 还款计划的OID
     */
    private String planOID;
    /**
     * 产品OID
     */
    private String businessOID;
    /**
     * 管理费
     */
    private BigDecimal manageMoney;
    /**
     * 服务费
     */
    private BigDecimal serviceMoney;

	public Integer getTerm() {
		return term;
	}
	public void setTerm(Integer term) {
		this.term = term;
	}
	public BigDecimal getInterestMoney() {
		return interestMoney;
	}
	public void setInterestMoney(BigDecimal interestMoney) {
		this.interestMoney = interestMoney;
	}
	public BigDecimal getTotalInterestMoney() {
		return totalInterestMoney;
	}
	public void setTotalInterestMoney(BigDecimal totalInterestMoney) {
		this.totalInterestMoney = totalInterestMoney;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public BigDecimal getSurplusPrincipal() {
		return surplusPrincipal;
	}
	public void setSurplusPrincipal(BigDecimal surplusPrincipal) {
		this.surplusPrincipal = surplusPrincipal;
	}
	public BigDecimal getPrincipalMoney() {
		return principalMoney;
	}
	public void setPrincipalMoney(BigDecimal principalMoney) {
		this.principalMoney = principalMoney;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public BigDecimal getDebitRate() {
		return debitRate;
	}
	public void setDebitRate(BigDecimal debitRate) {
		this.debitRate = debitRate;
	}
	public BigDecimal getAllmoney() {
		return allmoney;
	}
	public void setAllmoney(BigDecimal allmoney) {
		this.allmoney = allmoney;
	}
	public String getPlanOID() {
		return planOID;
	}
	public void setPlanOID(String planOID) {
		this.planOID = planOID;
	}
	public String getBusinessOID() {
		return businessOID;
	}
	public void setBusinessOID(String businessOID) {
		this.businessOID = businessOID;
	}
	public BigDecimal getManageMoney() {
		return manageMoney;
	}
	public void setManageMoney(BigDecimal manageMoney) {
		this.manageMoney = manageMoney;
	}
	public BigDecimal getServiceMoney() {
		return serviceMoney;
	}
	public void setServiceMoney(BigDecimal serviceMoney) {
		this.serviceMoney = serviceMoney;
	}
	public String getLoandatetime() {
		return loandatetime;
	}
	public void setLoandatetime(String loandatetime) {
		this.loandatetime = loandatetime;
	}
	public String getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}
	@Override
	public String toString() {
		return "RepayMentDTO [loandatetime=" + loandatetime + ", term=" + term + ", interestMoney=" + interestMoney
				+ ", totalInterestMoney=" + totalInterestMoney + ", rate=" + rate + ", surplusPrincipal="
				+ surplusPrincipal + ", principalMoney=" + principalMoney + ", businessName=" + businessName
				+ ", money=" + money + ", debitRate=" + debitRate + ", allmoney=" + allmoney + ", repayDate="
				+ repayDate + ", planOID=" + planOID + ", businessOID=" + businessOID + ", manageMoney=" + manageMoney
				+ ", serviceMoney=" + serviceMoney + "]";
	}

}
