package com.izhuantou.damain.lend;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.izhuantou.damain.BasePojo;

/**
 * 点点投实体对象
 * 
 * @author yangbosen
 *
 */
public class DDTou extends BasePojo {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    /**
     * 根据OID查询具体信息
     */
    @JsonProperty("OID")
    private String OID;
    /**
     * 项目名称
     */
    private String biddingName;
    /**
     * 最少投资资金
     */
    private Integer startBidAmount;
    /**
     * 资金
     */
    private BigDecimal biddingAmount;
    /**
     * 持有资金
     */
    private BigDecimal holdingAmount;
    /**
     * 利率
     */
    private Double yearRate;
    /**
     * 分期
     */
    private Integer productTerm;
    /**
     * 进程
     */
    private Double process;
    /**
     * 利率
     */
    private String rate;
    /**
     * 判断标识
     */
    private Integer szds;
    /**
     * 倒计时时间(毫秒)
     */
    private Date dsTime;
    /**
     * 倒计时时间时分秒
     */
    private String sj;
    /**
     * 标识
     */
    private long xtcz;
    /**
     * 原来的项目名称
     */
    private String newBiddingAmount;
    /**
     * 项目状态
     */
    private Integer productStatus;
    /**
     * 回款类型
     */
    private String repaymentType;
    /**
     * 剩余可投金额
     */
    private BigDecimal sy;
    private String productRateInfoID;

    public String getProductRateInfoID() {
	return productRateInfoID;
    }

    public void setProductRateInfoID(String productRateInfoID) {
	this.productRateInfoID = productRateInfoID;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getBiddingName() {
	return biddingName;
    }

    public void setBiddingName(String biddingName) {
	this.biddingName = biddingName;
    }

    public Integer getStartBidAmount() {
	return startBidAmount;
    }

    public void setStartBidAmount(Integer startBidAmount) {
	this.startBidAmount = startBidAmount;
    }

    public BigDecimal getBiddingAmount() {
	return biddingAmount;
    }

    public void setBiddingAmount(BigDecimal biddingAmount) {
	this.biddingAmount = biddingAmount;
    }

    public BigDecimal getHoldingAmount() {
	return holdingAmount;
    }

    public void setHoldingAmount(BigDecimal holdingAmount) {
	this.holdingAmount = holdingAmount;
    }

    public Double getYearRate() {
	return yearRate;
    }

    public void setYearRate(Double yearRate) {
	this.yearRate = yearRate;
    }

    public Integer getProductTerm() {
	return productTerm;
    }

    public void setProductTerm(Integer productTerm) {
	this.productTerm = productTerm;
    }

    public Double getProcess() {
	return process;
    }

    public void setProcess(Double process) {
	this.process = process;
    }

    public String getRate() {
	return rate;
    }

    public void setRate(String rate) {
	this.rate = rate;
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

    public String getNewBiddingAmount() {
	return newBiddingAmount;
    }

    public void setNewBiddingAmount(String newBiddingAmount) {
	this.newBiddingAmount = newBiddingAmount;
    }

    public Integer getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
	this.productStatus = productStatus;
    }

    public String getRepaymentType() {
	return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
	this.repaymentType = repaymentType;
    }

    public BigDecimal getSy() {
	return sy;
    }

    public void setSy(BigDecimal sy) {
	this.sy = sy;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "DDTou [OID=" + OID + ", biddingName=" + biddingName + ", startBidAmount=" + startBidAmount
		+ ", biddingAmount=" + biddingAmount + ", holdingAmount=" + holdingAmount + ", yearRate=" + yearRate
		+ ", productTerm=" + productTerm + ", process=" + process + ", rate=" + rate + ", szds=" + szds
		+ ", dsTime=" + dsTime + ", sj=" + sj + ", xtcz=" + xtcz + ", newBiddingAmount=" + newBiddingAmount
		+ ", productStatus=" + productStatus + ", repaymentType=" + repaymentType + ", sy=" + sy
		+ ", productRateInfoID=" + productRateInfoID + "]";
    }

}
