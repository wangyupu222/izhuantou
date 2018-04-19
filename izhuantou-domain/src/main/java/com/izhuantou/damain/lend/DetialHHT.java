package com.izhuantou.damain.lend;

import java.math.BigDecimal;

import com.izhuantou.damain.BasePojo;

/**
 * 环环投出借信息
 * 
 * @author yangbosen
 *
 */
public class DetialHHT extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 年化利率
     */
    private BigDecimal nhll;
    /**
     * 加息
     */
    private BigDecimal Jxother;
    /**
     * 总年化利率
     */
    private BigDecimal allnhll;
    /**
     * 出街期限
     */
    private double cjqx;
    /**
     * 分散标数
     */
    private Integer zbsl;
    /**
     * 产品状态
     */
    private String productStatus;
    /**
     * 还款方式
     */
    private String repaymentType;
    /**
     * 判断加息
     */
    private String JX;

    private BigDecimal useMoney;
    private BigDecimal ktje;
    private BigDecimal dbxeAmount;
    private BigDecimal dbxeFlag;

    public BigDecimal getDbxeAmount() {
	return dbxeAmount;
    }

    public void setDbxeAmount(BigDecimal dbxeAmount) {
	this.dbxeAmount = dbxeAmount;
    }

    public BigDecimal getDbxeFlag() {
	return dbxeFlag;
    }

    public void setDbxeFlag(BigDecimal dbxeFlag) {
	this.dbxeFlag = dbxeFlag;
    }

    public BigDecimal getUseMoney() {
	return useMoney;
    }

    public void setUseMoney(BigDecimal useMoney) {
	this.useMoney = useMoney;
    }

    public BigDecimal getKtje() {
	return ktje;
    }

    public void setKtje(BigDecimal ktje) {
	this.ktje = ktje;
    }

    public double getCjqx() {
	return cjqx;
    }

    public void setCjqx(double cjqx) {
	this.cjqx = cjqx;
    }

    public String getJX() {
	return JX;
    }

    public void setJX(String jX) {
	JX = jX;
    }

    public String getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(String productStatus) {
	this.productStatus = productStatus;
    }

    public String getRepaymentType() {
	return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
	this.repaymentType = repaymentType;
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

    public BigDecimal getJxother() {
	return Jxother;
    }

    public void setJxother(BigDecimal jxother) {
	Jxother = jxother;
    }

    public BigDecimal getAllnhll() {
	return allnhll;
    }

    public void setAllnhll(BigDecimal allnhll) {
	this.allnhll = allnhll;
    }

    public Integer getZbsl() {
	return zbsl;
    }

    public void setZbsl(Integer zbsl) {
	this.zbsl = zbsl;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "DetialHHT [xmmc=" + xmmc + ", nhll=" + nhll + ", Jxother=" + Jxother + ", allnhll=" + allnhll
		+ ", cjqx=" + cjqx + ", zbsl=" + zbsl + ", productStatus=" + productStatus + ", repaymentType="
		+ repaymentType + ", JX=" + JX + ", useMoney=" + useMoney + ", ktje=" + ktje + ", dbxeAmount="
		+ dbxeAmount + ", dbxeFlag=" + dbxeFlag + "]";
    }

}
