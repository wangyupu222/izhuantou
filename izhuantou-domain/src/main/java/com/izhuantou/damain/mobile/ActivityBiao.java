package com.izhuantou.damain.mobile;

import com.izhuantou.damain.BasePojo;

/**
 * 活动标
 * 
 * @author yangbosen
 *
 */
public class ActivityBiao extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 利率信息OID
     */
    private String productRateInfoID;
    /**
     * 产品名
     */
    private String packageName;
    /**
     * 剩余可投
     */
    private String sykt;
    /**
     * 可投金额
     */
    private String applyAmount;
    /**
     * 起投金额
     */
    private String startBidAmount;
    /**
     * 产品状态
     */
    private String productStatus;
    /**
     * 主键OID
     */
    private String OID;

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

    public String getSykt() {
	return sykt;
    }

    public void setSykt(String sykt) {
	this.sykt = sykt;
    }

    public String getApplyAmount() {
	return applyAmount;
    }

    public void setApplyAmount(String applyAmount) {
	this.applyAmount = applyAmount;
    }

    public String getStartBidAmount() {
	return startBidAmount;
    }

    public void setStartBidAmount(String startBidAmount) {
	this.startBidAmount = startBidAmount;
    }

    public String getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(String productStatus) {
	this.productStatus = productStatus;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "ActivityBiao [productRateInfoID=" + productRateInfoID + ", packageName=" + packageName + ", sykt="
		+ sykt + ", applyAmount=" + applyAmount + ", startBidAmount=" + startBidAmount + ", productStatus="
		+ productStatus + ", OID=" + OID + "]";
    }

}
