package com.izhuantou.damain.lend;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

@Table(name = "plan_content")
public class PlanContent extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 709470708839277936L;
    /**
     * 主表产品OID
     */
    @Column(name = "OID")
    private String OID;

    @Column(name = "className")
    private String className;

    @Column(name = "nameCN")
    private String nameCN;

    private String parameter;

    /**
     * 创建时间
     */
    @Column(name = "createDateTime")
    private Date createDateTime;

    /**
     * 执行时间
     */
    @Column(name = "executeDateTime")
    private Date executeDateTime;

    /**
     * 执行间隔
     */
    @Column(name = "executeSpaceNumber")
    private Integer executeSpaceNumber;

    /**
     * 延迟时间
     */
    @Column(name = "delayTime")
    private Long delayTime;

    /**
     * 循环间隔
     */
    @Column(name = "cycleSpace")
    private Integer cycleSpace;

    /**
     * 循环次数
     */
    @Column(name = "cycleNumber")
    private Integer cycleNumber;

    /**
     * 状态
     */
    private String state;
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

    private boolean refresh;
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

    public String getClassName() {
	return className;
    }

    public void setClassName(String className) {
	this.className = className;
    }

    public String getNameCN() {
	return nameCN;
    }

    public void setNameCN(String nameCN) {
	this.nameCN = nameCN;
    }

    public String getParameter() {
	return parameter;
    }

    public void setParameter(String parameter) {
	this.parameter = parameter;
    }

    public Date getCreateDateTime() {
	return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
	this.createDateTime = createDateTime;
    }

    public Date getExecuteDateTime() {
	return executeDateTime;
    }

    public void setExecuteDateTime(Date executeDateTime) {
	this.executeDateTime = executeDateTime;
    }

    public Integer getExecuteSpaceNumber() {
	return executeSpaceNumber;
    }

    public void setExecuteSpaceNumber(Integer executeSpaceNumber) {
	this.executeSpaceNumber = executeSpaceNumber;
    }

    public Long getDelayTime() {
	return delayTime;
    }

    public void setDelayTime(Long delayTime) {
	this.delayTime = delayTime;
    }

    public Integer getCycleSpace() {
	return cycleSpace;
    }

    public void setCycleSpace(Integer cycleSpace) {
	this.cycleSpace = cycleSpace;
    }

    public Integer getCycleNumber() {
	return cycleNumber;
    }

    public void setCycleNumber(Integer cycleNumber) {
	this.cycleNumber = cycleNumber;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
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

    public boolean isRefresh() {
	return refresh;
    }

    public void setRefresh(boolean refresh) {
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
	return "PlanContent [OID=" + OID + ", className=" + className + ", nameCN=" + nameCN + ", parameter="
		+ parameter + ", createDateTime=" + createDateTime + ", executeDateTime=" + executeDateTime
		+ ", executeSpaceNumber=" + executeSpaceNumber + ", delayTime=" + delayTime + ", cycleSpace="
		+ cycleSpace + ", cycleNumber=" + cycleNumber + ", state=" + state + ", describe0=" + describe0
		+ ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid
		+ ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime + ", refresh=" + refresh
		+ ", version=" + version + "]";
    }

}
