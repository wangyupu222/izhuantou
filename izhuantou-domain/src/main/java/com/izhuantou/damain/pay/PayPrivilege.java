package com.izhuantou.damain.pay;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 特权信息
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "pay_privilege")
public class PayPrivilege extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = -4586636777010920269L;

    /**
     * OID
     */
    @Id
    @Column(name = "OID")
    private String OID;

    /**
     * 特权名称
     */
    @Column(name = "privilegeName")
    private String privilegeName;
    /**
     * 特权期限(天数)
     */
    @Column(name = "privilegeTerm")
    private Integer privilegeTerm;
    /**
     * 特权域(红包数额/加息百分位)
     */
    @Column(name = "privilegeRange")
    private BigDecimal privilegeRange;
    /**
     * 最低额度
     */
    @Column(name = "lowAmount")
    private BigDecimal lowAmount;
    /**
     * 特权类型(0:红包；1:加息)
     */
    @Column(name = "privilegeType")
    private String privilegeType;
    /**
     * 是否停用(0使用1停用)
     */
    @Column(name = "isDisable")
    private String isDisable;
    /**
     * 特权作用起效日
     */
    @Column(name = "beginDateTime")
    private Date beginDateTime;
    /**
     * 特权作用过期日
     */
    @Column(name = "endDateTime")
    private Date endDateTime;
    /**
     * 特权作用类型(e.g.:用于活动或者用于自动触发)
     */
    private String kind;
    /**
     * 限制
     */
    private Integer rule;

    /**
     * 限制说明
     */
    @Column(name = "ruleIntroduce")
    private String ruleIntroduce;

    /**
     * 用于什么活动
     */
    @Column(name = "useFor")
    private String useFor;

    /**
     * 描述
     */
    private String describe0;
    /**
     * 序号
     */
    @Column(name = "NO")
    private Integer NO;
    /**
     * 增加用户OID
     */
    @Column(name = "addUserOID")
    private String addUserOID;
    /**
     * 修改用户OID
     */
    @Column(name = "updUserOID")
    private String updUserOID;
    /**
     * 是否有效
     */

    private Boolean valid;

    /**
     * 添加的时间
     */
    @Column(name = "addDateTime")
    private Date addDateTime;
    /**
     * 更新的时间
     */
    @Column(name = "updDateTime")
    private Date updDateTime;
    /**
     * 是否更新
     */

    private Boolean refresh;
    /**
     * 版本号
     */
    private Integer version;

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

    public String getIsDisable() {
	return isDisable;
    }

    public void setIsDisable(String isDisable) {
	this.isDisable = isDisable;
    }

    public Date getBeginDateTime() {
	return beginDateTime;
    }

    public void setBeginDateTime(Date beginDateTime) {
	this.beginDateTime = beginDateTime;
    }

    public Date getEndDateTime() {
	return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
	this.endDateTime = endDateTime;
    }

    public String getKind() {
	return kind;
    }

    public void setKind(String kind) {
	this.kind = kind;
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

    public String getUseFor() {
	return useFor;
    }

    public void setUseFor(String useFor) {
	this.useFor = useFor;
    }

    public String getDescribe0() {
	return describe0;
    }

    public void setDescribe0(String describe0) {
	this.describe0 = describe0;
    }

    public Integer getNO() {
	return NO;
    }

    public void setNO(Integer nO) {
	NO = nO;
    }

    public String getAddUserOID() {
	return addUserOID;
    }

    public void setAddUserOID(String addUserOID) {
	this.addUserOID = addUserOID;
    }

    public String getUpdUserOID() {
	return updUserOID;
    }

    public void setUpdUserOID(String updUserOID) {
	this.updUserOID = updUserOID;
    }

    public Boolean getValid() {
	return valid;
    }

    public void setValid(Boolean valid) {
	this.valid = valid;
    }

    public Date getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(Date addDateTime) {
	this.addDateTime = addDateTime;
    }

    public Date getUpdDateTime() {
	return updDateTime;
    }

    public void setUpdDateTime(Date updDateTime) {
	this.updDateTime = updDateTime;
    }

    public Boolean getRefresh() {
	return refresh;
    }

    public void setRefresh(Boolean refresh) {
	this.refresh = refresh;
    }

    public Integer getVersion() {
	return version;
    }

    public void setVersion(Integer version) {
	this.version = version;
    }

    @Override
    public String toString() {
	return "PayPrivilege [OID=" + OID + ", privilegeName=" + privilegeName + ", privilegeTerm=" + privilegeTerm
		+ ", privilegeRange=" + privilegeRange + ", lowAmount=" + lowAmount + ", privilegeType=" + privilegeType
		+ ", isDisable=" + isDisable + ", beginDateTime=" + beginDateTime + ", endDateTime=" + endDateTime
		+ ", kind=" + kind + ", rule=" + rule + ", ruleIntroduce=" + ruleIntroduce + ", useFor=" + useFor
		+ ", describe0=" + describe0 + ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID="
		+ updUserOID + ", valid=" + valid + ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime
		+ ", refresh=" + refresh + ", version=" + version + "]";
    }

}
