package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class LendMoneyDTO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8711273306691717801L;
    /**
     * 总金额
     */
    private BigDecimal totalLoanMoney;
    /**
     * 借款标题
     */
    private String loanNumber;
    /**
     * 借款金额
     */
    private BigDecimal loanAmount;
    /***
     * 借款利率
     */
    private BigDecimal debitRate;
    /**
     * 借款时间
     */
    private String addDateTime;
    /**
     * 到期时间
     */
    private String endDateTime;
    /**
     * 下个还款日
     */
    private String repayDate;
    /**
     * 成功OID(可通过此id查协议)
     */
    private String businessOID;
    /**
     * 为判断状态 现在日期--下个还款日的日期所得天数，
     */
    private Integer dateFlag;

    public Integer getDateFlag() {
	return dateFlag;
    }

    public void setDateFlag(Integer dateFlag) {
	this.dateFlag = dateFlag;
    }

    public BigDecimal getTotalLoanMoney() {
	return totalLoanMoney;
    }

    public void setTotalLoanMoney(BigDecimal totalLoanMoney) {
	this.totalLoanMoney = totalLoanMoney;
    }

    public String getLoanNumber() {
	return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
	this.loanNumber = loanNumber;
    }

    public BigDecimal getLoanAmount() {
	return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
	this.loanAmount = loanAmount;
    }

    public BigDecimal getDebitRate() {
	return debitRate;
    }

    public void setDebitRate(BigDecimal debitRate) {
	this.debitRate = debitRate;
    }

    public String getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(String addDateTime) {
	this.addDateTime = addDateTime;
    }

    public String getEndDateTime() {
	return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
	this.endDateTime = endDateTime;
    }

    public String getRepayDate() {
	return repayDate;
    }

    public void setRepayDate(String repayDate) {
	this.repayDate = repayDate;
    }

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
    }

    @Override
    public String toString() {
	return "LendMoneyDTO [totalLoanMoney=" + totalLoanMoney + ", loanNumber=" + loanNumber + ", loanAmount="
		+ loanAmount + ", debitRate=" + debitRate + ", addDateTime=" + addDateTime + ", endDateTime="
		+ endDateTime + ", repayDate=" + repayDate + ", businessOID=" + businessOID + ", dateFlag=" + dateFlag
		+ "]";
    }

}
