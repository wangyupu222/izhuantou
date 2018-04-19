package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class MylendZZTDaoDTO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 5950468301278579519L;

    /**
     * 标的OID
     */
    private String biOID;

    /**
     * 还款方式（文字）
     */
    private String creditType;
    /**
     * 应收本息
     */
    private BigDecimal ysbx;
    /**
     * 剩余返还其数
     */
    private Integer syqs;

    /**
     * 剩余返还其数
     */
    private String DOID;

    /**
     * 状态
     */
    private Integer productType;

    /**
     * 回款状态
     */
    private String hkzt;

    /**
     * 出借金额（本金）
     */
    private BigDecimal cjje;

    /**
     * 团标名称
     */
    private String xmmc;
    /**
     * 年化利率
     */
    private BigDecimal nhll;
    /**
     * 出借开始时间
     */
    private String cjsjDate;

    /**
     * 到期时间
     */
    private String dqsj;
    /**
     * 下个还款日
     */
    private String xghkr;

    public String getBiOID() {
	return biOID;
    }

    public void setBiOID(String biOID) {
	this.biOID = biOID;
    }

    public String getCreditType() {
	return creditType;
    }

    public void setCreditType(String creditType) {
	this.creditType = creditType;
    }

    public BigDecimal getYsbx() {
	return ysbx;
    }

    public void setYsbx(BigDecimal ysbx) {
	this.ysbx = ysbx;
    }

    public Integer getSyqs() {
	return syqs;
    }

    public void setSyqs(Integer syqs) {
	this.syqs = syqs;
    }

    public String getDOID() {
	return DOID;
    }

    public void setDOID(String dOID) {
	DOID = dOID;
    }

    public Integer getProductType() {
	return productType;
    }

    public void setProductType(Integer productType) {
	this.productType = productType;
    }

    public String getHkzt() {
	return hkzt;
    }

    public void setHkzt(String hkzt) {
	this.hkzt = hkzt;
    }

    public BigDecimal getCjje() {
	return cjje;
    }

    public void setCjje(BigDecimal cjje) {
	this.cjje = cjje;
    }

    public String getXmmc() {
	return xmmc;
    }

    public void setXmmc(String xmmc) {
	this.xmmc = xmmc;
    }

    public BigDecimal getNhll() {
	return nhll;
    }

    public void setNhll(BigDecimal nhll) {
	this.nhll = nhll;
    }

    public String getCjsjDate() {
	return cjsjDate;
    }

    public void setCjsjDate(String cjsjDate) {
	this.cjsjDate = cjsjDate;
    }

    public String getDqsj() {
	return dqsj;
    }

    public void setDqsj(String dqsj) {
	this.dqsj = dqsj;
    }

    public String getXghkr() {
	return xghkr;
    }

    public void setXghkr(String xghkr) {
	this.xghkr = xghkr;
    }

    @Override
    public String toString() {
	return "MylendZZTDaoDTO [biOID=" + biOID + ", creditType=" + creditType + ", ysbx=" + ysbx + ", syqs=" + syqs
		+ ", DOID=" + DOID + ", productType=" + productType + ", hkzt=" + hkzt + ", cjje=" + cjje + ", xmmc="
		+ xmmc + ", nhll=" + nhll + ", cjsjDate=" + cjsjDate + ", dqsj=" + dqsj + ", xghkr=" + xghkr + "]";
    }

}
