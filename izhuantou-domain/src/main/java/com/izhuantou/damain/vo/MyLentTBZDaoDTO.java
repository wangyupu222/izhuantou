package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class MyLentTBZDaoDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2937777831124684819L;

    /**
     * 标的名称
     */
    private String biddingName;

    /**
     * 产品债权池类型（0：未分配；1：新手；2：散标；3：团标；4：债转；）
     */
    private Integer productType;

    /**
     * debitCreditOID
     */
    private String cond;

    /**
     * 借款时间(满标放款时间)
     */
    private String loanDay;

    /**
     * 
     */
    private boolean fullscale;
    /**
     * 标的状态（0：未发布；1：在投；2：已完成；3：已到期；4：逾期；5：满标）
     */
    private String hkzt;

    /**
     * 出借时间
     */
    private String cjsjDate;

    /**
     * 下个汇款日
     */
    private String xghkr;

    /**
     * 到期时间
     */
    private String dqsj;
    /**
     * 在投新手标id
     */
    private String biOID;

    /**
     * 
     */
    private String dOID;

    /**
     * 出借金额
     */
    private BigDecimal cjje;

    /**
     * 年化利率
     */
    private BigDecimal nhll;
    /**
     * 应收本息
     */
    private BigDecimal ysbx;

    /**
     * 剩余返还其数
     */
    private BigDecimal syqs;
    /**
     * 加息时间
     */
    private String jssj;
    /**
     * 期数
     */
    private Integer tatalTerm;
    /**
     * 查看协议使用
     */
    private String charge;

    public String getCharge() {
	return charge;
    }

    public void setCharge(String charge) {
	this.charge = charge;
    }

    public String getJssj() {
	return jssj;
    }

    public void setJssj(String jssj) {
	this.jssj = jssj;
    }

    public Integer getTatalTerm() {
	return tatalTerm;
    }

    public void setTatalTerm(Integer tatalTerm) {
	this.tatalTerm = tatalTerm;
    }

    public String getBiddingName() {
	return biddingName;
    }

    public void setBiddingName(String biddingName) {
	this.biddingName = biddingName;
    }

    public Integer getProductType() {
	return productType;
    }

    public void setProductType(Integer productType) {
	this.productType = productType;
    }

    public String getCond() {
	return cond;
    }

    public void setCond(String cond) {
	this.cond = cond;
    }

    public String getLoanDay() {
	return loanDay;
    }

    public void setLoanDay(String loanDay) {
	this.loanDay = loanDay;
    }

    public boolean isFullscale() {
	return fullscale;
    }

    public void setFullscale(boolean fullscale) {
	this.fullscale = fullscale;
    }

    public String getHkzt() {
	return hkzt;
    }

    public void setHkzt(String hkzt) {
	this.hkzt = hkzt;
    }

    public String getCjsjDate() {
	return cjsjDate;
    }

    public void setCjsjDate(String cjsjDate) {
	this.cjsjDate = cjsjDate;
    }

    public String getXghkr() {
	return xghkr;
    }

    public void setXghkr(String xghkr) {
	this.xghkr = xghkr;
    }

    public String getDqsj() {
	return dqsj;
    }

    public void setDqsj(String dqsj) {
	this.dqsj = dqsj;
    }

    public String getBiOID() {
	return biOID;
    }

    public void setBiOID(String biOID) {
	this.biOID = biOID;
    }

    public String getdOID() {
	return dOID;
    }

    public void setdOID(String dOID) {
	this.dOID = dOID;
    }

    public BigDecimal getCjje() {
	return cjje;
    }

    public void setCjje(BigDecimal cjje) {
	this.cjje = cjje;
    }

    public BigDecimal getNhll() {
	return nhll;
    }

    public void setNhll(BigDecimal nhll) {
	this.nhll = nhll;
    }

    public BigDecimal getYsbx() {
	return ysbx;
    }

    public void setYsbx(BigDecimal ysbx) {
	this.ysbx = ysbx;
    }

    public BigDecimal getSyqs() {
	return syqs;
    }

    public void setSyqs(BigDecimal syqs) {
	this.syqs = syqs;
    }

    @Override
    public String toString() {
	return "MyLentTBZDaoDTO [biddingName=" + biddingName + ", productType=" + productType + ", cond=" + cond
		+ ", loanDay=" + loanDay + ", fullscale=" + fullscale + ", hkzt=" + hkzt + ", cjsjDate=" + cjsjDate
		+ ", xghkr=" + xghkr + ", dqsj=" + dqsj + ", biOID=" + biOID + ", dOID=" + dOID + ", cjje=" + cjje
		+ ", nhll=" + nhll + ", ysbx=" + ysbx + ", syqs=" + syqs + ", jssj=" + jssj + ", tatalTerm=" + tatalTerm
		+ ", charge=" + charge + "]";
    }

}
