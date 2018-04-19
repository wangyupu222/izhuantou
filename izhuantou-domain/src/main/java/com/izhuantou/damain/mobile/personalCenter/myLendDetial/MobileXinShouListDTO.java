package com.izhuantou.damain.mobile.personalCenter.myLendDetial;

import java.math.BigDecimal;
import java.util.Date;

import com.izhuantou.damain.BasePojo;

public class MobileXinShouListDTO extends BasePojo {

    /**
     * 出借中列表实体
     */
    private static final long serialVersionUID = 1L;
    /**
     * OID
     */
    private String OID;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 出借金额
     */
    private BigDecimal cjje;
    /**
     * 年化利率
     */
    private BigDecimal nhll;
    /**
     * 出借时间
     */
    private Date cjsjDate;
    /**
     * 
     */
    private String debitCreditOID;

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

    public BigDecimal getCjje() {
	return cjje;
    }

    public void setCjje(BigDecimal cjje) {
	this.cjje = cjje;
    }

    public BigDecimal getNhll() {
	return nhll;
    }

    public void setNhll(BigDecimal nhll) {
	this.nhll = nhll;
    }

    public Date getCjsjDate() {
	return cjsjDate;
    }

    public void setCjsjDate(Date cjsjDate) {
	this.cjsjDate = cjsjDate;
    }

    public String getDebitCreditOID() {
	return debitCreditOID;
    }

    public void setDebitCreditOID(String debitCreditOID) {
	this.debitCreditOID = debitCreditOID;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileXinShouListDTO [OID=" + OID + ", xmmc=" + xmmc + ", cjje=" + cjje + ", nhll=" + nhll
		+ ", cjsjDate=" + cjsjDate + ", debitCreditOID=" + debitCreditOID + "]";
    }

}
