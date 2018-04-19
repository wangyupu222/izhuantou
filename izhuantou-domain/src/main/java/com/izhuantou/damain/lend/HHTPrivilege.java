package com.izhuantou.damain.lend;

import com.izhuantou.damain.BasePojo;

/**
 * 环环投加息券
 * 
 * @author yangbosen
 *
 */
public class HHTPrivilege extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String OID;
    /**
     * 加息券名称
     */
    private String privilegeName;
    /**
     * 加息券持续时间
     */
    private String privilegeTerm;
    /**
     * 
     */
    private String privilegeRange;
    /**
     * 加息券最低可用资金
     */
    private String lowAmount;
    /**
     * 加息券类型
     */
    private String privilegeType;
    /**
     * 
     */
    private String rule;
    /**
     * 加息券规则介绍
     */
    private String ruleIntroduce;
    /**
     * 
     */
    private String MappingOID;
    /**
     * 加息券OID
     */
    private String privilegeOID;
    /**
     * 加息券结束时间
     */
    private String jsDate;
    /**
     * 加息券开始时间
     */
    private String ksDate;

    public String getJsDate() {
	return jsDate;
    }

    public void setJsDate(String jsDate) {
	this.jsDate = jsDate;
    }

    public String getKsDate() {
	return ksDate;
    }

    public void setKsDate(String ksDate) {
	this.ksDate = ksDate;
    }

    public String getMappingOID() {
	return MappingOID;
    }

    public void setMappingOID(String mappingOID) {
	MappingOID = mappingOID;
    }

    public String getPrivilegeOID() {
	return privilegeOID;
    }

    public void setPrivilegeOID(String privilegeOID) {
	this.privilegeOID = privilegeOID;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
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

    public String getPrivilegeRange() {
	return privilegeRange;
    }

    public void setPrivilegeRange(String privilegeRange) {
	this.privilegeRange = privilegeRange;
    }

    public String getLowAmount() {
	return lowAmount;
    }

    public void setLowAmount(String lowAmount) {
	this.lowAmount = lowAmount;
    }

    public String getPrivilegeType() {
	return privilegeType;
    }

    public void setPrivilegeType(String privilegeType) {
	this.privilegeType = privilegeType;
    }

    public String getRule() {
	return rule;
    }

    public void setRule(String rule) {
	this.rule = rule;
    }

    public String getRuleIntroduce() {
	return ruleIntroduce;
    }

    public void setRuleIntroduce(String ruleIntroduce) {
	this.ruleIntroduce = ruleIntroduce;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "HHTPrivilege [OID=" + OID + ", privilegeName=" + privilegeName + ", privilegeTerm=" + privilegeTerm
		+ ", privilegeRange=" + privilegeRange + ", lowAmount=" + lowAmount + ", privilegeType=" + privilegeType
		+ ", rule=" + rule + ", ruleIntroduce=" + ruleIntroduce + ", MappingOID=" + MappingOID
		+ ", privilegeOID=" + privilegeOID + ", jsDate=" + jsDate + ", ksDate=" + ksDate + "]";
    }

}
