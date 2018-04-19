package com.izhuantou.damain.lend;

import java.math.BigDecimal;

import com.izhuantou.damain.BasePojo;

/**
 * 点点投出借信息
 * 
 * @author yangbosen
 *
 */
public class DetialDDT extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String businessOID;

    public String OID;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 年化利率
     */
    private BigDecimal nhll;
    /**
     * 出街期限
     */
    private Integer cjqx;
    /**
     * 还款方式
     */
    private String hkfs;
    /**
     * 项目总额
     */
    private BigDecimal xmze;
    /**
     * 起投金额
     */
    private BigDecimal qtje;
    /**
     * 
     */
    private String isCWTB;
    /**
     * 已投人数
     */
    private Integer ytrs;
    /**
     * 产品状态
     */
    private Integer productStatus;
    /**
     * 可投金额
     */
    private BigDecimal ktje;
    /**
     * 投资进度
     */
    private Integer tzjd;
    /**
     * 
     */
    private String hxProductType;
    /**
     * 
     */
    private String hxLoanCenterInfo;
    /**
     * 学历
     */
    private String xueli;
    /**
     * 可用余额
     */
    private BigDecimal useMoney;

    public BigDecimal getUseMoney() {
	return useMoney;
    }

    public void setUseMoney(BigDecimal useMoney) {
	this.useMoney = useMoney;
    }

    public String getHxProductType() {
	return hxProductType;
    }

    public void setHxProductType(String hxProductType) {
	this.hxProductType = hxProductType;
    }

    public String getHxLoanCenterInfo() {
	return hxLoanCenterInfo;
    }

    public void setHxLoanCenterInfo(String hxLoanCenterInfo) {
	this.hxLoanCenterInfo = hxLoanCenterInfo;
    }

    public String getXueli() {
	return xueli;
    }

    public void setXueli(String xueli) {
	this.xueli = xueli;
    }

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
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

    public Integer getCjqx() {
	return cjqx;
    }

    public void setCjqx(Integer cjqx) {
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

    public BigDecimal getQtje() {
	return qtje;
    }

    public void setQtje(BigDecimal qtje) {
	this.qtje = qtje;
    }

    public String getIsCWTB() {
	return isCWTB;
    }

    public void setIsCWTB(String isCWTB) {
	this.isCWTB = isCWTB;
    }

    public Integer getYtrs() {
	return ytrs;
    }

    public void setYtrs(Integer ytrs) {
	this.ytrs = ytrs;
    }

    public Integer getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
	this.productStatus = productStatus;
    }

    public BigDecimal getKtje() {
	return ktje;
    }

    public void setKtje(BigDecimal ktje) {
	this.ktje = ktje;
    }

    public Integer getTzjd() {
	return tzjd;
    }

    public void setTzjd(Integer tzjd) {
	this.tzjd = tzjd;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "DetialDDT [businessOID=" + businessOID + ", OID=" + OID + ", xmmc=" + xmmc + ", nhll=" + nhll
		+ ", cjqx=" + cjqx + ", hkfs=" + hkfs + ", xmze=" + xmze + ", qtje=" + qtje + ", isCWTB=" + isCWTB
		+ ", ytrs=" + ytrs + ", productStatus=" + productStatus + ", ktje=" + ktje + ", tzjd=" + tzjd
		+ ", hxProductType=" + hxProductType + ", hxLoanCenterInfo=" + hxLoanCenterInfo + ", xueli=" + xueli
		+ ", useMoney=" + useMoney + "]";
    }

}
