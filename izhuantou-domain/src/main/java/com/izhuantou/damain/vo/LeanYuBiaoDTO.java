package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.util.Date;

public class LeanYuBiaoDTO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -4148542352677300329L;
    /**
     * 借款人IOD
     */
    private String jkrOID;
    /**
     * 借款人名字
     */
    private String jkrxm;
    /**
     * 产品OID
     */
    private String jkrsjh;
    /**
     * 借款人身份证
     */
    private String jkrsfzh;
    /**
     * 产品
     */
    private String biddingOID;
    /**
     * 借款时间(满标放款时间)
     */
    private Date loanDay;

    public String getJkrOID() {
	return jkrOID;
    }

    public void setJkrOID(String jkrOID) {
	this.jkrOID = jkrOID;
    }

    public String getJkrxm() {
	return jkrxm;
    }

    public void setJkrxm(String jkrxm) {
	this.jkrxm = jkrxm;
    }

    public String getJkrsjh() {
	return jkrsjh;
    }

    public void setJkrsjh(String jkrsjh) {
	this.jkrsjh = jkrsjh;
    }

    public String getJkrsfzh() {
	return jkrsfzh;
    }

    public void setJkrsfzh(String jkrsfzh) {
	this.jkrsfzh = jkrsfzh;
    }

    public String getBiddingOID() {
	return biddingOID;
    }

    public void setBiddingOID(String biddingOID) {
	this.biddingOID = biddingOID;
    }

    public Date getLoanDay() {
	return loanDay;
    }

    public void setLoanDay(Date loanDay) {
	this.loanDay = loanDay;
    }

    @Override
    public String toString() {
	return "LeanYuBiaoDTO [jkrOID=" + jkrOID + ", jkrxm=" + jkrxm + ", jkrsjh=" + jkrsjh + ", jkrsfzh=" + jkrsfzh
		+ ", biddingOID=" + biddingOID + ", loanDay=" + loanDay + "]";
    }

}
