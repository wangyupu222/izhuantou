package com.izhuantou.damain.lend;

import java.math.BigDecimal;

import com.izhuantou.damain.BasePojo;

/**
 * 环环投标的组成
 * 
 * @author yangbosen
 *
 */
public class HHTTagComponent extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String OID;
    /**
     * 环环投借款金额
     */
    private BigDecimal jkje;
    // private Integer productStatus;
    /**
     * 借款利率
     */
    private BigDecimal jkll;
    /**
     * 借款名称
     */
    private String jkmc;
    /**
     * 借款期数
     */
    private String term;
    /**
     * 已收期数
     */
    private String syqs;
    // private String biddingOID;
    /**
     * 判断是否为债转
     */
    private String debitCreditOID;
    /**
     * 
     */
    private String panduan;

    private String financetransfertype;

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

    public BigDecimal getJkje() {
	return jkje;
    }

    public void setJkje(BigDecimal jkje) {
	this.jkje = jkje;
    }

    public BigDecimal getJkll() {
	return jkll;
    }

    public void setJkll(BigDecimal jkll) {
	this.jkll = jkll;
    }

    public String getJkmc() {
	return jkmc;
    }

    public void setJkmc(String jkmc) {
	this.jkmc = jkmc;
    }

    public String getTerm() {
	return term;
    }

    public void setTerm(String term) {
	this.term = term;
    }

    public String getSyqs() {
	return syqs;
    }

    public void setSyqs(String syqs) {
	this.syqs = syqs;
    }

    /*
     * public String getBiddingOID() { return biddingOID; }
     * 
     * 
     * 
     * public void setBiddingOID(String biddingOID) { this.biddingOID =
     * biddingOID; }
     */

    public String getDebitCreditOID() {
	return debitCreditOID;
    }

    public void setDebitCreditOID(String debitCreditOID) {
	this.debitCreditOID = debitCreditOID;
    }

    public String getPanduan() {
	return panduan;
    }

    public void setPanduan(String panduan) {
	this.panduan = panduan;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "HHTTagComponent [OID=" + OID + ", jkje=" + jkje + ", jkll=" + jkll + ", jkmc=" + jkmc + ", term=" + term
		+ ", syqs=" + syqs + ", debitCreditOID=" + debitCreditOID + ", panduan=" + panduan
		+ ", financetransfertype=" + financetransfertype + "]";
    }

}
