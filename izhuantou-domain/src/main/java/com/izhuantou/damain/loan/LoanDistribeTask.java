package com.izhuantou.damain.loan;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 工作流任务分配
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "loan_distribetask")
public class LoanDistribeTask extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 7278137025590511605L;

    /**
     * OID
     */
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 用户OID
     */
    @Column(name = "nndOID")
    private String nndOID;
    /**
     * 用户中文名
     */
    @Column(name = "usernameCN")
    private String usernameCN;
    /**
     * 工作流角色OID
     */
    private String rolename;
    /**
     * 工作流角色中文名
     */
    @Column(name = "rolenameCN")
    private String rolenameCN;
    /**
     * 执行任务数
     */
    private String tasknum;
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

    public String getNndOID() {
	return nndOID;
    }

    public void setNndOID(String nndOID) {
	this.nndOID = nndOID;
    }

    public String getUsernameCN() {
	return usernameCN;
    }

    public void setUsernameCN(String usernameCN) {
	this.usernameCN = usernameCN;
    }

    public String getRolename() {
	return rolename;
    }

    public void setRolename(String rolename) {
	this.rolename = rolename;
    }

    public String getRolenameCN() {
	return rolenameCN;
    }

    public void setRolenameCN(String rolenameCN) {
	this.rolenameCN = rolenameCN;
    }

    public String getTasknum() {
	return tasknum;
    }

    public void setTasknum(String tasknum) {
	this.tasknum = tasknum;
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
	return "LoanDistribeTask [OID=" + OID + ", nndOID=" + nndOID + ", usernameCN=" + usernameCN + ", rolename="
		+ rolename + ", rolenameCN=" + rolenameCN + ", tasknum=" + tasknum + ", describe0=" + describe0
		+ ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid
		+ ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime + ", refresh=" + refresh
		+ ", version=" + version + "]";
    }

}
