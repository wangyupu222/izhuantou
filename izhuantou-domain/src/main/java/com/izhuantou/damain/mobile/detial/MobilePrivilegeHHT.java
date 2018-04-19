package com.izhuantou.damain.mobile.detial;

import com.izhuantou.damain.BasePojo;

/**
 * 环环投加息券
 * 
 * @author yangbosen
 *
 */
public class MobilePrivilegeHHT extends BasePojo {
    /**
     * 加息券实体
     */
    private static final long serialVersionUID = 1L;
    /**
     * 加息券最低出借额
     */
    private String lowAmount;
    /**
     * 是否适用本产品
     */
    private String isProduct;
    /**
     * 加息券名称
     */
    private String privilegeName;
    /**
     * 加息券期限
     */
    private String privilegeTerm;
    /**
     * 加息券简介
     */
    private String ruleIntroduce;
    /**
     * 开始时间
     */
    private String ksDate;
    /**
     * 结束时间
     */
    private String jsDate;
    /**
     * 特权ID
     */
    private String privilegeOID;
    /**
     * 用户使用券OID
     */
    private String mappingOID;

    public String getLowAmount() {
	return lowAmount;
    }

    public void setLowAmount(String lowAmount) {
	this.lowAmount = lowAmount;
    }

    public String getIsProduct() {
	return isProduct;
    }

    public void setIsProduct(String isProduct) {
	this.isProduct = isProduct;
    }

    public String getPrivilegeName() {
	return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
	this.privilegeName = privilegeName;
    }

    public String getPrivilegeTerm() {
	return privilegeTerm;
    }

    public void setPrivilegeTerm(String privilegeTerm) {
	this.privilegeTerm = privilegeTerm;
    }

    public String getRuleIntroduce() {
	return ruleIntroduce;
    }

    public void setRuleIntroduce(String ruleIntroduce) {
	this.ruleIntroduce = ruleIntroduce;
    }

    public String getKsDate() {
	return ksDate;
    }

    public void setKsDate(String ksDate) {
	this.ksDate = ksDate;
    }

    public String getJsDate() {
	return jsDate;
    }

    public void setJsDate(String jsDate) {
	this.jsDate = jsDate;
    }

    public String getPrivilegeOID() {
	return privilegeOID;
    }

    public void setPrivilegeOID(String privilegeOID) {
	this.privilegeOID = privilegeOID;
    }

    public String getMappingOID() {
	return mappingOID;
    }

    public void setMappingOID(String mappingOID) {
	this.mappingOID = mappingOID;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobilePrivilegeHHT [lowAmount=" + lowAmount + ", isProduct=" + isProduct + ", privilegeName="
		+ privilegeName + ", privilegeTerm=" + privilegeTerm + ", ruleIntroduce=" + ruleIntroduce + ", ksDate="
		+ ksDate + ", jsDate=" + jsDate + ", privilegeOID=" + privilegeOID + ", mappingOID=" + mappingOID + "]";
    }

}
