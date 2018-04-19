package com.izhuantou.damain.lend;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.izhuantou.damain.BasePojo;

/**
 * 环环投实体
 * 
 * @author yangbosen
 *
 */
public class HHTou extends BasePojo {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /**
     * 根据ID查找利率
     */
    private String productRateInfoID;
    /**
     * 名称
     */
    private String packageName;
    /**
     * 期限
     */
    private Integer productTerm;
    /**
     * 利率
     */
    private Double yearRate;
    /**
     * 交款方式
     */
    private String repaymentType;
    /**
     * 投资名称
     */
    private String name;
    /**
     * 利率
     */
    private String rate;
    /**
     * 标识
     */
    private Integer szds;
    /**
     * 倒计时时间(毫秒表示)
     */
    private Date dsTime;
    /**
     * 倒计时时间
     */
    private String sj;
    /**
     * 标识
     */
    private long xtcz;
    /**
     * OID查询值
     */
    @JsonProperty("OID")
    private String OID;
    /**
     * 项目名称
     */
    private String newPackageName;
    /**
     * 判断 加息标
     */
    private BigDecimal JXOther;
    /**
     * 产品状态
     */
    private Integer productStatus;

    /**
     * 剩余资金数量
     */
    private Integer holdingAmount;
    /**
     * 剩余可投金额
     */
    private Double sykt;

    public Double getSykt() {
	return sykt;
    }

    public void setSykt(Double sykt) {
	this.sykt = sykt;
    }

    public Integer getHoldingAmount() {
	return holdingAmount;
    }

    public void setHoldingAmount(Integer holdingAmount) {
	this.holdingAmount = holdingAmount;
    }

    public Integer getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
	this.productStatus = productStatus;
    }

    public BigDecimal getJXOther() {
	return JXOther;
    }

    public void setJXOther(BigDecimal jXOther) {
	JXOther = jXOther;
    }

    public void setYearRate(Double yearRate) {
	this.yearRate = yearRate;
    }

    public String getNewPackageName() {
	return newPackageName;
    }

    public void setNewPackageName(String newPackageName) {
	this.newPackageName = newPackageName;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public Integer getSzds() {
	return szds;
    }

    public void setSzds(Integer szds) {
	this.szds = szds;
    }

    public Date getDsTime() {
	return dsTime;
    }

    public void setDsTime(Date dsTime) {
	this.dsTime = dsTime;
    }

    public String getSj() {
	return sj;
    }

    public void setSj(String sj) {
	this.sj = sj;
    }

    public long getXtcz() {
	return xtcz;
    }

    public void setXtcz(long xtcz) {
	this.xtcz = xtcz;
    }

    public String getRate() {
	return rate;
    }

    public void setRate(String rate) {
	this.rate = rate;
    }

    public Integer getProductTerm() {
	return productTerm;
    }

    public void setProductTerm(Integer productTerm) {
	this.productTerm = productTerm;
    }

    public double getYearRate() {
	return yearRate;
    }

    public void setYearRate(double yearRate) {
	this.yearRate = yearRate;
    }

    public String getRepaymentType() {
	return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
	this.repaymentType = repaymentType;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getProductRateInfoID() {
	return productRateInfoID;
    }

    public void setProductRateInfoID(String productRateInfoID) {
	this.productRateInfoID = productRateInfoID;
    }

    public String getPackageName() {
	return packageName;
    }

    public void setPackageName(String packageName) {
	this.packageName = packageName;
    }

    @Override
    public String toString() {
	return "HHTou [productRateInfoID=" + productRateInfoID + ", packageName=" + packageName + ", productTerm="
		+ productTerm + ", yearRate=" + yearRate + ", repaymentType=" + repaymentType + ", name=" + name
		+ ", rate=" + rate + ", szds=" + szds + ", dsTime=" + dsTime + ", sj=" + sj + ", xtcz=" + xtcz
		+ ", OID=" + OID + ", newPackageName=" + newPackageName + ", JXOther=" + JXOther + ", productStatus="
		+ productStatus + ", holdingAmount=" + holdingAmount + ", sykt=" + sykt + "]";
    }

}
