package com.izhuantou.damain.lend;

import java.math.BigDecimal;

import com.izhuantou.damain.BasePojo;

/**
 * 加息券
 * 
 * @author yangbosen
 *
 */
public class DDTPrivilege extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String mappingOID;

    private String OID;
    /**
     * 加息券名称
     */
    private String privilegeName;
    /**
     * 加息券时常
     */
    private Integer privilegeTerm;
    /**
     * 
     */
    private BigDecimal privilegeRange;
    /**
     * 加息券最低金额
     */
    private BigDecimal lowAmount;
    /**
     * 加息券类型
     */
    private String privilegeType;
    /**
     * 
     */
    private Integer rule;
    /**
     * 加息券规则介绍
     */
    private String ruleIntroduce;
    /**
     * 加息券OID
     */
    private String privilegeOID;
    /**
     * 开始时间
     */
    private String ksDate;
    /**
     * 结束时间
     */
    private String jsDate;

    public String getPrivilegeOID() {
	return privilegeOID;
    }

    public void setPrivilegeOID(String privilegeOID) {
	this.privilegeOID = privilegeOID;
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

    public String getMappingOID() {
	return mappingOID;
    }

    public void setMappingOID(String mappingOID) {
	this.mappingOID = mappingOID;
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

    public Integer getPrivilegeTerm() {
	return privilegeTerm;
    }

    public void setPrivilegeTerm(Integer privilegeTerm) {
	this.privilegeTerm = privilegeTerm;
    }

    public BigDecimal getPrivilegeRange() {
	return privilegeRange;
    }

    public void setPrivilegeRange(BigDecimal privilegeRange) {
	this.privilegeRange = privilegeRange;
    }

    public BigDecimal getLowAmount() {
	return lowAmount;
    }

    public void setLowAmount(BigDecimal lowAmount) {
	this.lowAmount = lowAmount;
    }

    public String getPrivilegeType() {
	return privilegeType;
    }

    public void setPrivilegeType(String privilegeType) {
	this.privilegeType = privilegeType;
    }

    public Integer getRule() {
	return rule;
    }

    public void setRule(Integer rule) {
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
	return "DDTPrivilege [mappingOID=" + mappingOID + ", OID=" + OID + ", privilegeName=" + privilegeName
		+ ", privilegeTerm=" + privilegeTerm + ", privilegeRange=" + privilegeRange + ", lowAmount=" + lowAmount
		+ ", privilegeType=" + privilegeType + ", rule=" + rule + ", ruleIntroduce=" + ruleIntroduce
		+ ", privilegeOID=" + privilegeOID + ", ksDate=" + ksDate + ", jsDate=" + jsDate + "]";
    }

}
