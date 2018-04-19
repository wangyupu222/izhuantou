package com.izhuantou.damain.mobile.personalCenter.myLendDetial;

import java.math.BigDecimal;
import java.util.Date;

import com.izhuantou.damain.BasePojo;

public class MobileXinShouCyzDTO extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 债转实体
    /**
     * 添加时间
     */
    private Date addDateTime;
    /**
     * 金钱
     */
    private BigDecimal money;
    /**
     * businessOID
     */
    private String businessOID;
    /**
     * decribOid
     */
    private String DOID;
    /**
     * 标题
     */
    private String loanNumber;
    /**
     * OID
     */
    private String OID;
    /**
     * 用户OID
     */
    private String memberOID;
    /**
     * 产品名称
     */
    private String biddingName;
    /**
     * 年华利率
     */
    private BigDecimal yearRate;
    /**
     * 产品状态
     */
    private String productStatus;
    /**
     * 产品类型
     */
    private String productType;
    /**
     * 类型
     */
    private String type;
    /**
     * 债转
     */
    private String cond;

    public Date getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(Date addDateTime) {
	this.addDateTime = addDateTime;
    }

    public BigDecimal getMoney() {
	return money;
    }

    public void setMoney(BigDecimal money) {
	this.money = money;
    }

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
    }

    public String getDOID() {
	return DOID;
    }

    public void setDOID(String dOID) {
	DOID = dOID;
    }

    public String getLoanNumber() {
	return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
	this.loanNumber = loanNumber;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public String getBiddingName() {
	return biddingName;
    }

    public void setBiddingName(String biddingName) {
	this.biddingName = biddingName;
    }

    public BigDecimal getYearRate() {
	return yearRate;
    }

    public void setYearRate(BigDecimal yearRate) {
	this.yearRate = yearRate;
    }

    public String getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(String productStatus) {
	this.productStatus = productStatus;
    }

    public String getProductType() {
	return productType;
    }

    public void setProductType(String productType) {
	this.productType = productType;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getCond() {
	return cond;
    }

    public void setCond(String cond) {
	this.cond = cond;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileXinShouCyzDTO [addDateTime=" + addDateTime + ", money=" + money + ", businessOID=" + businessOID
		+ ", DOID=" + DOID + ", loanNumber=" + loanNumber + ", OID=" + OID + ", memberOID=" + memberOID
		+ ", biddingName=" + biddingName + ", yearRate=" + yearRate + ", productStatus=" + productStatus
		+ ", productType=" + productType + ", type=" + type + ", cond=" + cond + "]";
    }

}
