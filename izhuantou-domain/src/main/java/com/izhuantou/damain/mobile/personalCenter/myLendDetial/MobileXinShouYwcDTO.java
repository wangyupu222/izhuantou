package com.izhuantou.damain.mobile.personalCenter.myLendDetial;

import java.math.BigDecimal;
import java.util.Date;

import com.izhuantou.damain.BasePojo;

public class MobileXinShouYwcDTO extends BasePojo {

    /**
     * 我的出借新手转已完成dto
     */
    private static final long serialVersionUID = 1L;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 标OID
     */
    private String biOID;
    /**
     * 
     */
    private String DOID;
    /**
     * 出借金额
     */
    private BigDecimal cjje;
    /**
     * 出借时间
     */
    private Date cjsjDate;
    /**
     * 年化利率
     */
    private BigDecimal nhll;
    /**
     * 还款状态
     */
    private String hkzt;
    /**
     * 产品类型
     */
    private String prodictType;

    public String getXmmc() {
	return xmmc;
    }

    public void setXmmc(String xmmc) {
	this.xmmc = xmmc;
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

    public BigDecimal getCjje() {
	return cjje;
    }

    public void setCjje(BigDecimal cjje) {
	this.cjje = cjje;
    }

    public Date getCjsjDate() {
	return cjsjDate;
    }

    public void setCjsjDate(Date cjsjDate) {
	this.cjsjDate = cjsjDate;
    }

    public BigDecimal getNhll() {
	return nhll;
    }

    public void setNhll(BigDecimal nhll) {
	this.nhll = nhll;
    }

    public String getHkzt() {
	return hkzt;
    }

    public void setHkzt(String hkzt) {
	this.hkzt = hkzt;
    }

    public String getProdictType() {
	return prodictType;
    }

    public void setProdictType(String prodictType) {
	this.prodictType = prodictType;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileXinShouYwcDTO [xmmc=" + xmmc + ", biOID=" + biOID + ", DOID=" + DOID + ", cjje=" + cjje
		+ ", cjsjDate=" + cjsjDate + ", nhll=" + nhll + ", hkzt=" + hkzt + ", prodictType=" + prodictType + "]";
    }

}
