package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class MyPrivilegeDaoDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6397295701684651928L;

    /**
     * 特权名称
     */
    private String privilegeName;
    /**
     * 特权名称
     */
    private String OID;
    /**
     * 特权域(红包数额/加息百分位)
     */
    private BigDecimal privilegeRange;
    /**
     * 最低额度
     */
    private BigDecimal lowAmount;
    /**
     * 限制说明
     */
    private String ruleIntroduce;
    /**
     * 用户手中使用期限
     */
    private Integer useTerm;
    /**
     * 用户手中生效时间
     */
    private String startDate;
    /**
     * 用户手中失效时间
     */
    private String endDate;
    /**
     * 万能加息券特殊说明的OID
     */
    private String psOID;

    public String getPrivilegeName() {
	return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
	this.privilegeName = privilegeName;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
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

    public String getRuleIntroduce() {
	return ruleIntroduce;
    }

    public void setRuleIntroduce(String ruleIntroduce) {
	this.ruleIntroduce = ruleIntroduce;
    }

    public Integer getUseTerm() {
	return useTerm;
    }

    public void setUseTerm(Integer useTerm) {
	this.useTerm = useTerm;
    }

    public String getStartDate() {
	return startDate;
    }

    public void setStartDate(String startDate) {
	this.startDate = startDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public String getPsOID() {
	return psOID;
    }

    public void setPsOID(String psOID) {
	this.psOID = psOID;
    }

    @Override
    public String toString() {
	return "MyPrivilegeDaoDTO [privilegeName=" + privilegeName + ", OID=" + OID + ", privilegeRange="
		+ privilegeRange + ", lowAmount=" + lowAmount + ", ruleIntroduce=" + ruleIntroduce + ", useTerm="
		+ useTerm + ", startDate=" + startDate + ", endDate=" + endDate + ", psOID=" + psOID + "]";
    }

}
