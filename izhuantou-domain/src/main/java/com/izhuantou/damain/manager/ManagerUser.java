package com.izhuantou.damain.manager;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 用户实体类
 *
 * @author fucheng
 * @date 2018-05-28
 */
@Table(name = "tb_manager_user")
public class ManagerUser extends BasePojo {
    /** 
    *  
    */
    private static final long serialVersionUID = 1L;
    // 唯一主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String oid;
    // 用户真实姓名
    private String name;
    // 用户登录账号
    private String userName;
    // 用户登录密码
    private String passWord;
    // 用户状态 0 正常 ，1 已删除，2 已锁定
    private Integer status;
    // 工号
    private String userNumber;
    // 短信验证码
    private String smsCode;
    // 电话号码
    private String phone;
    // 拖动条验证码
    private String captcha;
    // 职务 2018-05-30追加
    private String duty;
    // 部门 2018-05-30 追加
    private String department;

    public String getOid() {
	return oid;
    }

    public void setOid(String oid) {
	this.oid = oid;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public Integer getStatus() {
	return status;
    }

    public void setStatus(Integer status) {
	this.status = status;
    }

    public String getPassWord() {
	return passWord;
    }

    public void setPassWord(String passWord) {
	this.passWord = passWord;
    }

    public String getUserNumber() {
	return userNumber;
    }

    public void setUserNumber(String userNumber) {
	this.userNumber = userNumber;
    }

    public String getSmsCode() {
	return smsCode;
    }

    public void setSmsCode(String smsCode) {
	this.smsCode = smsCode;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getCaptcha() {
	return captcha;
    }

    public void setCaptcha(String captcha) {
	this.captcha = captcha;
    }

    public String getDuty() {
	return duty;
    }

    public void setDuty(String duty) {
	this.duty = duty;
    }

    public String getDepartment() {
	return department;
    }

    public void setDepartment(String department) {
	this.department = department;
    }

    @Override
    public String toString() {
	return "ManagerUser [oid=" + oid + ", name=" + name + ", userName=" + userName + ", passWord=" + passWord
		+ ", status=" + status + ", userNumber=" + userNumber + ", smsCode=" + smsCode + ", phone=" + phone
		+ ", captcha=" + captcha + ", duty=" + duty + ", department=" + department + "]";
    }

}
