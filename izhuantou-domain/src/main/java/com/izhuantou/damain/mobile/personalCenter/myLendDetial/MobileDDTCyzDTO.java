package com.izhuantou.damain.mobile.personalCenter.myLendDetial;

import java.math.BigDecimal;
import java.util.Date;

import com.izhuantou.damain.BasePojo;

public class MobileDDTCyzDTO extends BasePojo {

    /**
     * 点点投持有中列表DTO
     */
    private static final long serialVersionUID = 1L;
    /**
     * 债转OID
     */
    private String DOID;
    /**
     * 出借时间
     */
    private Date cjsjTime;
    /**
     * 产品类型
     */
    private String productType;
    /**
     * 标OID
     */
    private String biOID;
    /**
     * 还款状态
     */
    private String hkzt;
    /**
     * 出借金额
     */
    private BigDecimal cjje;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 年华利率
     */
    private BigDecimal nhll;
    /**
     * 出借人OID
     */
    private String outMemberOID;
    /**
     * 收益期数
     */
    private Integer syqs;
    /**
     * 总期数
     */
    private Integer zqs;

    public String getDOID() {
	return DOID;
    }

    public void setDOID(String dOID) {
	DOID = dOID;
    }

    public Date getCjsjTime() {
	return cjsjTime;
    }

    public void setCjsjTime(Date cjsjTime) {
	this.cjsjTime = cjsjTime;
    }

    public String getProductType() {
	return productType;
    }

    public void setProductType(String productType) {
	this.productType = productType;
    }

    public String getBiOID() {
	return biOID;
    }

    public void setBiOID(String biOID) {
	this.biOID = biOID;
    }

    public String getHkzt() {
	return hkzt;
    }

    public void setHkzt(String hkzt) {
	this.hkzt = hkzt;
    }

    public BigDecimal getCjje() {
	return cjje;
    }

    public void setCjje(BigDecimal cjje) {
	this.cjje = cjje;
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

    public String getOutMemberOID() {
	return outMemberOID;
    }

    public void setOutMemberOID(String outMemberOID) {
	this.outMemberOID = outMemberOID;
    }

    public Integer getSyqs() {
	return syqs;
    }

    public void setSyqs(Integer syqs) {
	this.syqs = syqs;
    }

    public Integer getZqs() {
	return zqs;
    }

    public void setZqs(Integer zqs) {
	this.zqs = zqs;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileDDTCyzDTO [DOID=" + DOID + ", cjsjTime=" + cjsjTime + ", productType=" + productType + ", biOID="
		+ biOID + ", hkzt=" + hkzt + ", cjje=" + cjje + ", xmmc=" + xmmc + ", nhll=" + nhll + ", outMemberOID="
		+ outMemberOID + ", syqs=" + syqs + ", zqs=" + zqs + "]";
    }

}
