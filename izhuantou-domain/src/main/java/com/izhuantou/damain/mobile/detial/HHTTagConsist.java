package com.izhuantou.damain.mobile.detial;

import com.izhuantou.damain.BasePojo;

/**
 * 环环投标的组成(散列标)
 * 
 * @author yangbosen
 *
 */
public class HHTTagConsist extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 借款名称
     */
    private String jkmc;
    /**
     * jkje：借款金额
     */
    private String jkje;
    /**
     * jkll：借款利率
     */
    private String jkll;
    /**
     * zqs：总期数
     */
    private String zqs;
    /**
     * syqs：剩余期数
     */
    private String syqs;
    /**
     * biddingOID：标的OID
     */
    private String biddingOID;
    /**
     * financetransfertype：是否为债权转让
     */
    private String financetransfertype;
    /**
     * debitCreditOID：借贷关系OID
     */
    private String debitCreditOID;

    public String getJkmc() {
	return jkmc;
    }

    public void setJkmc(String jkmc) {
	this.jkmc = jkmc;
    }

    public String getJkje() {
	return jkje;
    }

    public void setJkje(String jkje) {
	this.jkje = jkje;
    }

    public String getJkll() {
	return jkll;
    }

    public void setJkll(String jkll) {
	this.jkll = jkll;
    }

    public String getZqs() {
	return zqs;
    }

    public void setZqs(String zqs) {
	this.zqs = zqs;
    }

    public String getSyqs() {
	return syqs;
    }

    public void setSyqs(String syqs) {
	this.syqs = syqs;
    }

    public String getBiddingOID() {
	return biddingOID;
    }

    public void setBiddingOID(String biddingOID) {
	this.biddingOID = biddingOID;
    }

    public String getFinancetransfertype() {
	return financetransfertype;
    }

    public void setFinancetransfertype(String financetransfertype) {
	this.financetransfertype = financetransfertype;
    }

    public String getDebitCreditOID() {
	return debitCreditOID;
    }

    public void setDebitCreditOID(String debitCreditOID) {
	this.debitCreditOID = debitCreditOID;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "HHTTagConsist [jkmc=" + jkmc + ", jkje=" + jkje + ", jkll=" + jkll + ", zqs=" + zqs + ", syqs=" + syqs
		+ ", biddingOID=" + biddingOID + ", financetransfertype=" + financetransfertype + ", debitCreditOID="
		+ debitCreditOID + "]";
    }

}
