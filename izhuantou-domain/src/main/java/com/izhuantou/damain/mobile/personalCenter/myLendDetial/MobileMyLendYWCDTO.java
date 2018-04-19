package com.izhuantou.damain.mobile.personalCenter.myLendDetial;

import com.izhuantou.damain.BasePojo;

/**
 * 我的出借已完成DTO
 * 
 * @author Administrator
 *
 */
public class MobileMyLendYWCDTO extends BasePojo {

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
     * 年化利率
     */
    private String nhll;
    /**
     * 总期数
     */
    private String zqs;
    /**
     * 出借时间
     */
    private String cjsjTime;
    /**
     * 债转
     */
    private String cond;
    /**
     * decrebiteOID
     */
    private String DOID;
    /**
     * 类型
     */
    private String type;
    /**
     * 加息
     */
    private String JXother;
    /**
     * 资金池OID
     */
    private String cashPoolOID;
    /**
     * 标的OID
     */
    private String biOID;
    /**
     * businesssOID
     */
    private String businessOID;

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
    }

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

    public String getJXother() {
	return JXother;
    }

    public void setJXother(String jXother) {
	JXother = jXother;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
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

    public static long getSerialversionuid() {
	return serialVersionUID;
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

    public String getNhll() {
	return nhll;
    }

    public void setNhll(String nhll) {
	this.nhll = nhll;
    }

    public String getZqs() {
	return zqs;
    }

    public void setZqs(String zqs) {
	this.zqs = zqs;
    }

    public String getCjsjTime() {
	return cjsjTime;
    }

    public void setCjsjTime(String cjsjTime) {
	this.cjsjTime = cjsjTime;
    }

    @Override
    public String toString() {
	return "MobileMyLendYWCDTO [xmmc=" + xmmc + ", cjje=" + cjje + ", nhll=" + nhll + ", zqs=" + zqs + ", cjsjTime="
		+ cjsjTime + ", cond=" + cond + ", DOID=" + DOID + ", type=" + type + ", JXother=" + JXother
		+ ", cashPoolOID=" + cashPoolOID + ", biOID=" + biOID + ", businessOID=" + businessOID + "]";
    }

}
