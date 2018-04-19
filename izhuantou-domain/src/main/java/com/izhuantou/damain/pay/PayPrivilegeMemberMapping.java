package com.izhuantou.damain.pay;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 特权红包加息与出借人关系映射
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "pay_privilegemembermapping")
public class PayPrivilegeMemberMapping extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 2008123899954617546L;
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 特权对应记录OID
     */
    @Column(name = "privilegeOID")
    private String privilegeOID;
    /**
     * 出借人用户对应记录OID
     */
    @Column(name = "memberOID")
    private String memberOID;
    /**
     * 是否使用
     */
    @Column(name = "isUsed")
    private String isUsed;
    /**
     * 用户手中生效时间
     */
    @Column(name = "startDate")
    private Date startDate;
    /**
     * 用户手中失效时间
     */
    @Column(name = "endDate")
    private Date endDate;
    /**
     * 用户手中使用期限
     */
    @Column(name = "useTerm")
    private Integer useTerm;
    /**
     * 限制
     */
    private Integer rule;
    /**
     * 万能加息券特殊说明的OID
     */
    @Column(name = "psOID")
    private String psOID;

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
     * 描述
     */
    private String describe0;
    /**
     * 序号
     */
    @Column(name = "NO")
    private Integer NO;
    /**
     * 是否有效
     */
    private Boolean valid;

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

    public String getPrivilegeOID() {
	return privilegeOID;
    }

    public void setPrivilegeOID(String privilegeOID) {
	this.privilegeOID = privilegeOID;
    }

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public String getIsUsed() {
	return isUsed;
    }

    public void setIsUsed(String isUsed) {
	this.isUsed = isUsed;
    }

    public Date getStartDate() {
	return startDate;
    }

    public void setStartDate(Date startDate) {
	this.startDate = startDate;
    }

    public Date getEndDate() {
	return endDate;
    }

    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }

    public Integer getUseTerm() {
	return useTerm;
    }

    public void setUseTerm(Integer useTerm) {
	this.useTerm = useTerm;
    }

    public Integer getRule() {
	return rule;
    }

    public void setRule(Integer rule) {
	this.rule = rule;
    }

    public String getPsOID() {
	return psOID;
    }

    public void setPsOID(String psOID) {
	this.psOID = psOID;
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

    public Boolean getValid() {
	return valid;
    }

    public void setValid(Boolean valid) {
	this.valid = valid;
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
	return "PAY_PrivilegeMemberMapping [OID=" + OID + ", privilegeOID=" + privilegeOID + ", memberOID=" + memberOID
		+ ", isUsed=" + isUsed + ", startDate=" + startDate + ", endDate=" + endDate + ", useTerm=" + useTerm
		+ ", rule=" + rule + ", psOID=" + psOID + ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID
		+ ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime + ", describe0=" + describe0 + ", NO="
		+ NO + ", valid=" + valid + ", refresh=" + refresh + ", version=" + version + "]";
    }

}
