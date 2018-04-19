package com.izhuantou.damain.lend;

import java.math.BigDecimal;

import com.izhuantou.damain.BasePojo;

/**
 * 环环投债转实体
 * 
 * @author yangbosen
 *
 */
public class HHTZZInfo extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 转让人
     */
    private String zzr;
    /**
     * 转让金额
     */
    private BigDecimal zrje;
    /**
     * 代收本息
     */
    private BigDecimal dsbj;
    /**
     * 原标年化
     */
    private BigDecimal ybnh;
    /**
     * 当前代收利息
     */
    private BigDecimal dqdslx;
    /**
     * 债转编号
     */
    private String zzbh;

    private String hkzt;

    private BigDecimal nhll;

    private String hkfs;
    /**
     * 判断是否为债转 空的没有债转为元标
     */
    private String debitCreditOID;

    /**
     * 已还期数
     */
    private String yhnum;
    /**
     * 所有期数
     */
    private String allNum;
    /**
     * 原始标借款总额
     */
    private BigDecimal jkze;

    public BigDecimal getJkze() {
	return jkze;
    }

    public void setJkze(BigDecimal jkze) {
	this.jkze = jkze;
    }

    public String getYhnum() {
	return yhnum;
    }

    public void setYhnum(String yhnum) {
	this.yhnum = yhnum;
    }

    public String getAllNum() {
	return allNum;
    }

    public void setAllNum(String allNum) {
	this.allNum = allNum;
    }

    public String getDebitCreditOID() {
	return debitCreditOID;
    }

    public void setDebitCreditOID(String debitCreditOID) {
	this.debitCreditOID = debitCreditOID;
    }

    public String getHkfs() {
	return hkfs;
    }

    public void setHkfs(String hkfs) {
	this.hkfs = hkfs;
    }

    public BigDecimal getNhll() {
	return nhll;
    }

    public void setNhll(BigDecimal nhll) {
	this.nhll = nhll;
    }

    public String getHkzt() {
	return hkzt;
    }

    public void setHkzt(String hkzt) {
	this.hkzt = hkzt;
    }

    public String getZzbh() {
	return zzbh;
    }

    public void setZzbh(String zzbh) {
	this.zzbh = zzbh;
    }

    public String getZzr() {
	return zzr;
    }

    public void setZzr(String zzr) {
	this.zzr = zzr;
    }

    public BigDecimal getZrje() {
	return zrje;
    }

    public void setZrje(BigDecimal zrje) {
	this.zrje = zrje;
    }

    public BigDecimal getDsbj() {
	return dsbj;
    }

    public void setDsbj(BigDecimal dsbj) {
	this.dsbj = dsbj;
    }

    public BigDecimal getYbnh() {
	return ybnh;
    }

    public void setYbnh(BigDecimal ybnh) {
	this.ybnh = ybnh;
    }

    public BigDecimal getDqdslx() {
	return dqdslx;
    }

    public void setDqdslx(BigDecimal dqdslx) {
	this.dqdslx = dqdslx;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "HHTZZInfo [zzr=" + zzr + ", zrje=" + zrje + ", dsbj=" + dsbj + ", ybnh=" + ybnh + ", dqdslx=" + dqdslx
		+ ", zzbh=" + zzbh + ", hkzt=" + hkzt + ", nhll=" + nhll + ", hkfs=" + hkfs + ", debitCreditOID="
		+ debitCreditOID + ", yhnum=" + yhnum + ", allNum=" + allNum + ", jkze=" + jkze + "]";
    }

}
