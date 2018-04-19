package com.izhuantou.damain.mobile;

import java.math.BigDecimal;

import com.izhuantou.damain.BasePojo;

/**
 * 活动标实体
 * 
 * @author yangbosen
 *
 */
public class ActivityModel extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 产品OID
     */
    private String businessOID;
    /**
     * 产品状态
     */
    private String productStatus;
    /**
     * 主键OID
     */
    private String OID;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 年化利率
     */
    private BigDecimal nhll;
    /**
     * 出借期限
     */
    private Integer cjqx;
    /**
     * 还款方式
     */
    private String hkfs;
    /**
     * 项目总额
     */
    private String xmze;
    /**
     * 已投人数
     */
    private Integer ytrs;
    /**
     * 起投金额
     */
    private String qtje;
    /**
     * 可投金额
     */
    private String ktje;

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
    }

    public String getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(String productStatus) {
	this.productStatus = productStatus;
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

    public BigDecimal getNhll() {
	return nhll;
    }

    public void setNhll(BigDecimal nhll) {
	this.nhll = nhll;
    }

    public String getHkfs() {
	return hkfs;
    }

    public void setHkfs(String hkfs) {
	this.hkfs = hkfs;
    }

    public String getXmze() {
	return xmze;
    }

    public void setXmze(String xmze) {
	this.xmze = xmze;
    }

    public Integer getYtrs() {
	return ytrs;
    }

    public void setYtrs(Integer ytrs) {
	this.ytrs = ytrs;
    }

    public String getQtje() {
	return qtje;
    }

    public void setQtje(String qtje) {
	this.qtje = qtje;
    }

    public String getKtje() {
	return ktje;
    }

    public void setKtje(String ktje) {
	this.ktje = ktje;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    public Integer getCjqx() {
	return cjqx;
    }

    public void setCjqx(Integer cjqx) {
	this.cjqx = cjqx;
    }

    @Override
    public String toString() {
	return "ActivityModel [businessOID=" + businessOID + ", productStatus=" + productStatus + ", OID=" + OID
		+ ", xmmc=" + xmmc + ", nhll=" + nhll + ", cjqx=" + cjqx + ", hkfs=" + hkfs + ", xmze=" + xmze
		+ ", ytrs=" + ytrs + ", qtje=" + qtje + ", ktje=" + ktje + "]";
    }

}
