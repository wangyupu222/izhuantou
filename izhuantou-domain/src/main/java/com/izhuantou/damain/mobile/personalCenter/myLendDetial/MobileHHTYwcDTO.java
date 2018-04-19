package com.izhuantou.damain.mobile.personalCenter.myLendDetial;

import java.math.BigDecimal;
import java.util.Date;

import com.izhuantou.damain.BasePojo;

public class MobileHHTYwcDTO extends BasePojo {

    /**
     * 我的出借新环环投已完成dto
     */
    private static final long serialVersionUID = 1L;
    /**
     * 总期数
     */
    private String zqs;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 债转OID
     */
    private String DOID;
    /**
     * 出借金额
     */
    private BigDecimal cjje;
    /**
     * 出借时间
     */
    private Date cjsjTime;
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
    /**
     * OID
     */
    private String OID;
    /**
     * 特权OID
     */
    private String tqOID;
    /**
     * 产品OID
     */
    private String businessOID;
    /**
     * 资金池OID
     */
    private String CashPoolOID;

    public String getZqs() {
	return zqs;
    }

    public void setZqs(String zqs) {
	this.zqs = zqs;
    }

    public String getXmmc() {
	return xmmc;
    }

    public void setXmmc(String xmmc) {
	this.xmmc = xmmc;
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

    public Date getCjsjTime() {
	return cjsjTime;
    }

    public void setCjsjTime(Date cjsjTime) {
	this.cjsjTime = cjsjTime;
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

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getTqOID() {
	return tqOID;
    }

    public void setTqOID(String tqOID) {
	this.tqOID = tqOID;
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

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileHHTYwcDTO [zqs=" + zqs + ", xmmc=" + xmmc + ", DOID=" + DOID + ", cjje=" + cjje + ", cjsjTime="
		+ cjsjTime + ", nhll=" + nhll + ", hkzt=" + hkzt + ", prodictType=" + prodictType + ", OID=" + OID
		+ ", tqOID=" + tqOID + ", businessOID=" + businessOID + ", CashPoolOID=" + CashPoolOID + "]";
    }

}
