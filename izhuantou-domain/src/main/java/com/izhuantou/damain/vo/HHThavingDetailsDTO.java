package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class HHThavingDetailsDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4362526752493544002L;

    private String xtTime;

    /**
     * 借款时间(满标放款时间)
     */
    private String loanDay;

    /**
     * normal为普通标的financetransfer财务债转标的
     */
    private String financetransfertype;
    /**
     * OID
     */
    private String OID;

    /**
     * 标的状态（0：未发布；1：在投；2：还款中；3：已完成；4：已到期；5：逾期；6：满标未发）
     */
    private String productStatus;

    /**
     * 借款编号（借款申请审批后形成编号）
     */
    private String jkmc;

    /**
     * 年利率
     */
    private BigDecimal jkll;

    /**
     * 标的金额（实际投标金额）
     */
    private BigDecimal jkje;

    /**
     * 期限（月）
     */
    private Integer term;

    /**
     * 已还期数
     */

    private Integer yhnum;

    private String debitCreditOID;

    public String getXtTime() {
	return xtTime;
    }

    public void setXtTime(String xtTime) {
	this.xtTime = xtTime;
    }

    public String getLoanDay() {
	return loanDay;
    }

    public void setLoanDay(String loanDay) {
	this.loanDay = loanDay;
    }

    public String getFinancetransfertype() {
	return financetransfertype;
    }

    public void setFinancetransfertype(String financetransfertype) {
	this.financetransfertype = financetransfertype;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(String productStatus) {
	this.productStatus = productStatus;
    }

    public String getJkmc() {
	return jkmc;
    }

    public void setJkmc(String jkmc) {
	this.jkmc = jkmc;
    }

    public BigDecimal getJkll() {
	return jkll;
    }

    public void setJkll(BigDecimal jkll) {
	this.jkll = jkll;
    }

    public BigDecimal getJkje() {
	return jkje;
    }

    public void setJkje(BigDecimal jkje) {
	this.jkje = jkje;
    }

    public Integer getTerm() {
	return term;
    }

    public void setTerm(Integer term) {
	this.term = term;
    }

    public Integer getYhnum() {
	return yhnum;
    }

    public void setYhnum(Integer yhnum) {
	this.yhnum = yhnum;
    }

    public String getDebitCreditOID() {
	return debitCreditOID;
    }

    public void setDebitCreditOID(String debitCreditOID) {
	this.debitCreditOID = debitCreditOID;
    }

    @Override
    public String toString() {
	return "HHThavingDetailsDTO [xtTime=" + xtTime + ", loanDay=" + loanDay + ", financetransfertype="
		+ financetransfertype + ", OID=" + OID + ", productStatus=" + productStatus + ", jkmc=" + jkmc
		+ ", jkll=" + jkll + ", jkje=" + jkje + ", term=" + term + ", yhnum=" + yhnum + ", debitCreditOID="
		+ debitCreditOID + "]";
    }

}
