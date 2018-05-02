package com.izhuantou.damain.vo.bidding;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author dear
 * @version 1.0
 */
public class ProductInfoDTO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -8915234679209800797L;
    /**
     * 用户OID
     */
    private String memberOID;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品OID
     */
    private String businessOID;
    /**
     * 等本等息"EPEI",按月返"BIAP",一次性"OPI"repaymentType
     */
    private String debitType;
    /**
     * "month" day
     */
    private String returnCycle;
    /**
     * 期数
     */
    private Integer returnNumber;
    /**
     * 年利率
     */
    private BigDecimal creditRate;
    /**
     * 环环投提前赎回手续费（线下）
     */
    private BigDecimal hhtXXtqsh;
    /**
     * 环环投提前赎回手续费（线上）
     */
    private BigDecimal hhtXStqsh;
    /**
     * 点点投债权转让手续费
     */
    private BigDecimal ddtZQZR;

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

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
    }

    public String getDebitType() {
	return debitType;
    }

    public void setDebitType(String debitType) {
	this.debitType = debitType;
    }

    public String getReturnCycle() {
	return returnCycle;
    }

    public void setReturnCycle(String returnCycle) {
	this.returnCycle = returnCycle;
    }

    public Integer getReturnNumber() {
	return returnNumber;
    }

    public void setReturnNumber(Integer returnNumber) {
	this.returnNumber = returnNumber;
    }

    public BigDecimal getCreditRate() {
	return creditRate;
    }

    public void setCreditRate(BigDecimal creditRate) {
	this.creditRate = creditRate;
    }

    public BigDecimal getHhtXXtqsh() {
	return hhtXXtqsh;
    }

    public void setHhtXXtqsh(BigDecimal hhtXXtqsh) {
	this.hhtXXtqsh = hhtXXtqsh;
    }

    public BigDecimal getHhtXStqsh() {
	return hhtXStqsh;
    }

    public void setHhtXStqsh(BigDecimal hhtXStqsh) {
	this.hhtXStqsh = hhtXStqsh;
    }

    public BigDecimal getDdtZQZR() {
	return ddtZQZR;
    }

    public void setDdtZQZR(BigDecimal ddtZQZR) {
	this.ddtZQZR = ddtZQZR;
    }

    @Override
    public String toString() {
	return "ProDuctInfoDTO [memberOID=" + memberOID + ", name=" + name + ", businessOID=" + businessOID
		+ ", debitType=" + debitType + ", returnCycle=" + returnCycle + ", returnNumber=" + returnNumber
		+ ", creditRate=" + creditRate + ", hhtXXtqsh=" + hhtXXtqsh + ", hhtXStqsh=" + hhtXStqsh + ", ddtZQZR="
		+ ddtZQZR + "]";
    }

}
