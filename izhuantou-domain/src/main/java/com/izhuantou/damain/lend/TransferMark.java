package com.izhuantou.damain.lend;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.izhuantou.damain.BasePojo;

/**
 * 转让标信息
 * 
 * @author yangbosen
 *
 */
public class TransferMark extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty("OID")
    private String OID;
    /**
     * 转让人
     */
    private String zrr;
    /**
     * 原标借款利率
     */
    private BigDecimal ybjkll;
    /**
     * 原标金额
     */
    private BigDecimal ybze;
    /**
     * 当前代收利息
     */
    private BigDecimal dqdslx;
    /**
     * 代收本金
     */
    private String dsbj;
    private String debitCreditOID;

    public String getDebitCreditOID() {
	return debitCreditOID;
    }

    public void setDebitCreditOID(String debitCreditOID) {
	this.debitCreditOID = debitCreditOID;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getZrr() {
	return zrr;
    }

    public void setZrr(String zrr) {
	this.zrr = zrr;
    }

    public BigDecimal getYbjkll() {
	return ybjkll;
    }

    public void setYbjkll(BigDecimal ybjkll) {
	this.ybjkll = ybjkll;
    }

    public BigDecimal getYbze() {
	return ybze;
    }

    public void setYbze(BigDecimal ybze) {
	this.ybze = ybze;
    }

    public BigDecimal getDqdslx() {
	return dqdslx;
    }

    public void setDqdslx(BigDecimal dqdslx) {
	this.dqdslx = dqdslx;
    }

    public String getDsbj() {
	return dsbj;
    }

    public void setDsbj(String dsbj) {
	this.dsbj = dsbj;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "TransferMark [OID=" + OID + ", zrr=" + zrr + ", ybjkll=" + ybjkll + ", ybze=" + ybze + ", dqdslx="
		+ dqdslx + ", dsbj=" + dsbj + ", debitCreditOID=" + debitCreditOID + "]";
    }

}
