package com.izhuantou.damain.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.izhuantou.damain.BasePojo;

/** 个人中心我的借款借款审核 */
public class MyLendRecord extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String loanNumber;
    private String loanAmount;
    private BigDecimal yearRate;
    private Date addDateTime;
    private String businessOID;
    private Date loanDay;
    private Date nextDay;
    private String loanProductRateInfoID;
    private String jkqx;
    private String isFK;
    private String xyType;
    private String loanSJ;
    private String nextSJ;
    private String endSJ;
    private String hkfs;

    public String getEndSJ() {
	return endSJ;
    }

    public void setEndSJ(String endSJ) {
	this.endSJ = endSJ;
    }

    public String getHkfs() {
	return hkfs;
    }

    public void setHkfs(String hkfs) {
	this.hkfs = hkfs;
    }

    private String memberOID;
    private String idCard;
    private String mobile;
    private String userName;
    private String loanYT;
    private String loanTerm;
    private String first;
    private String second;
    private String OID;
    private int space;

    public int getSpace() {
	return space;
    }

    public void setSpace(int space) {
	this.space = space;
    }

    public String getLoanSJ() {
	return loanSJ;
    }

    public void setLoanSJ(String loanSJ) {
	this.loanSJ = loanSJ;
    }

    public String getNextSJ() {
	return nextSJ;
    }

    public void setNextSJ(String nextSJ) {
	this.nextSJ = nextSJ;
    }

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public String getIdCard() {
	return idCard;
    }

    public void setIdCard(String idCard) {
	this.idCard = idCard;
    }

    public String getMobile() {
	return mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getLoanYT() {
	return loanYT;
    }

    public void setLoanYT(String loanYT) {
	this.loanYT = loanYT;
    }

    public String getLoanTerm() {
	return loanTerm;
    }

    public void setLoanTerm(String loanTerm) {
	this.loanTerm = loanTerm;
    }

    public String getFirst() {
	return first;
    }

    public void setFirst(String first) {
	this.first = first;
    }

    public String getSecond() {
	return second;
    }

    public void setSecond(String second) {
	this.second = second;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getXyType() {
	return xyType;
    }

    public void setXyType(String xyType) {
	this.xyType = xyType;
    }

    public String getLoanProductRateInfoID() {
	return loanProductRateInfoID;
    }

    public void setLoanProductRateInfoID(String loanProductRateInfoID) {
	this.loanProductRateInfoID = loanProductRateInfoID;
    }

    public String getJkqx() {
	return jkqx;
    }

    public void setJkqx(String jkqx) {
	this.jkqx = jkqx;
    }

    public String getIsFK() {
	return isFK;
    }

    public void setIsFK(String isFK) {
	this.isFK = isFK;
    }

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

    public Date getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(Date addDateTime) {
	this.addDateTime = addDateTime;
    }

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
    }

    public Date getLoanDay() {
	return loanDay;
    }

    public void setLoanDay(Date loanDay) {
	this.loanDay = loanDay;
    }

    public Date getNextDay() {
	return nextDay;
    }

    public void setNextDay(Date nextDay) {
	this.nextDay = nextDay;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MyLendRecord [loanNumber=" + loanNumber + ", loanAmount=" + loanAmount + ", yearRate=" + yearRate
		+ ", addDateTime=" + addDateTime + ", businessOID=" + businessOID + ", loanDay=" + loanDay
		+ ", nextDay=" + nextDay + ", loanProductRateInfoID=" + loanProductRateInfoID + ", jkqx=" + jkqx
		+ ", isFK=" + isFK + ", xyType=" + xyType + ", loanSJ=" + loanSJ + ", nextSJ=" + nextSJ + ", endSJ="
		+ endSJ + ", hkfs=" + hkfs + ", memberOID=" + memberOID + ", idCard=" + idCard + ", mobile=" + mobile
		+ ", userName=" + userName + ", loanYT=" + loanYT + ", loanTerm=" + loanTerm + ", first=" + first
		+ ", second=" + second + ", OID=" + OID + ", space=" + space + "]";
    }

}
