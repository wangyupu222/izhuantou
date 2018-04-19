package com.izhuantou.damain.mobile.personalCenter.myLendDetial;

import java.math.BigDecimal;
import java.util.Date;

import com.izhuantou.damain.BasePojo;

public class MobileDDTYwcDTO extends BasePojo {

    /**
     * 我的出借点点投已完成dto
     */
    private static final long serialVersionUID = 1L;
    /**
     * 还款状态
     */
    private String hkzt;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 出借金额
     */
    private BigDecimal cjje;
    /**
     * 标的OID
     */
    private String biOID;
    /**
     * 债转OID
     */
    private String DOID;
    /**
     * 特权OID
     */
    private String tqOID;
    /**
     * 总期数
     */
    private Integer zqs;
    /**
     * 出借时间
     */
    private Date cjsjTime;
    /**
     * 产品类型
     */
    private String productType;
    /**
     * 年化利率
     */
    private BigDecimal nhll;

    public String getHkzt() {
	return hkzt;
    }

    public void setHkzt(String hkzt) {
	this.hkzt = hkzt;
    }

    public String getXmmc() {
	return xmmc;
    }

    public void setXmmc(String xmmc) {
	this.xmmc = xmmc;
    }

    public BigDecimal getCjje() {
	return cjje;
    }

    public void setCjje(BigDecimal cjje) {
	this.cjje = cjje;
    }

    public String getBiOID() {
	return biOID;
    }

    public void setBiOID(String biOID) {
	this.biOID = biOID;
    }

    public String getDOID() {
	return DOID;
    }

    public void setDOID(String dOID) {
	DOID = dOID;
    }

    public String getTqOID() {
	return tqOID;
    }

    public void setTqOID(String tqOID) {
	this.tqOID = tqOID;
    }

    public Integer getZqs() {
	return zqs;
    }

    public void setZqs(Integer zqs) {
	this.zqs = zqs;
    }

    public Date getCjsjTime() {
	return cjsjTime;
    }

    public void setCjsjTime(Date cjsjTime) {
	this.cjsjTime = cjsjTime;
    }

    public String getProductType() {
	return productType;
    }

    public void setProductType(String productType) {
	this.productType = productType;
    }

    public BigDecimal getNhll() {
	return nhll;
    }

    public void setNhll(BigDecimal nhll) {
	this.nhll = nhll;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileDDTYwcDTO [hkzt=" + hkzt + ", xmmc=" + xmmc + ", cjje=" + cjje + ", biOID=" + biOID + ", DOID="
		+ DOID + ", tqOID=" + tqOID + ", zqs=" + zqs + ", cjsjTime=" + cjsjTime + ", productType=" + productType
		+ ", nhll=" + nhll + "]";
    }

}
