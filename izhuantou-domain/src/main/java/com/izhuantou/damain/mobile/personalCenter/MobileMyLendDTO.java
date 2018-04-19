package com.izhuantou.damain.mobile.personalCenter;

import com.izhuantou.damain.BasePojo;

/**
 * 个人中心我的出借参数实体
 * 
 * @author yangbosen
 *
 */
public class MobileMyLendDTO extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 出借金额
     */
    private String cjje;
    /**
     * 产品类型
     */
    private String productType;
    /**
     * 总期数
     */
    private String zqs;
    /**
     * 加息
     */
    private String Jxother;
    /**
     * 年化利率
     */
    private String nhll;
    /**
     * 出借时间
     */
    private String cjsjTime;
    /**
     * 是否是债转标
     */
    private String cond;
    /**
     * 类型
     */
    private String type;
    /**
     * 资金池OID
     */
    private String CashPoolOID;
    /**
     * 标OID
     */
    private String biOID;

    private String productStatus;

    public String getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(String productStatus) {
	this.productStatus = productStatus;
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

    public String getProductType() {
	return productType;
    }

    public void setProductType(String productType) {
	this.productType = productType;
    }

    public String getZqs() {
	return zqs;
    }

    public void setZqs(String zqs) {
	this.zqs = zqs;
    }

    public String getJxother() {
	return Jxother;
    }

    public void setJxother(String jxother) {
	Jxother = jxother;
    }

    public String getNhll() {
	return nhll;
    }

    public void setNhll(String nhll) {
	this.nhll = nhll;
    }

    public String getCjsjTime() {
	return cjsjTime;
    }

    public void setCjsjTime(String cjsjTime) {
	this.cjsjTime = cjsjTime;
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

    public String getCashPoolOID() {
	return CashPoolOID;
    }

    public void setCashPoolOID(String cashPoolOID) {
	CashPoolOID = cashPoolOID;
    }

    public String getBiOID() {
	return biOID;
    }

    public void setBiOID(String biOID) {
	this.biOID = biOID;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileMyLendDTO [xmmc=" + xmmc + ", cjje=" + cjje + ", productType=" + productType + ", zqs=" + zqs
		+ ", Jxother=" + Jxother + ", nhll=" + nhll + ", cjsjTime=" + cjsjTime + ", cond=" + cond + ", type="
		+ type + ", CashPoolOID=" + CashPoolOID + ", biOID=" + biOID + ", productStatus=" + productStatus + "]";
    }

}
