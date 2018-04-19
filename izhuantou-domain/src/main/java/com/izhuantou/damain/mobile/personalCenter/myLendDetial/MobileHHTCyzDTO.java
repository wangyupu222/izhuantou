package com.izhuantou.damain.mobile.personalCenter.myLendDetial;

import java.math.BigDecimal;
import java.util.Date;

import com.izhuantou.damain.BasePojo;

public class MobileHHTCyzDTO extends BasePojo {

    /**
     * 环环投持有中列表
     */
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    private Date xtTime;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 出借金额
     */
    private BigDecimal cjje;
    /**
     * 总期数
     */
    private Integer zqs;
    /**
     * 年化利率
     */
    private BigDecimal nhll;
    /**
     * 出借时间
     */
    private Date cjsjTime;
    /**
     * OID
     */
    private String OID;
    /**
     * 产品信息OID
     */
    private String productInfoOID;
    /**
     * 产品OID
     */
    private String businessOID;
    /**
     * 资金池OID
     */
    private String CashPoolOID;
    /**
     * 特权OID
     */
    private String tqOID;

    public Date getXtTime() {
	return xtTime;
    }

    public void setXtTime(Date xtTime) {
	this.xtTime = xtTime;
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

    public Integer getZqs() {
	return zqs;
    }

    public void setZqs(Integer zqs) {
	this.zqs = zqs;
    }

    public BigDecimal getNhll() {
	return nhll;
    }

    public void setNhll(BigDecimal nhll) {
	this.nhll = nhll;
    }

    public Date getCjsjTime() {
	return cjsjTime;
    }

    public void setCjsjTime(Date cjsjTime) {
	this.cjsjTime = cjsjTime;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getProductInfoOID() {
	return productInfoOID;
    }

    public void setProductInfoOID(String productInfoOID) {
	this.productInfoOID = productInfoOID;
    }

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
    }

    public String getCashPoolOID() {
	return CashPoolOID;
    }

    public void setCashPoolOID(String cashPoolOID) {
	CashPoolOID = cashPoolOID;
    }

    public String getTqOID() {
	return tqOID;
    }

    public void setTqOID(String tqOID) {
	this.tqOID = tqOID;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileHHTCyzDTO [xtTime=" + xtTime + ", xmmc=" + xmmc + ", cjje=" + cjje + ", zqs=" + zqs + ", nhll="
		+ nhll + ", cjsjTime=" + cjsjTime + ", OID=" + OID + ", productInfoOID=" + productInfoOID
		+ ", businessOID=" + businessOID + ", CashPoolOID=" + CashPoolOID + ", tqOID=" + tqOID + "]";
    }

}
