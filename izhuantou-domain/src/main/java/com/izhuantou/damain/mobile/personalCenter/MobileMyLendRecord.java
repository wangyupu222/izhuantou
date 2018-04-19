package com.izhuantou.damain.mobile.personalCenter;

import com.izhuantou.damain.BasePojo;

public class MobileMyLendRecord extends BasePojo {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 年化
     */
    private String yearRate;
    /**
     * 产品OID
     */
    private String businessOID;
    /**
     * 借款编号
     */
    private String loanNumber;
    /**
     * 还款方式
     */
    private String HKFS;
    /**
     * 协议类型
     */
    private String xyType;
    /**
     * ：借款期限
     */
    private String jkqx;
    /**
     * ：金额
     */
    private String loanAmount;
    /**
     * ：开始时间
     */
    private String loanDay;
    /**
     * ：下个还款日
     */
    private String nextday;
    /**
     * ：结束时间
     */
    private String endDate;

    public String getYearRate() {
	return yearRate;
    }

    public void setYearRate(String yearRate) {
	this.yearRate = yearRate;
    }

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
    }

    public String getLoanNumber() {
	return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
	this.loanNumber = loanNumber;
    }

    public String getHKFS() {
	return HKFS;
    }

    public void setHKFS(String hKFS) {
	HKFS = hKFS;
    }

    public String getXyType() {
	return xyType;
    }

    public void setXyType(String xyType) {
	this.xyType = xyType;
    }

    public String getJkqx() {
	return jkqx;
    }

    public void setJkqx(String jkqx) {
	this.jkqx = jkqx;
    }

    public String getLoanAmount() {
	return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
	this.loanAmount = loanAmount;
    }

    public String getLoanDay() {
	return loanDay;
    }

    public void setLoanDay(String loanDay) {
	this.loanDay = loanDay;
    }

    public String getNextday() {
	return nextday;
    }

    public void setNextday(String nextday) {
	this.nextday = nextday;
    }

    public String getEndDate() {
	return endDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileMyLendRecord [yearRate=" + yearRate + ", businessOID=" + businessOID + ", loanNumber="
		+ loanNumber + ", HKFS=" + HKFS + ", xyType=" + xyType + ", jkqx=" + jkqx + ", loanAmount=" + loanAmount
		+ ", loanDay=" + loanDay + ", nextday=" + nextday + ", endDate=" + endDate + "]";
    }

}
