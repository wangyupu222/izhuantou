package com.izhuantou.damain.lend;

import java.math.BigDecimal;

import com.izhuantou.damain.BasePojo;

/**
 * 环环投出借信息
 * 
 * @author yangbosen
 *
 */
public class DetialZZT extends BasePojo {

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
    private String productStatus;
    /**
     * 可投金额
     */
    private BigDecimal ktje;
    /**
     * 投资进度
     */
    private BigDecimal tzjd;
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
     * 转让价格
     */
    private BigDecimal zbsl;
    /**
     * 转让人
     */
    private String zrName;
    /**
     * 原标金额
     */
    private BigDecimal yuanMoney;
    /**
     * 代收本金
     */
    private BigDecimal dsbj;
    /**
     * 原标年化
     */
    private BigDecimal ybnh;
    /**
     * 当前代收利息
     */
    private BigDecimal dqds_interest;
    /**
     * businessOID
     */
    private String biOID;
    /**
     * 产品状态
     */
    private Integer productType;
    /**
     * 可用余额
     */
    private BigDecimal availablemoney;

    public BigDecimal getAvailablemoney() {
	return availablemoney;
    }

    public void setAvailablemoney(BigDecimal availablemoney) {
	this.availablemoney = availablemoney;
    }

    public Integer getProductType() {
	return productType;
    }

    public void setProductType(Integer productType) {
	this.productType = productType;
    }

    public String getBiOID() {
	return biOID;
    }

    public void setBiOID(String biOID) {
	this.biOID = biOID;
    }

    public BigDecimal getDqds_interest() {
	return dqds_interest;
    }

    public void setDqds_interest(BigDecimal dqds_interest) {
	this.dqds_interest = dqds_interest;
    }

    public BigDecimal getYbnh() {
	return ybnh;
    }

    public void setYbnh(BigDecimal ybnh) {
	this.ybnh = ybnh;
    }

    public BigDecimal getDsbj() {
	return dsbj;
    }

    public void setDsbj(BigDecimal dsbj) {
	this.dsbj = dsbj;
    }

    public BigDecimal getYuanMoney() {
	return yuanMoney;
    }

    public void setYuanMoney(BigDecimal yuanMoney) {
	this.yuanMoney = yuanMoney;
    }

    public String getZrName() {
	return zrName;
    }

    public void setZrName(String zrName) {
	this.zrName = zrName;
    }

    public BigDecimal getZbsl() {
	return zbsl;
    }

    public void setZbsl(BigDecimal zbsl) {
	this.zbsl = zbsl;
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

    public String getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(String productStatus) {
	this.productStatus = productStatus;
    }

    public BigDecimal getKtje() {
	return ktje;
    }

    public void setKtje(BigDecimal ktje) {
	this.ktje = ktje;
    }

    public BigDecimal getTzjd() {
	return tzjd;
    }

    public void setTzjd(BigDecimal tzjd) {
	this.tzjd = tzjd;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "DetialZZT [businessOID=" + businessOID + ", OID=" + OID + ", xmmc=" + xmmc + ", nhll=" + nhll
		+ ", cjqx=" + cjqx + ", hkfs=" + hkfs + ", xmze=" + xmze + ", qtje=" + qtje + ", isCWTB=" + isCWTB
		+ ", ytrs=" + ytrs + ", productStatus=" + productStatus + ", ktje=" + ktje + ", tzjd=" + tzjd
		+ ", hxProductType=" + hxProductType + ", hxLoanCenterInfo=" + hxLoanCenterInfo + ", xueli=" + xueli
		+ ", zbsl=" + zbsl + ", zrName=" + zrName + ", yuanMoney=" + yuanMoney + ", dsbj=" + dsbj + ", ybnh="
		+ ybnh + ", dqds_interest=" + dqds_interest + ", biOID=" + biOID + ", productType=" + productType
		+ ", availablemoney=" + availablemoney + "]";
    }

}
