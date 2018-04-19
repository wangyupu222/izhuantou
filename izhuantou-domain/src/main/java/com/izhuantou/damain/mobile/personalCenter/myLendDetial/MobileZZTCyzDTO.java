package com.izhuantou.damain.mobile.personalCenter.myLendDetial;

import java.math.BigDecimal;
import java.util.Date;

import com.izhuantou.damain.BasePojo;

public class MobileZZTCyzDTO extends BasePojo {

    /**
     * 转转投持有中列表DTO
     */
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    private Date xtTime;
    /**
     * 标OID
     */
    private String biOID;
    /**
     * 
     */
    private String creditType;
    /**
     * 已收本息
     */
    private BigDecimal ysbx;
    /**
     * 收益期数
     */
    private Integer syqs;
    /**
     * 
     */
    private String DOID;
    /**
     * 产品类型
     */
    private String productType;
    /**
     * 还款状态
     */
    private String hkzt;
    /**
     * 出借金额
     */
    private BigDecimal cjje;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 年化利率
     */
    private BigDecimal nhll;
    /**
     * 出借时间
     */
    private Date cjsjDate;
    /**
     * 
     */
    private Date dqsj;
    /**
     * 下个还款日
     */
    private Date xghkr;

    public Date getXtTime() {

	return xtTime;
    }

    public void setXtTime(Date xtTime) {
	this.xtTime = xtTime;
    }

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

    public String getProductType() {
	return productType;
    }

    public void setProductType(String productType) {
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

    public Date getCjsjDate() {
	return cjsjDate;
    }

    public void setCjsjDate(Date cjsjDate) {
	this.cjsjDate = cjsjDate;
    }

    public Date getDqsj() {
	return dqsj;
    }

    public void setDqsj(Date dqsj) {
	this.dqsj = dqsj;
    }

    public Date getXghkr() {
	return xghkr;
    }

    public void setXghkr(Date xghkr) {
	this.xghkr = xghkr;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileZZTCyzDTO [xtTime=" + xtTime + ", biOID=" + biOID + ", creditType=" + creditType + ", ysbx="
		+ ysbx + ", syqs=" + syqs + ", DOID=" + DOID + ", productType=" + productType + ", hkzt=" + hkzt
		+ ", cjje=" + cjje + ", xmmc=" + xmmc + ", nhll=" + nhll + ", cjsjDate=" + cjsjDate + ", dqsj=" + dqsj
		+ ", xghkr=" + xghkr + "]";
    }

}
