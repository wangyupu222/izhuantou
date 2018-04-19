package com.izhuantou.damain.lend;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.izhuantou.damain.BasePojo;

/**
 * 环环投列表展示
 * 
 * @author yangbosen
 *
 */
public class DisplayHHT extends BasePojo {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 年化利率
     */
    private Double nhll;
    /**
     * 出借期限
     */
    private BigDecimal cjqx;
    /**
     * 还款方式
     */
    private String hkfs;
    /**
     * 项目金额
     */
    private BigDecimal xmze;
    /**
     * 剩余可投
     */
    private BigDecimal sykt;
    /**
     * 进度
     */
    private BigDecimal jd;
    /**
     * 产品状态
     */
    private Integer productStatus;
    /**
     * 表OID
     */
    @JsonProperty("OID")
    private String OID;
    /**
     * 标识
     */
    private Integer isRecommend;
    /**
     * 标识
     */
    private Integer szds;
    /**
     * 加息
     */
    private Double JXother;

    public String getXmmc() {
	return xmmc;
    }

    public void setXmmc(String xmmc) {
	this.xmmc = xmmc;
    }

    public Double getNhll() {
	return nhll;
    }

    public void setNhll(Double nhll) {
	this.nhll = nhll;
    }

    public BigDecimal getCjqx() {
	return cjqx;
    }

    public void setCjqx(BigDecimal cjqx) {
	this.cjqx = cjqx;
    }

    public String getHkfs() {
	return hkfs;
    }

    public void setHkfs(String hkfs) {
	this.hkfs = hkfs;
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

    public BigDecimal getJd() {
	return jd;
    }

    public void setJd(BigDecimal jd) {
	this.jd = jd;
    }

    public Integer getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
	this.productStatus = productStatus;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public Integer getIsRecommend() {
	return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
	this.isRecommend = isRecommend;
    }

    public Integer getSzds() {
	return szds;
    }

    public void setSzds(Integer szds) {
	this.szds = szds;
    }

    public Double getJXother() {
	return JXother;
    }

    public void setJXother(Double jXother) {
	JXother = jXother;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "DisplayHHT [xmmc=" + xmmc + ", nhll=" + nhll + ", cjqx=" + cjqx + ", hkfs=" + hkfs + ", xmze=" + xmze
		+ ", sykt=" + sykt + ", jd=" + jd + ", productStatus=" + productStatus + ", OID=" + OID
		+ ", isRecommend=" + isRecommend + ", szds=" + szds + ", JXother=" + JXother + "]";
    }

}
