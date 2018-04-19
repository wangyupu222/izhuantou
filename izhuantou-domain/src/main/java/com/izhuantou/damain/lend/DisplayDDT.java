package com.izhuantou.damain.lend;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.izhuantou.damain.BasePojo;

/**
 * 点点投展示
 * 
 * @author yangbosen
 *
 */
public class DisplayDDT extends BasePojo {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 表OID值
     */
    @JsonProperty("OID")
    private String OID;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 产品状态
     */
    private Integer productStatus;
    /**
     * 年化利率
     */
    private Double nhll;
    /**
     * 出借期限
     */
    private Double cjqx;
    /**
     * 还款方式
     */
    private String hkfs;
    /**
     * 投资进度
     */
    private double tzjd;
    /**
     * 年化利率
     */
    private String rate;
    /**
     * 剩余金额
     */
    private BigDecimal sy;
    /**
     * 项目金额
     */
    private BigDecimal xmze;
    /**
     * 剩余可投
     */
    private BigDecimal sykt;
    /**
     * 排序字段
     */
    private String sortp;
    /**
     * 已投金额
     */
    private BigDecimal holdingAmount;
    /**
     * 
     */
    private Integer bdtj1;
    /**
     * 进度
     */
    private Double jd;
    /**
     * 判断标识
     */
    private Integer szds;

    public Integer getSzds() {
	return szds;
    }

    public void setSzds(Integer szds) {
	this.szds = szds;
    }

    public Double getJd() {
	return jd;
    }

    public void setJd(Double jd) {
	this.jd = jd;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getXmmc() {
	return xmmc;
    }

    public void setXmmc(String xmmc) {
	this.xmmc = xmmc;
    }

    public Integer getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
	this.productStatus = productStatus;
    }

    public Double getNhll() {
	return nhll;
    }

    public void setNhll(Double nhll) {
	this.nhll = nhll;
    }

    public Double getCjqx() {
	return cjqx;
    }

    public void setCjqx(Double cjqx) {
	this.cjqx = cjqx;
    }

    public String getHkfs() {
	return hkfs;
    }

    public void setHkfs(String hkfs) {
	this.hkfs = hkfs;
    }

    public double getTzjd() {
	return tzjd;
    }

    public void setTzjd(double tzjd) {
	this.tzjd = tzjd;
    }

    public String getRate() {
	return rate;
    }

    public void setRate(String rate) {
	this.rate = rate;
    }

    public BigDecimal getSy() {
	return sy;
    }

    public void setSy(BigDecimal sy) {
	this.sy = sy;
    }

    public BigDecimal getXmze() {
	return xmze;
    }

    public void setXmze(BigDecimal xmze) {
	this.xmze = xmze;
    }

    public BigDecimal getSykt() {
	return sykt;
    }

    public void setSykt(BigDecimal sykt) {
	this.sykt = sykt;
    }

    public String getSortp() {
	return sortp;
    }

    public void setSortp(String sortp) {
	this.sortp = sortp;
    }

    public BigDecimal getHoldingAmount() {
	return holdingAmount;
    }

    public void setHoldingAmount(BigDecimal holdingAmount) {
	this.holdingAmount = holdingAmount;
    }

    public Integer getBdtj1() {
	return bdtj1;
    }

    public void setBdtj1(Integer bdtj1) {
	this.bdtj1 = bdtj1;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "DisplayDDT [OID=" + OID + ", xmmc=" + xmmc + ", productStatus=" + productStatus + ", nhll=" + nhll
		+ ", cjqx=" + cjqx + ", hkfs=" + hkfs + ", tzjd=" + tzjd + ", rate=" + rate + ", sy=" + sy + ", xmze="
		+ xmze + ", sykt=" + sykt + ", sortp=" + sortp + ", holdingAmount=" + holdingAmount + ", bdtj1=" + bdtj1
		+ ", jd=" + jd + ", szds=" + szds + "]";
    }

}
