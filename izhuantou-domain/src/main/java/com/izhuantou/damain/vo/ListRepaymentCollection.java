package com.izhuantou.damain.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.izhuantou.damain.BasePojo;

public class ListRepaymentCollection extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Date loandatetime;
    private Integer term;
    private BigDecimal interestMoney;
    private BigDecimal totalInterestMoney;
    private BigDecimal rate;
    private BigDecimal surplusPrincipal;
    private BigDecimal principalMoney;
    private String businessName;
    private BigDecimal money;
    private BigDecimal debitRate;
    private BigDecimal allmoney;
    private Date repayDate;
    private String planOID;
    private String businessOID;
    private BigDecimal manageMoney;
    private BigDecimal serviceMoney;
    private String loanSJ;
    private String nextSJ;
    private BigDecimal oncePay;
    private BigDecimal yhbj;
    private BigDecimal yqlx;

    private String but;
    private String qs;

    public String getBut() {
	return but;
    }

    public void setBut(String but) {
	this.but = but;
    }

    public String getQs() {
	return qs;
    }

    public void setQs(String qs) {
	this.qs = qs;
    }

    public BigDecimal getYqlx() {
	return yqlx;
    }

    public void setYqlx(BigDecimal yqlx) {
	this.yqlx = yqlx;
    }

    public BigDecimal getYhbj() {
	return yhbj;
    }

    public void setYhbj(BigDecimal yhbj) {
	this.yhbj = yhbj;
    }

    /**
     * 逾期管理费
     */
    private BigDecimal yqglf;
    /**
     * 逾期违约金
     */
    private BigDecimal yqwyj;
    /**
     * 逾期罚息
     */
    private BigDecimal yqfx;
    /**
     * 是否逾期
     */
    private String isYQ;
    /**
     * 还款方式
     */
    private String hkfs;
    /**
     * 
     */
    private String loanNumber;
    private BigDecimal wyj;
    /**
     * 
     */
    private String yuqiflag;
    private String isFK;
    // 已还金额
    private BigDecimal yhje;
    // 咨询服务费
    private BigDecimal zxsxf;

    private BigDecimal ptglf;

    public BigDecimal getPtglf() {
	return ptglf;
    }

    public void setPtglf(BigDecimal ptglf) {
	this.ptglf = ptglf;
    }

    public BigDecimal getZxsxf() {
	return zxsxf;
    }

    public void setZxsxf(BigDecimal zxsxf) {
	this.zxsxf = zxsxf;
    }

    public BigDecimal getWyj() {
	return wyj;
    }

    public void setWyj(BigDecimal wyj) {
	this.wyj = wyj;
    }

    public BigDecimal getYhje() {
	return yhje;
    }

    public void setYhje(BigDecimal yhje) {
	this.yhje = yhje;
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

    public BigDecimal getOncePay() {
	return oncePay;
    }

    public void setOncePay(BigDecimal oncePay) {
	this.oncePay = oncePay;
    }

    public String getYuqiflag() {
	return yuqiflag;
    }

    public void setYuqiflag(String yuqiflag) {
	this.yuqiflag = yuqiflag;
    }

    public Date getLoandatetime() {
	return loandatetime;
    }

    public void setLoandatetime(Date loandatetime) {
	this.loandatetime = loandatetime;
    }

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

    public Date getRepayDate() {
	return repayDate;
    }

    public void setRepayDate(Date repayDate) {
	this.repayDate = repayDate;
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

    public BigDecimal getYqglf() {
	return yqglf;
    }

    public void setYqglf(BigDecimal yqglf) {
	this.yqglf = yqglf;
    }

    public BigDecimal getYqwyj() {
	return yqwyj;
    }

    public void setYqwyj(BigDecimal yqwyj) {
	this.yqwyj = yqwyj;
    }

    public BigDecimal getYqfx() {
	return yqfx;
    }

    public void setYqfx(BigDecimal yqfx) {
	this.yqfx = yqfx;
    }

    public String getIsYQ() {
	return isYQ;
    }

    public void setIsYQ(String isYQ) {
	this.isYQ = isYQ;
    }

    public String getHkfs() {
	return hkfs;
    }

    public void setHkfs(String hkfs) {
	this.hkfs = hkfs;
    }

    public String getLoanNumber() {
	return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
	this.loanNumber = loanNumber;
    }

    public String getIsFK() {
	return isFK;
    }

    public void setIsFK(String isFK) {
	this.isFK = isFK;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "ListRepaymentCollection [loandatetime=" + loandatetime + ", term=" + term + ", interestMoney="
		+ interestMoney + ", totalInterestMoney=" + totalInterestMoney + ", rate=" + rate
		+ ", surplusPrincipal=" + surplusPrincipal + ", principalMoney=" + principalMoney + ", businessName="
		+ businessName + ", money=" + money + ", debitRate=" + debitRate + ", allmoney=" + allmoney
		+ ", repayDate=" + repayDate + ", planOID=" + planOID + ", businessOID=" + businessOID
		+ ", manageMoney=" + manageMoney + ", serviceMoney=" + serviceMoney + ", loanSJ=" + loanSJ + ", nextSJ="
		+ nextSJ + ", oncePay=" + oncePay + ", yhbj=" + yhbj + ", yqlx=" + yqlx + ", but=" + but + ", qs=" + qs
		+ ", yqglf=" + yqglf + ", yqwyj=" + yqwyj + ", yqfx=" + yqfx + ", isYQ=" + isYQ + ", hkfs=" + hkfs
		+ ", loanNumber=" + loanNumber + ", wyj=" + wyj + ", yuqiflag=" + yuqiflag + ", isFK=" + isFK
		+ ", yhje=" + yhje + ", zxsxf=" + zxsxf + ", ptglf=" + ptglf + "]";
    }

}
