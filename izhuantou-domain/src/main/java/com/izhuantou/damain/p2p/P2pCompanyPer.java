package com.izhuantou.damain.p2p;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 公司人员信息表
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "p2p_companyper")
public class P2pCompanyPer extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = -6573994791977719341L;

    /**
     * OID
     */
    private String OID;
    /**
     * 业务人员的OID
     */
    private String yewuOID;
    /**
     * GroupIOD
     */
    private String groupOID;

    /**
     * userIOD
     */
    private String userOID;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 公司人员定义名称
     */
    private String name;

    /**
     * 公司人员部门
     */
    private String department;
    /**
     * 公司人员职业
     */
    private String occupation;

    /**
     * 公司人员日期
     */
    private String time;
    /**
     * 平台编号
     */

    private String number;
    /**
     * 备注
     */
    private String message;
    /**
     * 职责角色
     */
    private String zhize;

    /**
     * 菜单角色
     */
    private String caidan;

    /**
     * 工作流角色
     */
    private String workflow;

    /**
     * 描述
     */
    private String describe0;
    /**
     * 序号
     */
    @Column(name = "NO")
    private String NO;
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
    private String valid;

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

    public String getYewuOID() {
	return yewuOID;
    }

    public void setYewuOID(String yewuOID) {
	this.yewuOID = yewuOID;
    }

    public String getGroupOID() {
	return groupOID;
    }

    public void setGroupOID(String groupOID) {
	this.groupOID = groupOID;
    }

    public String getUserOID() {
	return userOID;
    }

    public void setUserOID(String userOID) {
	this.userOID = userOID;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPwd() {
	return pwd;
    }

    public void setPwd(String pwd) {
	this.pwd = pwd;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDepartment() {
	return department;
    }

    public void setDepartment(String department) {
	this.department = department;
    }

    public String getOccupation() {
	return occupation;
    }

    public void setOccupation(String occupation) {
	this.occupation = occupation;
    }

    public String getTime() {
	return time;
    }

    public void setTime(String time) {
	this.time = time;
    }

    public String getNumber() {
	return number;
    }

    public void setNumber(String number) {
	this.number = number;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public String getZhize() {
	return zhize;
    }

    public void setZhize(String zhize) {
	this.zhize = zhize;
    }

    public String getCaidan() {
	return caidan;
    }

    public void setCaidan(String caidan) {
	this.caidan = caidan;
    }

    public String getWorkflow() {
	return workflow;
    }

    public void setWorkflow(String workflow) {
	this.workflow = workflow;
    }

    public String getDescribe0() {
	return describe0;
    }

    public void setDescribe0(String describe0) {
	this.describe0 = describe0;
    }

    public String getNO() {
	return NO;
    }

    public void setNO(String nO) {
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

    public String getValid() {
	return valid;
    }

    public void setValid(String valid) {
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
	return "P2pCompanyPer [OID=" + OID + ", yewuOID=" + yewuOID + ", groupOID=" + groupOID + ", userOID=" + userOID
		+ ", username=" + username + ", pwd=" + pwd + ", name=" + name + ", department=" + department
		+ ", occupation=" + occupation + ", time=" + time + ", number=" + number + ", message=" + message
		+ ", zhize=" + zhize + ", caidan=" + caidan + ", workflow=" + workflow + ", describe0=" + describe0
		+ ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid
		+ ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime + ", refresh=" + refresh
		+ ", version=" + version + "]";
    }

}
