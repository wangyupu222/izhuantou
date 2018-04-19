package com.izhuantou.damain.mobile.personalCenter.myLendDetial;

import com.izhuantou.damain.BasePojo;

public class MobileMyLendCYZDTO extends BasePojo {

    /**
     * 个人中心我的出借持有中DTO
     */
    private static final long serialVersionUID = 1L;
    private String cashPoolOID;
    /**
     * 出借时间
     */
    private String cjsjTime;
    /**
     * 产品OID
     */
    private String businessOID;
    /**
     * 年化利率
     */
    private String nhll;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 出借金额
     */
    private String cjje;
    /**
     * 债转OID
     */
    private String DOID;
    /**
     * 债转OID
     */
    private String cond;
    /**
     * 类型("点点","环环","转转")
     */
    private String type;
    /**
     * 加息
     */
    private String JXother;
    /**
     * 总期数
     */
    private String zqs;
    /**
     * OID
     */
    private String OID;
    // biaoOID
    private String biOID;
    /**
     * 产品类型
     */
    private String productType;

    public String getCashPoolOID() {
	return cashPoolOID;
    }

    public void setCashPoolOID(String cashPoolOID) {
	this.cashPoolOID = cashPoolOID;
    }

    public String getBiOID() {
	return biOID;
    }

    public void setBiOID(String biOID) {
	this.biOID = biOID;
    }

    public String getProductType() {
	return productType;
    }

    public void setProductType(String productType) {
	this.productType = productType;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getZqs() {
	return zqs;
    }

    public void setZqs(String zqs) {
	this.zqs = zqs;
    }

    public String getJXother() {
	return JXother;
    }

    public void setJXother(String jXother) {
	JXother = jXother;
    }

    public String getCjsjTime() {
	return cjsjTime;
    }

    public void setCjsjTime(String cjsjTime) {
	this.cjsjTime = cjsjTime;
    }

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
    }

    public String getNhll() {
	return nhll;
    }

    public void setNhll(String nhll) {
	this.nhll = nhll;
    }

    public String getXmmc() {
	return xmmc;
    }

    public void setXmmc(String xmmc) {
	this.xmmc = xmmc;
    }

    public String getCjje() {
	return cjje;
    }

    public void setCjje(String cjje) {
	this.cjje = cjje;
    }

    public String getDOID() {
	return DOID;
    }

    public void setDOID(String dOID) {
	DOID = dOID;
    }

    public String getCond() {
	return cond;
    }

    public void setCond(String cond) {
	this.cond = cond;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileMyLendCYZDTO [cjsjTime=" + cjsjTime + ", businessOID=" + businessOID + ", nhll=" + nhll
		+ ", xmmc=" + xmmc + ", cjje=" + cjje + ", DOID=" + DOID + ", cond=" + cond + ", type=" + type
		+ ", JXother=" + JXother + ", zqs=" + zqs + ", OID=" + OID + ", biOID=" + biOID + ", productType="
		+ productType + "]";
    }

}
