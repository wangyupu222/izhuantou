package com.izhuantou.damain.lend;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.izhuantou.damain.BasePojo;

/**
 * 
 * @author yangbosen
 *
 */
public class DisplayTBZ extends BasePojo {
    /**
     * 头笔赚列表展示
     */
    private static final long serialVersionUID = 1L;
    /**
     * 表OID
     */
    @JsonProperty("OID")
    private String OID;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 最低出借金额
     */
    private BigDecimal startBidAmount;
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
     * 排序标识
     */
    private String sortp;
    /**
     * 已投金额
     */
    private BigDecimal holdingAmount;
    /**
     * 总金额
     */
    private BigDecimal biddingAmount;
    /**
     * 进度
     */
    private Double jd;
    /**
     * 判断参数
     */
    private Double szds;

    public BigDecimal getBiddingAmount() {
	return biddingAmount;
    }

    public void setBiddingAmount(BigDecimal biddingAmount) {
	this.biddingAmount = biddingAmount;
    }

    public Double getSzds() {
	return szds;
    }

    public void setSzds(Double szds) {
	this.szds = szds;
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

    public BigDecimal getStartBidAmount() {
	return startBidAmount;
    }

    public void setStartBidAmount(BigDecimal startBidAmount) {
	this.startBidAmount = startBidAmount;
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

    public Double getJd() {
	return jd;
    }

    public void setJd(Double jd) {
	this.jd = jd;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "DisplayTBZ [OID=" + OID + ", xmmc=" + xmmc + ", startBidAmount=" + startBidAmount + ", productStatus="
		+ productStatus + ", nhll=" + nhll + ", cjqx=" + cjqx + ", hkfs=" + hkfs + ", sy=" + sy + ", xmze="
		+ xmze + ", sykt=" + sykt + ", sortp=" + sortp + ", holdingAmount=" + holdingAmount + ", biddingAmount="
		+ biddingAmount + ", jd=" + jd + ", szds=" + szds + "]";
    }

}
