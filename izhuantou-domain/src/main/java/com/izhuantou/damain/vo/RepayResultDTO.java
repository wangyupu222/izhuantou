package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class RepayResultDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1393119094056960639L;
	/**
	 * 借款标题
	 */
	private String businessName;
	/**
	 * 产品OID
	 */
	private String businessOID;
	/**
	 * 借款金额
	 */
	private BigDecimal money;
	/**
	 * 借款利率
	 */
	private BigDecimal debitRate;
	/**
	 * 到期费用
	 */
	private BigDecimal allmoney;
	
	/**
	 * 下个还款日
	 */
	private String nextRepayTime;
	/**
	 * 是否逾期
	 */
	private String yuqiFlag;
	/**
	 * 期数
	 */
	private Integer term;
	/**
	 * 借款日期
	 */
	private String loanTime;
	
	/**
	 * 当期利息 或者是 逾期利息
	 */
	private BigDecimal interestMoney;
	/**
	 * 咨询服务费
	 */
	private BigDecimal serviceMoney;
	/**
	 * 平台管理费
	 */
	private BigDecimal manageMoney;
	/**
	 * 违约金
	 */
	private BigDecimal  penalty;
	/**
	 * 罚息
	 */
	private BigDecimal defaultInterest;
	/**
	 * 应还本金
	 */
	private BigDecimal yhbj;
	/**
	 * 逾期本金
	 */
	private BigDecimal yqbj;
	/**
	 * 逾期管理费
	 */
	private BigDecimal yqglf;
	/**
	 * 还款计划OID
	 */
	private String planOID;
	/**
	 * 逾期或提前还款总计费用
	 */
	private BigDecimal countMoney;
	
	/**
	 * 能否还款开关
	 */
	private String hkFlag;
	
	public String getHkFlag() {
		return hkFlag;
	}
	public void setHkFlag(String hkFlag) {
		this.hkFlag = hkFlag;
	}
	public BigDecimal getCountMoney() {
		return countMoney;
	}
	public void setCountMoney(BigDecimal countMoney) {
		this.countMoney = countMoney;
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
	public BigDecimal getYqglf() {
		return yqglf;
	}
	public void setYqglf(BigDecimal yqglf) {
		this.yqglf = yqglf;
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
	public String getNextRepayTime() {
		return nextRepayTime;
	}
	public void setNextRepayTime(String nextRepayTime) {
		this.nextRepayTime = nextRepayTime;
	}
	public String getYuqiFlag() {
		return yuqiFlag;
	}
	public void setYuqiFlag(String yuqiFlag) {
		this.yuqiFlag = yuqiFlag;
	}
	public Integer getTerm() {
		return term;
	}
	public void setTerm(Integer term) {
		this.term = term;
	}
	public String getLoanTime() {
		return loanTime;
	}
	public void setLoanTime(String loanTime) {
		this.loanTime = loanTime;
	}
	public BigDecimal getYhbj() {
		return yhbj;
	}
	public void setYhbj(BigDecimal yhbj) {
		this.yhbj = yhbj;
	}
	public BigDecimal getInterestMoney() {
		return interestMoney;
	}
	public void setInterestMoney(BigDecimal interestMoney) {
		this.interestMoney = interestMoney;
	}
	public BigDecimal getServiceMoney() {
		return serviceMoney;
	}
	public void setServiceMoney(BigDecimal serviceMoney) {
		this.serviceMoney = serviceMoney;
	}
	public BigDecimal getManageMoney() {
		return manageMoney;
	}
	public void setManageMoney(BigDecimal manageMoney) {
		this.manageMoney = manageMoney;
	}
	public BigDecimal getPenalty() {
		return penalty;
	}
	public void setPenalty(BigDecimal penalty) {
		this.penalty = penalty;
	}
	public BigDecimal getDefaultInterest() {
		return defaultInterest;
	}
	public void setDefaultInterest(BigDecimal defaultInterest) {
		this.defaultInterest = defaultInterest;
	}
	public BigDecimal getYqbj() {
		return yqbj;
	}
	public void setYqbj(BigDecimal yqbj) {
		this.yqbj = yqbj;
	}
	@Override
	public String toString() {
		return "RepayResultDTO [businessName=" + businessName + ", businessOID=" + businessOID + ", money=" + money
				+ ", debitRate=" + debitRate + ", allmoney=" + allmoney + ", nextRepayTime=" + nextRepayTime
				+ ", yuqiFlag=" + yuqiFlag + ", term=" + term + ", loanTime=" + loanTime + ", interestMoney="
				+ interestMoney + ", serviceMoney=" + serviceMoney + ", manageMoney=" + manageMoney + ", penalty="
				+ penalty + ", defaultInterest=" + defaultInterest + ", yhbj=" + yhbj + ", yqbj=" + yqbj + ", yqglf="
				+ yqglf + ", planOID=" + planOID + ", countMoney=" + countMoney + ", hkFlag=" + hkFlag + "]";
	}
	
	
	
	
	
	
}
