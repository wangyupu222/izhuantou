package com.izhuantou.damain.lend;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.izhuantou.damain.BasePojo;

/**
 * 头笔赚实体
 * 
 * @author yangbosen
 *
 */
public class XinShouZhuan extends BasePojo {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /**
     * 标题名称
     */
    private String biddingName;
    /**
     * 最低借出额
     */
    private Double startBidAmount;
    /**
     * 剩余总金额
     */
    private BigDecimal biddingAmount;
    /**
     * 年化利率
     */
    private Double yearRate;
    /**
     * 年化利率
     */
    private String rate;
    /**
     * 借出天数
     */
    private Integer days;

    /**
     * 进度
     */
    private double process;
    /**
     * 持有资金
     */
    private BigDecimal holdingAmount;
    /**
     * 产品状态
     */
    private Integer productStatus;
    /**
     * 标识
     */
    private Integer szds;
    /**
     * 标的倒计时(毫秒)
     */
    private Date dsTime;
    /**
     * 标的倒计时(时分秒)
     */
    private String sj;
    /**
     * 标识
     */
    private long xtcz;
    /**
     * 项目标识OID
     */
    @JsonProperty("OID")
    private String OID;
    /**
     * 项目名称
     */
    private String newbiddingName;
    /**
     * 回款方式
     */
    private String repaymentType;
    /**
     * 剩余可投金额
     */
    private BigDecimal sy;

    private String debitCreditOID;

    public String getDebitCreditOID() {
	return debitCreditOID;
    }

    public void setDebitCreditOID(String debitCreditOID) {
	this.debitCreditOID = debitCreditOID;
    }

    public String getBiddingName() {
	return biddingName;
    }

    public void setBiddingName(String biddingName) {
	this.biddingName = biddingName;
    }

    public Double getStartBidAmount() {
	return startBidAmount;
    }

    public void setStartBidAmount(Double startBidAmount) {
	this.startBidAmount = startBidAmount;
    }

    public BigDecimal getBiddingAmount() {
	return biddingAmount;
    }

    public void setBiddingAmount(BigDecimal biddingAmount) {
	this.biddingAmount = biddingAmount;
    }

    public Double getYearRate() {
	return yearRate;
    }

    public void setYearRate(Double yearRate) {
	this.yearRate = yearRate;
    }

    public String getRate() {
	return rate;
    }

    public void setRate(String rate) {
	this.rate = rate;
    }

    public Integer getDays() {
	return days;
    }

    public void setDays(Integer days) {
	this.days = days;
    }

    public double getProcess() {
	return process;
    }

    public void setProcess(double process) {
	this.process = process;
    }

    public BigDecimal getHoldingAmount() {
	return holdingAmount;
    }

    public void setHoldingAmount(BigDecimal holdingAmount) {
	this.holdingAmount = holdingAmount;
    }

    public Integer getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
	this.productStatus = productStatus;
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

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getNewbiddingName() {
	return newbiddingName;
    }

    public void setNewbiddingName(String newbiddingName) {
	this.newbiddingName = newbiddingName;
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
	return "XinShouZhuan [biddingName=" + biddingName + ", startBidAmount=" + startBidAmount + ", biddingAmount="
		+ biddingAmount + ", yearRate=" + yearRate + ", rate=" + rate + ", days=" + days + ", process="
		+ process + ", holdingAmount=" + holdingAmount + ", productStatus=" + productStatus + ", szds=" + szds
		+ ", dsTime=" + dsTime + ", sj=" + sj + ", xtcz=" + xtcz + ", OID=" + OID + ", newbiddingName="
		+ newbiddingName + ", repaymentType=" + repaymentType + ", sy=" + sy + "]";
    }

}
