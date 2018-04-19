package com.izhuantou.damain.mobile.personalCenter.myLendDetial;

public class MobileZZTYwcDTO {

    /**
     * 我的出借转转投已完成dto
     */
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    private String OID;
    /**
     * 
     */
    private String businessOID;
    /**
     * 产品名
     */
    private String biddingName;
    /**
     * 钱数
     */
    private String money;
    /**
     * 年化利率
     */
    private String yearRate;
    /**
     * 增加时间
     */
    private String addDateTime;
    /**
     * 产品期数
     */
    private String productTerm;

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
    }

    public String getBiddingName() {
	return biddingName;
    }

    public void setBiddingName(String biddingName) {
	this.biddingName = biddingName;
    }

    public String getMoney() {
	return money;
    }

    public void setMoney(String money) {
	this.money = money;
    }

    public String getYearRate() {
	return yearRate;
    }

    public void setYearRate(String yearRate) {
	this.yearRate = yearRate;
    }

    public String getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(String addDateTime) {
	this.addDateTime = addDateTime;
    }

    public String getProductTerm() {
	return productTerm;
    }

    public void setProductTerm(String productTerm) {
	this.productTerm = productTerm;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileZZTYwcDTO [OID=" + OID + ", businessOID=" + businessOID + ", biddingName=" + biddingName
		+ ", money=" + money + ", yearRate=" + yearRate + ", addDateTime=" + addDateTime + ", productTerm="
		+ productTerm + "]";
    }

}
