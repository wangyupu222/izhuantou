package com.izhuantou.damain.lend;

import java.math.BigDecimal;

import com.izhuantou.damain.BasePojo;

/**
 * 我的出借头笔赚详情
 * 
 * @author yangbosen
 *
 */
public class DetialTBZ extends BasePojo {

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
     * 附加利率
     */
    private BigDecimal fjll;
    /**
     * 总利率
     */
    private BigDecimal nhlltotal;
    /**
     * 投资进度
     */
    private Integer tzjd;
    /**
     * 转让价格
     */
    private BigDecimal zbsl;
    /**
     * 剩余可投
     */
    private BigDecimal sykt;
    /**
     * 用户剩余金额
     */
    private BigDecimal availablemoney;
    /**
     * 还款方式
     */
    private String hkfs;
    /**
     * 产品状态
     */
    private String productStatus;

    private String debitCreditOID;

    private String biddingType;
    /**
     * 可用余额
     */
    private BigDecimal useMoney;

    private BigDecimal ir;

    private BigDecimal num;
    /**
     * 是否出借过头笔赚
     */
    private Integer isnew;

    public Integer getIsnew() {
	return isnew;
    }

    public void setIsnew(Integer isnew) {
	this.isnew = isnew;
    }

    public BigDecimal getNum() {
	return num;
    }

    public void setNum(BigDecimal num) {
	this.num = num;
    }

    public BigDecimal getIr() {
	return ir;
    }

    public void setIr(BigDecimal ir) {
	this.ir = ir;
    }

    public BigDecimal getUseMoney() {
	return useMoney;
    }

    public void setUseMoney(BigDecimal useMoney) {
	this.useMoney = useMoney;
    }

    public String getDebitCreditOID() {
	return debitCreditOID;
    }

    public void setDebitCreditOID(String debitCreditOID) {
	this.debitCreditOID = debitCreditOID;
    }

    public String getBiddingType() {
	return biddingType;
    }

    public void setBiddingType(String biddingType) {
	this.biddingType = biddingType;
    }

    public String getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(String productStatus) {
	this.productStatus = productStatus;
    }

    public String getHkfs() {
	return hkfs;
    }

    public void setHkfs(String hkfs) {
	this.hkfs = hkfs;
    }

    public String getXmmc() {
	return xmmc;
    }

    public void setXmmc(String xmmc) {
	this.xmmc = xmmc;
    }

    public Integer getTzjd() {
	return tzjd;
    }

    public void setTzjd(Integer tzjd) {
	this.tzjd = tzjd;
    }

    public BigDecimal getZbsl() {
	return zbsl;
    }

    public void setZbsl(BigDecimal zbsl) {
	this.zbsl = zbsl;
    }

    public BigDecimal getSykt() {
	return sykt;
    }

    public void setSykt(BigDecimal sykt) {
	this.sykt = sykt;
    }

    public BigDecimal getAvailablemoney() {
	return availablemoney;
    }

    public void setAvailablemoney(BigDecimal availablemoney) {
	this.availablemoney = availablemoney;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    public BigDecimal getNhll() {
	return nhll;
    }

    public void setNhll(BigDecimal nhll) {
	this.nhll = nhll;
    }

    public BigDecimal getFjll() {
	return fjll;
    }

    public void setFjll(BigDecimal fjll) {
	this.fjll = fjll;
    }

    public BigDecimal getNhlltotal() {
	return nhlltotal;
    }

    public void setNhlltotal(BigDecimal nhlltotal) {
	this.nhlltotal = nhlltotal;
    }

    @Override
    public String toString() {
	return "DetialTBZ [xmmc=" + xmmc + ", nhll=" + nhll + ", fjll=" + fjll + ", nhlltotal=" + nhlltotal + ", tzjd="
		+ tzjd + ", zbsl=" + zbsl + ", sykt=" + sykt + ", availablemoney=" + availablemoney + ", hkfs=" + hkfs
		+ ", productStatus=" + productStatus + ", debitCreditOID=" + debitCreditOID + ", biddingType="
		+ biddingType + ", useMoney=" + useMoney + ", ir=" + ir + ", num=" + num + ", isnew=" + isnew + "]";
    }

}
