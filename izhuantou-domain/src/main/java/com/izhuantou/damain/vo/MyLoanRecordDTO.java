package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 借款记录
 * @author  dear
 *
 */
public class MyLoanRecordDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -50801162842904264L;
	/**
	 * 借款编号
	 */
	private String loanNumber;
	/**
	 * 借款金额
	 */
	private String loanAmount;
	/**
	 * 年利率
	 */
	private BigDecimal yearRate;
	/**
	 * 借款时间
	 */
	private String addDateTime;
	/**
	 * 产品oiD
	 */
	private String businessOID;
	/**
	 * 借款时间(满标放款时间)
	 */
	private String loanDay;
	/**
	 * 下个还款日
	 */
	private String nextDay;
	/**
	 * 借款产品类型ID
	 */
	private String loanProductRateInfoID;
	/**
	 * 判断是否逾期用
	 */
	private int space;
	public String getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public BigDecimal getYearRate() {
		return yearRate;
	}
	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}
	public String getAddDateTime() {
		return addDateTime;
	}
	public void setAddDateTime(String addDateTime) {
		this.addDateTime = addDateTime;
	}
	public String getBusinessOID() {
		return businessOID;
	}
	public void setBusinessOID(String businessOID) {
		this.businessOID = businessOID;
	}
	public String getLoanDay() {
		return loanDay;
	}
	public void setLoanDay(String loanDay) {
		this.loanDay = loanDay;
	}
	public String getNextDay() {
		return nextDay;
	}
	public void setNextDay(String nextDay) {
		this.nextDay = nextDay;
	}
	public String getLoanProductRateInfoID() {
		return loanProductRateInfoID;
	}
	public void setLoanProductRateInfoID(String loanProductRateInfoID) {
		this.loanProductRateInfoID = loanProductRateInfoID;
	}
	public int getSpace() {
		return space;
	}
	public void setSpace(int space) {
		this.space = space;
	}
	@Override
	public String toString() {
		return "MyLoanRecordDTO [loanNumber=" + loanNumber + ", loanAmount=" + loanAmount + ", yearRate=" + yearRate
				+ ", addDateTime=" + addDateTime + ", businessOID=" + businessOID + ", loanDay=" + loanDay
				+ ", nextDay=" + nextDay + ", loanProductRateInfoID=" + loanProductRateInfoID + ", space=" + space
				+ "]";
	}
	
	

}
